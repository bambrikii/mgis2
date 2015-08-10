angular.module("mgis.terr-zones.zone", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.terr-zones.zone.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("terr-zones.zones", {
				url: "/zones/:terrZoneId",
				templateUrl: "app2/terr-zones/zone/zone-list.htm"
			})
	})
	.controller("TerrZonesZoneController", function ($scope, $rootScope, MGISCommonsModalForm, TerrZonesZoneService) {

		function modifyItem(modalScope) {
			MGISCommonsModalForm.edit(function (modalScope) {

			});
		}

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.zone = {
				id: 0
			}
			modifyItem(modalScope);
		}
		$scope.editItem = function (id) {
			var modalScope = $rootScope.$new();
			TerrZonesZoneService.get(id).then(function (data) {
				modalScope.zone = data;
				modifyItem(modalScope);
			});
		}
		$scope.deleteItem = function (id) {
			MGISCommonsModalForm.confirmRemoval(function () {

			});
		}
	})

