angular.module("mgis.lands.lands", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons", //
	"mgis.commons.forms", //
	"mgis.lands.services",
	"mgis.nc.services",
	"mgis.commons.executive_person.service",
	"mgis.terr-zones.zone.service",
	"mgis.persons.person",
	"mgis.lands.land.map",
	"mgis.isogd.document.selector",
	"mgis.address.selector",
	"mgis.geo.spatial.data",
	"mgis.address",
	"mgis.reports.report"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("lands.lands", {
				url: "/lands/:landId",
				templateUrl: "app2/lands/land/land-list.htm"
			})
		;
	}) //
	.factory("LandsLandCRUDService", function ($rootScope, LandsLandService,
											   MGISCommonsModalForm,
											   NcOKATOService,
											   NcOKTMOService,
											   NcLandAllowedUsageService,
											   NcLandCategoryService,
											   NcOKFSService,
											   NcLandEncumbranceService,
											   CommonExecutivePersonService,
											   LandsInspectionKindService,
											   LandsInspectionTypeService,
											   LandsAvailabilityOfViolationsService,
											   LandsInspectionReasonService,
											   LandsInspectionSubjectService,
											   TerrZonesZoneService,
											   LandsLandConstants) {
		function editItem0(modalScope, updateGrid) {
			modalScope.LAND_CADASTRAL_NUMBER = LandsLandConstants.LAND_CADASTRAL_NUMBER;
			LandsInspectionKindService.get().then(function (inspectionKinds) {
				modalScope.availableInspectionKinds = inspectionKinds.list;
				LandsInspectionTypeService.get().then(function (inspectionTypes) {
					modalScope.availableInspectionTypes = inspectionTypes.list;
					LandsAvailabilityOfViolationsService.get().then(function (availabilityOfViolations) {
						modalScope.availableAvailabilityOfViolations = availabilityOfViolations.list;
						LandsInspectionReasonService.get().then(function (inspectionReasons) {
							modalScope.availableInspectionReasons = inspectionReasons.list;
							LandsInspectionSubjectService.get().then(function (inspectionSubjects) {
								modalScope.availableInspectionSubjects = inspectionSubjects.list;
								CommonExecutivePersonService.get().then(function (executivePersons) {
									modalScope.availableExecutivePersons = executivePersons.list;
									NcLandCategoryService.get().then(function (availableLandCategories) {
										modalScope.availableLandCategories = availableLandCategories.list;
										NcLandAllowedUsageService.get().then(function (availableLandsAllowedUsage) {
											modalScope.availableLandsAllowedUsage = availableLandsAllowedUsage.list;

											// AddressMunicipalEntities
											modalScope.availableAddressMunicipalEntities = new Array();
											modalScope.refreshAvailableMunicipalEntities = function (name) {
												NcOKTMOService.get("", 0, 15, null, name).then(function (data) {
													modalScope.availableAddressMunicipalEntities = data.list;
												});
											}

											// NearestMunicipalEntities
											modalScope.availableNearestMunicipalEntities = new Array();
											modalScope.refreshAvailableNearestMunicipalEntities = function (name) {
												NcOKTMOService.get("", 0, 15, null, name).then(function (data) {
													modalScope.availableNearestMunicipalEntities = data.list;
												});
											}

											// TerritorialZones
											modalScope.availableAllowedUsageByTerritorialZones = new Array();
											modalScope.refreshAvailableAllowedUsageByTerritorialZones = function (name) {
												TerrZonesZoneService.get("", 0, 15, name).then(function (availableTerritorialZones) {
													modalScope.availableAllowedUsageByTerritorialZones = availableTerritorialZones.list;
												});
											}

											modalScope.areas = modalScope.land.landAreas;

											MGISCommonsModalForm.edit("app2/lands/land/land-form.htm", modalScope, function (scope, $modalInstance) {
												LandsLandService.save(scope.land).then(function (data) {
													$modalInstance.close();
													updateGrid();
												});
											}, {windowClass: "mgis-land-modal-form"});
										});
									});
								});
							});
						});
					});
				});
			});
		}

		function editItem(id, updateGrid) {
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
				if (!modalScope.land.landAreas) {
					modalScope.land.landAreas = [];
				}
				editItem0(modalScope, updateGrid);
			});
		}

		function addItem(updateGrid) {
			var modalScope = $rootScope.$new();
			modalScope.land = {
				id: 0,
				cadastralNumber: "",
				landAreas: [],
				rights: {},
				characteristics: {}
			}
			editItem0(modalScope, updateGrid);
		}

		function deleteItem(id, updateGrid) {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				LandsLandService.remove(id).then(function () {
					modalInstance.close();
					updateGrid();
				});
			});
		}

		return {
			addItem: addItem,
			editItem: editItem,
			deleteItem: deleteItem
		}

	}) //
	.controller("LandsLandController", function ($scope,
												 $state,
												 $rootScope,
												 $filter,
												 LandsLandService,
												 LandsLandCRUDService,
												 LandsLandSelectorService,
												 CommonsPagerManager,
												 MGISCommonsModalForm) {
		$scope.cadastralNumber = "";
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		$scope.pagerMaxSize = CommonsPagerManager.maxSize();
		$scope.searchText = "";
		$scope.selectedIds = new Array();

		function updateGrid() {
			var ids = LandsLandSelectorService.ids();
			LandsLandService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage,
				$scope.cadastralNumber,
				ids
			).then(function (data) {
					$scope.landsPager = data;
					$scope.selectedIds.splice(0, $scope.selectedIds.length);
					for (var i in data.list) {
						var land = data.list[i];
						if (ids.indexOf(land.id) > -1) {
							$scope.selectedIds[land.id] = {checked: true};
						}
					}
				}
			);
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		// editItem

		$scope.find = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			LandsLandCRUDService.addItem(updateGrid);
		}

		$scope.editItem = function (id) {
			LandsLandCRUDService.editItem(id, updateGrid);
		}

		$scope.deleteItem = function (id) {
			LandsLandCRUDService.deleteItem(id, updateGrid);
		}

		$scope.deleteSelectedItems = function () {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				var ids = LandsLandSelectorService.ids();
				LandsLandService.removeSelected(ids).then(function (data) {
					LandsLandSelectorService.removeByIds(data.ids);
					updateGrid();
					modalInstance.close();
				});
			});
		}

		updateGrid();

		$scope.displayOnTheMap = function () {
			$state.go("^.maps");
		}

		$scope.checkLandSelected = function (checked, item) {
			var land = {id: item.id, cadastralnumber: item.cadastralNumber}
			if (checked) {
				LandsLandSelectorService.add(land);
				$scope.selectedIds[item.id] = {checked: true};
			} else {
				LandsLandSelectorService.remove(land);
				delete $scope.selectedIds[item.id];
			}
			var ids = LandsLandSelectorService.ids();
			for (var id in ids) {
				$scope.selectedIds[id] = {checked: true};
			}
			//updateGrid();
		}
		$scope.selectAll = function () {
			for (var i in $scope.landsPager.list) {
				var id = $scope.landsPager.list[i].id
				LandsLandSelectorService.add({id: id});
				$scope.selectedIds[id] = {checked: true};
			}
		}
		$scope.deselectAll = function () {
			LandsLandSelectorService.removeByIds(Object.keys($scope.selectedIds));
			$scope.selectedIds.splice(0, $scope.selectedIds.length);
		}

	})
	.controller("LandsLandAreaController", function ($scope, $rootScope, MGISCommonsModalForm, LandsLandAreaTypeService) {

		var areas = $scope.areas;

		function editItem(area, addFlag) {
			var modalScope = $rootScope.$new();
			modalScope.NUMBER_FORMAT = /^\d+([\.\,]\d+)?$/
			modalScope.area = {id: area.id, value: area.value, landAreaType: area.landAreaType};
			LandsLandAreaTypeService.get("", 0, 0).then(function (data) {
				modalScope.availableLandAreaTypes = data.list;
				MGISCommonsModalForm.edit('app2/lands/land/land-area-form.htm', modalScope, function ($scope, $modalInstance) {
						var area2 = $scope.area;

						function normalizeNumber(value) {
							return value ? value.replace(/\,/, ".") : value;
						}

						if (addFlag) {
							area2.value = normalizeNumber(area2.value);
							areas.push(area2);
						} else {
							area.value = normalizeNumber(area2.value);
							area.landAreaType = area2.landAreaType;
						}
						$modalInstance.close();
					},
					{windowClass: "mgis-land-area-modal-form"});
			});
		}

		$scope.addLandArea = function () {
			editItem({id: 0}, true);
		}
		$scope.editLandArea = function (area) {
			editItem(area);
		}
		$scope.removeLandArea = function (area) {
			if (MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
					var i = areas.indexOf(area);
					if (i > -1) {
						areas.splice(i, 1);
					}
					$modalInstance.close();
				}));
		}
	})
;
