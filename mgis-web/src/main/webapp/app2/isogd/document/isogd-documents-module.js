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
                controller: function ($scope, $state, $stateParams, ISOGDDocumentsService, $modal, $rootScope, MGISCommonsModalForm, ISOGDClassifiersDocumentsTypesService) {
                    $scope.stateParams = $stateParams;

                    function updateGrid() {
                        ISOGDDocumentsService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage, $stateParams.volumeId).then(function (data) {
                            $scope.documentsPager = data;
                        });
                    }

                    function openEditDocumentForm(modalScope) {
                        ISOGDDocumentsService.loadDocumentClassByVolumeId($stateParams.volumeId).then(function (documentClass) {
                            if (documentClass.hasCommonPart) {
                                modalScope.commonPartTab = {
                                    open: true
                                }
                                modalScope.commonPartUploadComplete = function (data) {
                                    modalScope.document.commonPart = modalScope.document.commonPart || {};
                                    modalScope.document.commonPart.documentContents = modalScope.document.commonPart.documentContents || new Array();
                                    modalScope.document.commonPart.documentContents.push({id: data.id, fileName: data.fileName});
                                }
                            }
                            if (documentClass.hasSpecialPart) {
                                modalScope.specialPartTab = {
                                    open: true
                                }
                                modalScope.specialPartUploadComplete = function (data) {
                                    modalScope.document.specialPart = modalScope.document.specialPart || {};
                                    modalScope.document.specialPart.documentContents = modalScope.document.specialPart.documentContents || new Array();
                                    modalScope.document.specialPart.documentContents.push({id: data.id, fileName: data.fileName});
                                }
                            }
                            MGISCommonsModalForm.edit("app2/isogd/document/isogd-document-form.htm", modalScope,
                                function ($scope, $modalInstance) {
                                    ISOGDDocumentsService.save($scope.document).then(function (data) {
                                        updateGrid();
                                        $modalInstance.close();
                                    });
                                }, {
                                    windowClass: "mgis-document-modal-form"
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
    }) //
    .controller("MGISUploadFileController", function ($scope, Upload) {
        $scope.init = function (uploadUrl, uploadFields, uploadProgress, uploadComplete) {
            $scope.uploadUrl = uploadUrl;
            $scope.uploadFields = uploadFields;
            $scope.uploadProgress = uploadProgress;
            $scope.uploadComplete = uploadComplete;
        }

        $scope.$watch('files', function () {
            $scope.upload($scope.files);
        });

        $scope.upload = function (files) {
            if (files && files.length) {
                for (var i = 0; i < files.length; i++) {
                    var file = files[i];
                    Upload.upload({
                        url: $scope.uploadUrl,
                        fields: {'username': $scope.username},
                        file: file
                    }).progress(function (event) {
                        var progressPercentage = parseInt(100.0 * event.loaded / event.total);
                        if ($scope.uploadProgress) {
                            $scope.uploadProgress(event);
                        }
                    }).success(function (data, status, headers, config) {
                        if ($scope.uploadComplete) {
                            $scope.uploadComplete(data);
                        }
                    });
                }
            }
        };
    })
;

