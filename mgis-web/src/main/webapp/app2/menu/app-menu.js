angular.module("mgis.menu.main", ["ui.router", "ui.bootstrap"]) //
	.controller("MainMenuCtrl", function ($scope, $location) {
		$scope.navCollapsed = false;
		function resetMenus() {
			$scope.isogdMenuActive = false;
			$scope.landsMenuActive = false;
			$scope.oksMenuActive = false;
			$scope.administrationMenuActive = false;
			$scope.terrZonesMenuActive = false;
		}

		$scope.isogdMenuClicked = function () {
			resetMenus();
			$scope.isogdMenuActive = true;
		}
		$scope.landsMenuClicked = function () {
			resetMenus();
			$scope.landsMenuActive = true;
		}
		$scope.terrZonesMenuClicked = function () {
			resetMenus();
			$scope.terrZonesMenuActive = true;
		}
		$scope.oksMenuClicked = function () {
			resetMenus();
			$scope.oksMenuActive = true;
		}
		$scope.administrationMenuClicked = function () {
			resetMenus();
			$scope.administrationMenuActive = true;
		}

		$scope.toggleNavCollapsed = function () {
			$scope.navCollapsed = !$scope.navCollapsed;
		}
	});
