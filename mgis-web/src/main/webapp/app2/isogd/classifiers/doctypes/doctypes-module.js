/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
angular.module("mgis.isogd.classifiers.doctypes", ["ui-router", "ui.bootstrap", //
        "mgis.isogd.classifiers.doctypes.service"
    ]
) //
    .config(function ($stateProvider, $urlRouterProvider) {
//$stateProvider //
//    .state("classifiers.doctypes")
    }) //
    .controller("ISOGDDocTypesController", function ($scope, ISOGDDocTypesService) {
        $scope.first = 0;
        $scope.max = 15;
        function updateGrid() {
            ISOGDDocTypesService.get("", $scope.first, $scope.max).then(function (data) {
                $scope.doctypes = data.list;
            });
        }

        function openModal() {
            $modal.open({
                animate: true,
                scope: $scope,
                templateUrl: "app2/isogd/classifiers/doctypes/edit.htm",
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        ISOGDDocTypesService.save($scope.editDocType).then(function () {
                            $modalInstance.close();
                            updateGrid();
                        })
                    }
                }
            });
        }

        updateGrid();

        $scope.add = function () {
            $scope.editDocType = {
                id: 0,
                code: ""
            }
            openModal();
        }
        $scope.edit = function (id) {
            ISOGDDocTypesService.get(id).then(function (item) {
                $scope.editDocType = item;
                openModal();
            });
        }
        $scope.remove = function (id) {
            $modal.open({
                templateUrl: "app2/common/confirm-deletion.htm"
            })
        }
    })
;
