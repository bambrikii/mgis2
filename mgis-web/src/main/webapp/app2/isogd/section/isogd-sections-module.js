angular.module("mgis.isogd.sections", ["ui.router", "ui.bootstrap",//
    "mgis.commons", //
    "mgis.isogd.sections.service",
    "mgis.isogd.classifiers.documents.structure.services"//
]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider//
            .state("isogd.sections", {
                url: "/sections/:sectionId",
                templateUrl: "app2/isogd/section/isogd-sections-list.htm",
                controller: function ($scope, $rootScope, $state, $stateParams, ISOGDSectionsService, $modal, $q, MGISCommonsModalForm, ISOGClassifiersDocumentsClassesService) {
                    $scope.stateParams = $stateParams;

                    function updateGrid() {
                        ISOGDSectionsService.get("", 0, 15).then(function (data) {
                            $scope.sections = data.list;
                        });
                    }

                    function editSectionModal(modalScope) {
                        MGISCommonsModalForm.edit("app2/isogd/section/isogd-section-form.htm", modalScope, function (scope, $modalInstance) {
                            ISOGDSectionsService.save(scope.section).then(function (data) {
                                $modalInstance.close();
                                updateGrid();
                            });
                        });
                    }

                    updateGrid();

                    // Section
                    $scope.addSection = function () {
                        ISOGClassifiersDocumentsClassesService.get().then(function (documentClassesData) {
                            var modalScope = $rootScope.$new();
                            modalScope.section = {
                                id: 0,
                                name: "",
                                documentClass: {}
                            };
                            modalScope.availableDocumentClasses = documentClassesData.list;
                            editSectionModal(modalScope);
                        });
                    }
                    $scope.editSection = function (sectionId) {
                        ISOGDSectionsService.get(sectionId).then(function (sectionsData) {
                            ISOGClassifiersDocumentsClassesService.get().then(function (documentClassesData) {
                                var modalScope = $rootScope.$new();
                                modalScope.section = sectionsData;
                                modalScope.availableDocumentClasses = documentClassesData.list;
                                editSectionModal(modalScope);
                            });
                        });
                    }
                    $scope.removeSection = function (sectionId) {
                        MGISCommonsModalForm.confirmRemoval(
                            function ($modalInstance) {
                                $modalInstance.close();
                                ISOGDSectionsService.remove(sectionId).then(function (data) {
                                    updateGrid();
                                });
                            }
                        );
                    }
                }
            })//
        ;
    }) //
    .controller("ISOGDSectionCtrl", function ($scope, ISOGDSectionsService) {
        $scope.sectionIsOpen = false;
        $scope.volumes1 = [];
        $scope.$watch("sectionIsOpen", function (newValue, oldValue, scope) {
            console.log("isOpenChanged: " + newValue + ", " + oldValue + ", " + scope.section.id);
            if (newValue) {
                // TODO: load volumes
                $scope.volumes1.push("asd");
                console.log($scope.volumes1);
            }
        })
    })//
;

angular.module("mgis.isogd.sections.module", ["ui.router", "ui.bootstrap"]) //
    .config(function ($stateProvider, $urlRouterProvider) {

    }) // https://angular-ui.github.io/bootstrap/
;

