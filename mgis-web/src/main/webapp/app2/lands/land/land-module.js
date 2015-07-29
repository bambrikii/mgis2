angular.module("mgis.lands.lands", ["ui.router", "ui.bootstrap", //
	"mgis.commons", //
	"mgis.lands.lands.service",

	"mgis.nc.oktmo.service",
	"mgis.nc.okato.service",
	"mgis.nc.territorial_zone.service",
	"mgis.nc.land_allowed_usage.service",
	"mgis.nc.land_category.service"
	//"mgis.nc.cache"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("lands.lands", {
				url: "/lands/:landId",
				templateUrl: "app2/lands/land/land-list.htm"
			})
	})
	.controller("LandsLandController", function ($scope, LandsLandService, $rootScope, MGISCommonsModalForm, NcOKATOService, NcOKTMOService, NcTerritorialZoneService, NcLandAllowedUsageService, NcLandCategoryService) {
		$scope.cadastralNumber = "";
		$scope.first = 0;
		$scope.max = 15;
		function updateGrid() {
			LandsLandService.get("", $scope.first, $scope.max, $scope.cadastralNumber).then(function (data) {
				$scope.list = data.list;
				$scope.first = data.first;
				$scope.max = data.max;
			});
		}

		function editItem(modalScope) {
			NcLandCategoryService.get().then(function (availableLandCategories) {
				modalScope.availableLandCategories = availableLandCategories.list;
				NcTerritorialZoneService.get().then(function (availableTerritorialZones) {
					modalScope.availableTerritorialZones = availableTerritorialZones.list;
					NcLandAllowedUsageService.get().then(function (availableLandsAllowedUsage) {
						modalScope.availableLandsAllowedUsage = availableLandsAllowedUsage.list;
						MGISCommonsModalForm.edit("app2/lands/land/land-form.htm", modalScope, function (scope, $modalInstance) {
							LandsLandService.save(scope.land).then(function (data) {
								$modalInstance.close();
								updateGrid();
							});
						}, {
							windowClass: "mgis-land-modal-form"
						});
					});
				});
			});
		}

		$scope.find = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.land = {
				id: 0,
				cadastralNumber: ""
			}
			editItem(modalScope);
		}

		$scope.editItem = function (id) {
			LandsLandService.get(id).then(function (data) {
				var modalScope = $rootScope.$new();
				if (!data.landCategory) {
					data.landCategory = {}
				}
				if (!data.allowedUsageByDictionary) {
					data.allowedUsageByDictionary = {}
				}
				if (!data.allowedUsageByTerritorialZone) {
					data.allowedUsageByTerritorialZone = {}
				}
				modalScope.land = data;
				editItem(modalScope);
			});
		}

		$scope.deleteItem = function (id) {
			LandsLandService.remove(id).then(function () {
				updateGrid();
			})
		}

		updateGrid();

		$scope.displayOnTheMap = function () {
			// TODO: display on the map
		}
	})
;
