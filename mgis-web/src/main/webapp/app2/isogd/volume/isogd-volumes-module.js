angular.module("mgis.isogd.volumes", ["ui.router", "ui.bootstrap"]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider//
            .state("isogd.volumes", {
                url: "/sections/:sectionId/volumes/",
                templateUrl: "app2/isogd/volume/isogd-volumes-list.htm",
                controller: function ($scope, $state, $stateParams, ISOGDVolumesService, $modal) {
                    console.log("volumes...");
                    console.log($scope);
                    $scope.stateParams = $stateParams;

                    $scope.volumes = function (sectionId) {
                        return ISOGDVolumesService.list(sectionId);
                    }

                    // Volume
                    $scope.addVolume = function (sectionId) {
                        console.log("add Volume");
                    }
                    $scope.editVolume = function (volumeId) {
                        console.log("edit Volume");
                    }
                    $scope.removeVolume = function (volumeId) {
                        var modalInstance = $modal.open({
                            templateUrl: 'app2/isogd/isogd-confirm-deletion.htm',
                            controller: function ($scope, $modalInstance) {
                                $scope.ok = function () {
                                    $modalInstance.close("");
                                    console.log("remove Volume");
                                    // TODO:
                                }
                                $scope.cancel = function () {
                                    $modalInstance.dismiss('cancel');
                                }
                            }
                        });
                    }
                }
            })//
            .state("isogd.volumes.edit", {
                url: "edit/:volumeId",
                templateUrl: "app2/isogd/volume/isogd-volume-form.htm",
                controller: function ($scope, $state) {
                    console.log("edit section...");
                }
            }) //

    }) //
    .controller("ISOGDVolumesCtrl", function ($scope) {
        console.log($scope);
    }) //
;

