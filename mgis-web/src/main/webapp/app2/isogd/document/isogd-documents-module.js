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
                controller: function ($scope, $state, $stateParams, ISOGDDocumentsService, $modal, $rootScope, MGISCommonsModalForm, ISOGDClassifiersDocumentsTypesService, ISOGClassifiersDocumentsSubObjectsService) {
                    $scope.stateParams = $stateParams;

                    function updateGrid() {
                        ISOGDDocumentsService.get("", 0, 0, $stateParams.volumeId).then(function (data) {
                            $scope.documents = data.list;
                        });
                    }

                    function openEditDocumentForm(modalScope) {
                        MGISCommonsModalForm.open("app2/isogd/document/isogd-document-form.htm", modalScope,
                            function ($scope, $modalInstance) {
                                ISOGDDocumentsService.save($scope.document).then(function (data) {
                                    $modalInstance.close();
                                    updateGrid();
                                });
                            });
                    }

                    updateGrid();

                    // Document
                    $scope.addDocument = function (volumeId) {
                        ISOGDClassifiersDocumentsTypesService.get().then(function (documentTypes) {
                            ISOGClassifiersDocumentsSubObjectsService.listByVolumeId(volumeId).then(function (subObjects) {
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
                                modalScope.availableDocumentSubObjects = subObjects.list;
                                openEditDocumentForm(modalScope)
                            });
                        });
                    }

                    $scope.editDocument = function (id) {
                        ISOGDClassifiersDocumentsTypesService.get().then(function (documentTypes) {
                            ISOGClassifiersDocumentsSubObjectsService.get().then(function (subObjects) {
                                ISOGDDocumentsService.get(id).then(function (data) {
                                    var modalScope = $rootScope.$new();
                                    modalScope.document = data;
                                    modalScope.availableDocumentTypes = documentTypes.list;
                                    modalScope.availableDocumentSubObjects = subObjects.list;
                                    openEditDocumentForm(modalScope);
                                });
                            });
                        });
                    }

                    $scope.removeDocument = function (documentId) {
                        MGISCommonsModalForm.confirmRemoval("app2/common/confirm-deletion.htm", function ($scope, $modalInstance) {
                            ISOGDDocumentsService.remove(documentId).then(function (data) {
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

