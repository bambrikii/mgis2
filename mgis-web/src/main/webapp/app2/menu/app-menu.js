angular.module("mgis.menu.main", [ "ui.router", "ui.bootstrap" ]) //
.controller("MainMenuCtrl", function($scope, $location) {
	$scope.navCollapsed = false;
	function resetMenus() {
		$scope.isogdMenuActive = false;
		$scope.landsMenuActive = false;
		$scope.oksMenuActive = false;
		$scope.administrationMenuActive = false;
	}
	$scope.isogdMenuClicked = function() {
		resetMenus();
		$scope.isogdMenuActive = true;
	}
	$scope.landsMenuClicked = function() {
		resetMenus();
		$scope.landsMenuActive = true;
	}
	$scope.oksMenuClicked = function() {
		resetMenus();
		$scope.oksMenuActive = true;
	}
	$scope.administrationMenuClicked = function() {
		resetMenus();
		$scope.administrationMenuActive = true;
	}

	$scope.toggleNavCollapsed = function() {
		$scope.navCollapsed = !$scope.navCollapsed;
	}
});
