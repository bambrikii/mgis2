angular.module("mgis.terr-zones.zone", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.terr-zones.zone.service",
	"mgis.nc.services"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("terr-zones.zones", {
				url: "/zones/:terrZoneId",
				templateUrl: "app2/terr-zones/zone/zone-list.htm"
			})
	})
	.controller("TerrZonesZoneController", function ($scope, $rootScope,
													 MGISCommonsModalForm,
													 TerrZonesZoneService,
													 NcOKTMOService,
													 NcTerritorialZoneTypeService,
													 NcLandAllowedUsageService) {
		$scope.first = 0;
		$scope.max = 15;
		function updateGrid() {
			TerrZonesZoneService.get("", $scope.first, $scope.max).then(function (data) {
				$scope.list = data.list;
				$scope.first = data.first;
				$scope.max = data.max;
			});
		}

		function editItem(modalScope) {
			NcOKTMOService.get("", 0, 15, name).then(function (admTerrEntities) {
				modalScope.availableAdministrativeTerritorialEntities = admTerrEntities.list;
				NcTerritorialZoneTypeService.get("", 0, 15).then(function (zoneTypes) {
					modalScope.availableZoneTypes = zoneTypes.list;
					NcLandAllowedUsageService.get("", 0, 15, name).then(function (availableAllowedUsageKinds) {
						modalScope.availableAllowedUsageKinds = availableAllowedUsageKinds.list;

						// AdministrativeTerritorialEntities
						modalScope.refreshAvailableAdministrativeTerritorialEntities = function (name) {
							NcOKTMOService.get("", 0, 15, name).then(function (admTerrEntities) {
								modalScope.availableAdministrativeTerritorialEntities = admTerrEntities.list;
							});
						}

						MGISCommonsModalForm.edit("app2/terr-zones/zone/zone-form.htm", modalScope, function (scope, $modalInstance) {
							TerrZonesZoneService.save(scope.zone).then(function (data) {
								$modalInstance.close();
								updateGrid();
							})
						}, {
							windowClass: "mgis-terr-zone-modal-form"
						});
					});
				});
			});
		}

		updateGrid();

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.zone = {
				id: 0
			}
			editItem(modalScope);
		}
		$scope.editItem = function (id) {
			var modalScope = $rootScope.$new();
			TerrZonesZoneService.get(id).then(function (data) {
				modalScope.zone = data;
				editItem(modalScope);
			});
		}
		$scope.deleteItem = function (id) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				TerrZonesZoneService.remove(id).then(function () {
					$modalInstance.close();
					updateGrid();
				});
			});
		}
	})

