angular.module("mgis.lands.lands", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons", //
	"mgis.lands.services",
	"mgis.nc.services",
	"mgis.common.executive_person.service",
	"mgis.terr-zones.zone.service"
	//"mgis.nc.cache"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("lands.lands", {
				url: "/lands/:landId",
				templateUrl: "app2/lands/land/land-list.htm"
			})
	})
	.controller("LandsLandController", function ($scope, LandsLandService, $rootScope, MGISCommonsModalForm,
												 NcOKATOService,
												 NcOKTMOService,
												 //NcTerritorialZoneService,
												 NcLandAllowedUsageService,
												 NcLandCategoryService,
												 NcLandOwnershipFormService,
												 NcLandRightKindService,
												 NcLandEncumbranceService,
												 CommonExecutivePersonService,
												 LandsInspectionKindService,
												 LandsInspectionTypeService,
												 LandsAvailabilityOfViolationsService,
												 LandsInspectionReasonService,
												 LandsInspectionSubjectService,
												 TerrZonesZoneService) {
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
									NcLandEncumbranceService.get().then(function (landEncumbrances) {
										modalScope.availableLandEncumbrances = landEncumbrances.list;
										NcLandOwnershipFormService.get().then(function (landOwnershipForms) {
											modalScope.availableLandOwnershipForms = landOwnershipForms.list;
											NcLandRightKindService.get().then(function (landRightKinds) {
												modalScope.availableLandRightKinds = landRightKinds.list;
												NcLandCategoryService.get().then(function (availableLandCategories) {
													modalScope.availableLandCategories = availableLandCategories.list;
													NcLandAllowedUsageService.get().then(function (availableLandsAllowedUsage) {
														modalScope.availableLandsAllowedUsage = availableLandsAllowedUsage.list;

														// AddressMunicipalEntities
														modalScope.availableAddressMunicipalEntities = new Array();
														modalScope.refreshAvailableMunicipalEntities = function (name) {
															NcOKTMOService.get("", 0, 15, name).then(function (data) {
																modalScope.availableAddressMunicipalEntities = data.list;
															});
														}
														if (modalScope.land.addressOfMunicipalEntity) {
															NcOKTMOService.get(modalScope.land.addressOfMunicipalEntity.id).then(function (data) {
																modalScope.availableAddressMunicipalEntities.push(data);
															});
														}

														// NearestMunicipalEntities
														modalScope.availableNearestMunicipalEntities = new Array();
														modalScope.refreshAvailableNearestMunicipalEntities = function (name) {
															NcOKTMOService.get("", 0, 15, name).then(function (data) {
																modalScope.availableNearestMunicipalEntities = data.list;
															});
														}
														if (modalScope.land.characteristics && modalScope.land.characteristics.nearestMunicipalEntity) {
															NcOKTMOService.get(modalScope.land.characteristics.nearestMunicipalEntity.id).then(function (data) {
																modalScope.availableNearestMunicipalEntities.push(data);
															});
														}

														// TerritorialZones
														modalScope.availableAllowedUsageByTerritorialZones = new Array();
														modalScope.refreshAvailableAllowedUsageByTerritorialZones = function (name) {
															TerrZonesZoneService.get("", 0, 15, name).then(function (availableTerritorialZones) {
																modalScope.availableAllowedUsageByTerritorialZones = availableTerritorialZones.list;
															});
														}
														if (modalScope.land.allowedUsageByTerritorialZone && modalScope.land.allowedUsageByTerritorialZone.id) {
															TerrZonesZoneService.get(modalScope.land.allowedUsageByTerritorialZone.id).then(function (data) {
																modalScope.availableAllowedUsageByTerritorialZones.push(data);
															});
														}


														modalScope.areas = modalScope.land.landAreas;

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
										});
									});
								});
							});
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
				cadastralNumber: "",
				landAreas: [],
				rights: {},
				characteristics: {}
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
				if (!modalScope.land.landAreas) {
					modalScope.land.landAreas = [];
				}
				editItem(modalScope);
			});
		}

		$scope.deleteItem = function (id) {
			MGISCommonsModalForm.confirmRemoval(function () {
				LandsLandService.remove(id).then(function () {
					updateGrid();
				});
			});
		}

		updateGrid();

		$scope.displayOnTheMap = function () {
			// TODO: display on the map
		}
	})
	.controller("LandsLandAreaController", function ($scope, $rootScope, MGISCommonsModalForm, LandsLandAreaTypeService) {

		var areas = $scope.areas;

		function editItem(area, addFlag) {
			var modalScope = $rootScope.$new();
			modalScope.area = {id: area.id, value: area.value, landAreaType: area.landAreaType};
			LandsLandAreaTypeService.get("", 0, 0).then(function (data) {
				modalScope.availableLandAreaTypes = data.list;
				MGISCommonsModalForm.edit('app2/lands/land/land-area-form.htm', modalScope, function ($scope, $modalInstance) {
					var area2 = $scope.area;
					if (addFlag) {
						areas.push(area2);
					} else {
						area.value = area2.value;
						area.landAreaType = area2.landAreaType;
					}
					$modalInstance.close();
				});
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
