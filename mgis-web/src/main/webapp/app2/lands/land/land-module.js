angular.module("mgis.lands.lands", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons", //
	"mgis.lands.lands.service",

	"mgis.nc.oktmo.service",
	"mgis.nc.okato.service",
	"mgis.nc.territorial_zone.service",
	"mgis.nc.land_allowed_usage.service",
	"mgis.nc.land_category.service",
	"mgis.nc.land_ownership_form.service",
	"mgis.nc.land_right_kind.service",
	"mgis.nc.land_encumbrance.service",

	"mgis.common.executive_person.service",
	"mgis.lands.inspection_kind.service",
	"mgis.lands.inspection_type.service",
	"mgis.lands.inspection_reason.service",
	"mgis.lands.inspection_subject.service",
	"mgis.lands.availability_of_violations.service"
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
												 NcTerritorialZoneService,
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
												 LandsInspectionSubjectService) {
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
													NcTerritorialZoneService.get().then(function (availableTerritorialZones) {
														modalScope.availableTerritorialZones = availableTerritorialZones.list;
														NcLandAllowedUsageService.get().then(function (availableLandsAllowedUsage) {
															modalScope.availableLandsAllowedUsage = availableLandsAllowedUsage.list;

															//
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

															//
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
	}
)
;
