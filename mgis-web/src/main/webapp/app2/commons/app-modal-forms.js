/**
 * Created by Alexander Arakelyan on 24.06.15.
 */
angular.module("mgis.commons", ["ui.bootstrap"])
    .factory("MGISCommonsModalForm", function ($modal) {
        return {
            confirmRemoval: function (acceptMethod) {
                var modalInstance = $modal.open({
                    templateUrl: "app2/commons/confirm-deletion.htm",
                    controller: function ($scope, $modalInstance) {
                        $scope.ok = function () {
                            acceptMethod($modalInstance);
                        };
                        $scope.cancel = function () {
                            $modalInstance.dismiss('cancel');
                        }
                    }
                });
            },
            edit: function (templateUrl, modalScope, applyMethod, params) {
                var params2 = {
                    scope: modalScope,
                    templateUrl: templateUrl,
                    controller: function ($scope, $modalInstance) {
                        $scope.ok = function () {
                            applyMethod($scope, $modalInstance);
                        }
                        $scope.cancel = function () {
                            $modalInstance.dismiss("cancel");
                        }
                    }
                }
                if (params) {
                    angular.extend(params2, params);
                }
                $modal.open(params2);
            }
        }
    }) //
//
    .controller("MGISDateTimeController", function ($scope) {

        $scope.showWeeks = true;
        $scope.toggleWeeks = function () {
            $scope.showWeeks = !$scope.showWeeks;
        };

        $scope.clear = function () {
            $scope.dt = null;
        };

        // Disable weekend selection
        $scope.disabled = function (date, mode) {
            return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
        };


        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            'year-format': "'yy'",
            'starting-day': 1
        };

        $scope.format = "dd.MM.yyyy";
    });

;
