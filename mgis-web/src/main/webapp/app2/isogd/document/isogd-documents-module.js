angular.module("mgis.isogd.documents", ["ui.router", "ui.bootstrap", "ngFileUpload", //
    "mgis.commons",
    "mgis.isogd.documents.service", //
    "mgis.isogd.classifiers.documents.types.service", //
    "mgis.isogd.classifiers.documents.structure.services" //
]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider //
            .state("isogd.documents", {
                data: {
                    displayName: "Documents"
                },
                url: "/sections/:sectionId/books/:bookId/volumes/:volumeId/documents/",
                templateUrl: "app2/isogd/document/isogd-documents-list.htm",
                controller: function ($scope, $state, $stateParams, ISOGDDocumentsService, $modal, $rootScope, MGISCommonsModalForm, ISOGDClassifiersDocumentsTypesService, Upload) {
                    $scope.stateParams = $stateParams;

                    function updateGrid() {
                        ISOGDDocumentsService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage, $stateParams.volumeId).then(function (data) {
                            $scope.documentsPager = data;
                        });
                    }

                    function openEditDocumentForm(modalScope) {
                        ISOGDDocumentsService.loadDocumentClassByVolumeId($stateParams.volumeId).then(function (documentClass) {
                            if (documentClass.hasCommonPart) {
                                modalScope.hasCommonPart = documentClass.hasCommonPart;
                                modalScope.commonPartTab = {
                                    open: documentClass.hasCommonPart
                                }
                                modalScope.commonPartUploadProgress = function (event) {
                                    console.log("common part upload progress");
                                }
                                modalScope.commonPartUploadComplete = function () {
                                    console.log("common part upload complete");
                                }
                            }
                            if (documentClass.hasSpecialPart) {
                                modalScope.hasSpecialPart = documentClass.hasSpecialPart;
                                modalScope.specialPartTab = {
                                    open: documentClass.hasSpecialPart
                                }
                                modalScope.specialPartUploadProgress = function (event) {
                                    console.log("common part upload progress");
                                }
                                modalScope.specialPartUploadComplete = function () {
                                    console.log("common part upload complete");
                                }
                            }
                            MGISCommonsModalForm.edit("app2/isogd/document/isogd-document-form.htm", modalScope,
                                function ($scope, $modalInstance) {
                                    ISOGDDocumentsService.save($scope.document).then(function (data) {
                                        $modalInstance.close();
                                        updateGrid();
                                    });
                                });
                        });
                    }


                    $scope.itemsPerPage = 15;
                    $scope.currentPage = 1;
                    $scope.pageChanged = function () {
                        updateGrid();
                    }

                    updateGrid();

                    // Document
                    $scope.addDocument = function (volumeId) {
                        ISOGDClassifiersDocumentsTypesService.get().then(function (documentTypes) {
                            ISOGDDocumentsService.listDocumentSubObjectsByVolumeId(volumeId).then(function (documentSubObjects) {
                                var modalScope = $rootScope.$new();
                                modalScope.document = {
                                    id: 0,
                                    name: "",
                                    documentType: {},
                                    documentSubObject: {},
                                    volume: {
                                        id: volumeId
                                    }
                                }
                                modalScope.availableDocumentTypes = documentTypes.list;
                                modalScope.availableDocumentSubObjects = documentSubObjects.list;
                                openEditDocumentForm(modalScope)
                            });
                        });
                    }

                    $scope.editDocument = function (id) {
                        ISOGDClassifiersDocumentsTypesService.get().then(function (documentTypes) {
                            ISOGDDocumentsService.get(id).then(function (document) {
                                ISOGDDocumentsService.listDocumentSubObjectsByVolumeId(document.volume.id).then(function (documentSubObjects) {
                                    var modalScope = $rootScope.$new();
                                    modalScope.document = document;
                                    modalScope.availableDocumentTypes = documentTypes.list;
                                    modalScope.availableDocumentSubObjects = documentSubObjects.list;
                                    openEditDocumentForm(modalScope);
                                });
                            });
                        });
                    }

                    $scope.removeDocument = function (id) {
                        MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                            ISOGDDocumentsService.remove(id).then(function (document) {
                                $modalInstance.close();
                                updateGrid();
                            });
                        });
                    }

                }
            })
    }).controller("ISOGDDocumentsCtrl", function ($scope) {

    }) //
    .controller("MGISUploadFileController", function ($scope, Upload) {
        console.log("MGISUploadFileController");
        var uploadUrl1;
        var uploadFields1;
        var uploadProgressHandler1;
        var uploadCompleteHandler1;
        $scope.files = [];
        $scope.init = function (uploadUrl, uploadFields, uploadProgressHandler, uploadCompleteHandler) {
            console.log(arguments);
            uploadUrl1 = uploadUrl;
            uploadFields1 = uploadFields;
            uploadProgressHandler1 = uploadProgressHandler;
            uploadCompleteHandler1 = uploadCompleteHandler;
        }

        $scope.upload = function (files) {
            for (var i = 0; i < files.length; i++) {
                Upload.upload({
                    url: uploadUrl1,
                    fields: uploadFields1,
                    file: files[i]
                }).progress(function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
                }).success(function (data, status, headers, config) {
                    console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                    uploadCompleteHandler1(data);
                });
            }
        }

        $scope.$watch($scope.files, function () {
            console.log()
            $scope.upload($scope.files);
        })
    }
)

;

