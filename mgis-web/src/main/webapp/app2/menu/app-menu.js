angular.module("mgis.menu.main", ["ui.router", "ui.bootstrap"]) //
	.controller("MainMenuCtrl", function ($scope, $location) {
		$scope.navCollapsed = false;
		function resetMenus() {
			$scope.isogdMenuActive = false;
			$scope.landsMenuActive = false;
			$scope.oksMenuActive = false;
			$scope.administrationMenuActive = false;
			$scope.terrZonesMenuActive = false;
			$scope.personsMenuActive = false;
			$scope.addressesMenuActive = false;
			$scope.geoMenuActive = false;
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
		$scope.personsMenuClicked = function () {
			resetMenus();
			$scope.personsMenuActive = true;
		}
		$scope.addressesMenuClicked = function () {
			resetMenus();
			$scope.addressesMenuActive = true;
		}
		$scope.geoMenuClicked = function () {
			resetMenus();
			$scope.geoMenuActive = trie;
		}

		$scope.toggleNavCollapsed = function () {
			//$scope.navCollapsed = !$scope.navCollapsed;
		}

		$scope.menuStates = {}
		$scope.updateMenu = function (state) {
			for (var i in $scope.menuStates) {
				$scope.menuStates[i] = false;
			}
			$scope.menuStates[state] = true;
		}
	});
