angular.module("mgis.isogd.documents", ["ui.router", "ui.bootstrap",//
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
                            modalScope.hasCommonPart = documentClass.hasCommonPart;
                            modalScope.hasSpecialPart = documentClass.hasSpecialPart;
                            modalScope.commonPartTab = {
                                open: documentClass.hasCommonPart
                            }
                            modalScope.specialPartTab = {
                                open: documentClass.hasSpecialPart
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
;

