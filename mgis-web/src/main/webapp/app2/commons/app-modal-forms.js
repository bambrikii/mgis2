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
            edit: function (templateUrl, modalScope, applyMethod) {
                $modal.open({
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
                });
            }
        }
    })
;
