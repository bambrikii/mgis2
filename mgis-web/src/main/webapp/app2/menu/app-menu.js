angular.module("mgis.menu.main", [ "ui.router", "ui.bootstrap" ]) //
.controller("MainMenuCtrl", function($scope, $location) {
	$scope.navCollapsed = false;
	$scope.isogdMenuActive = function() {
		return $location.path().indexOf("/isogd") > -1;
	}
	$scope.toggleNavCollapsed = function() {
		$scope.navCollapsed = !$scope.navCollapsed;
	}
});
