/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
angular.module("mgis.isogd.classifiers.documents.types", ["ui.router", "ui.bootstrap", //
        "mgis.commons",
        "mgis.isogd.classifiers.documents.types.service"
    ]
) //
    .config(function ($stateProvider, $urlRouterProvider) {
//$stateProvider //
//    .state("classifiers.doctypes")
    }) //
    .controller("ISOGDClassifiersDocumentsTypesController", function ($scope, $rootScope, ISOGDClassifiersDocumentsTypesService, MGISCommonsModalForm) {
        $scope.first = 0;
        $scope.max = 15;
        $scope.documentTypes = [];

        function updateGrid() {
            ISOGDClassifiersDocumentsTypesService.get("", $scope.first, $scope.max).then(function (data) {
                $scope.documentTypes = data.list;
            });
        }

        function openModal(modalScope) {
            MGISCommonsModalForm.edit("app2/isogd/classifiers/documents/types/edit.htm", modalScope, function ($scope, $modalInstance) {
                ISOGDClassifiersDocumentsTypesService.save($scope.documentType).then(function () {
                    $modalInstance.close();
                    updateGrid();
                })
            });
        }

        updateGrid();

        $scope.addDocumentType = function () {
            var modalScope = $rootScope.$new();
            modalScope.documentType = {
                id: 0,
                code: "",
                name: ""
            }
            openModal(modalScope);
        }

        $scope.editDocumentType = function (id) {
            ISOGDClassifiersDocumentsTypesService.get(id).then(function (item) {
                var modalScope = $rootScope.$new();
                modalScope.documentType = item;
                openModal(modalScope);
            });
        }
        $scope.removeDocumentType = function (id) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                ISOGDClassifiersDocumentsTypesService.remove(id).then(function () {
                    updateGrid();
                    $modalInstance.close();
                })
            });
        }
    })
;
