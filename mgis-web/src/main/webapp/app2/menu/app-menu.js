angular.module("mgis.menu.main", [ "ui.router", "ui.bootstrap" ]) //
    .controller("MainMenuCtrl", function ($scope) {
        $scope.navCollapsed = false;
        $scope.toggleNavCollapsed = function () {
            $scope.navCollapsed = !$scope.navCollapsed;
        }
    });
