angular.module("mgis.capital-constructs.construct", ["ui.router", "ui.bootstrap",
	"mgis.commons",
	"mgis.commons.forms",
	"mgis.capital-constructs.construct.service",
	"mgis.property",
	"mgis.capital-constructs.characteristics",
	"mgis.capital-constructs.constructive-elements",
	"mgis.nc.services",
	"mgis.reports.report"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("constructs", {
				url: "/capital-constructs/constructs/",
				templateUrl: "app2/capital-constructs/construct/construct-list.htm"
			});
	})
	.factory("CapitalConstructsConstructCRUDService", function ($rootScope, CapitalConstructsConstructService, CapitalConstructsConstructTypeService, NcOKTMOService, MGISCommonsModalForm) {
		function editItem0(item, updateHandler) {
			var modalScope = $rootScope.$new();
			modalScope.item = item;
			// AddressMunicipalEntities
			modalScope.availableMunicipalEntities = new Array();
			modalScope.refreshAvailableMunicipalEntities = function (name) {
				NcOKTMOService.get("", 0, 15, null, name).then(function (data) {
					modalScope.availableMunicipalEntities = data.list;
				});
			}
			CapitalConstructsConstructTypeService.get().then(function (constructTypePager) {
				modalScope.availableTypes = constructTypePager.list;
				MGISCommonsModalForm.edit("app2/capital-constructs/construct/construct-form.htm", modalScope, function (scope, modalInstance) {
					CapitalConstructsConstructService.save(scope.item).then(function () {
						if (updateHandler) {
							updateHandler();
						}
						modalInstance.close();
					});
				}, {windowClass: "mgis-capital-construct-modal-form"});
			});
		}


		function addItem(updateHandler) {
			editItem0({id: 0}, updateHandler);
		}

		function editItem(id, updateHandler) {
			CapitalConstructsConstructService.get(id).then(function (data) {
				editItem0(data, updateHandler);
			});
		}

		function removeItem(id, updateHandler) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				CapitalConstructsConstructService.remove(id).then(function () {
					$modalInstance.close();
					if (updateHandler) {
						updateHandler();
					}
				});
			});

		}

		function reloadItemInList(id, list) {
			CapitalConstructsConstructService.get(id).then(function (data) {
				for (var i in list) {
					var item = list[i];
					if (item.id == data.id) {
						list[i] = data;
					}
				}
			});
		}

		return {
			addItem: addItem,
			editItem: editItem,
			removeItem: removeItem,
			reloadItemInList: reloadItemInList
		}
	})
	.controller("CapitalConstructsConstructListController", function ($scope,
																	  $rootScope,
																	  CapitalConstructsConstructService,
																	  CapitalConstructEconomicCharacteristicsCRUDService,
																	  CapitalConstructTechnicalCharacteristicsCRUDService,
																	  CapitalConstructsConstructTypeService,
																	  MGISCommonsModalForm,
																	  CapitalConstructsConstructiveElementCRUDService,
																	  NcOKTMOService,
																	  CommonsPagerManager,
																	  CapitalConstructsConstructCRUDService) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		function updateGrid() {
			CapitalConstructsConstructService.get("", CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage).then(function (data) {
				$scope.constructsPager = data;
			});
		}

		updateGrid();

		$scope.addItem = function () {
			CapitalConstructsConstructCRUDService.addItem(updateGrid);
		}

		$scope.editItem = function (id) {
			CapitalConstructsConstructCRUDService.editItem(id, updateGrid);
		}
		$scope.deleteItem = function (id) {
			CapitalConstructsConstructCRUDService.removeItem(id, updateGrid);
		}

	})
	.controller("CapitalConstructsConstructController", function ($scope,
																  CapitalConstructEconomicCharacteristicsCRUDService,
																  CapitalConstructTechnicalCharacteristicsCRUDService,
																  CapitalConstructsConstructiveElementCRUDService) {

		// Economic Characteristics
		$scope.addEconomicCharacteristic = function (item) {
			CapitalConstructEconomicCharacteristicsCRUDService.add(item);
		}
		$scope.editEconomicCharacteristic = function (char) {
			CapitalConstructEconomicCharacteristicsCRUDService.edit(char);
		}
		$scope.removeEconomicCharacteristic = function (item, char) {
			CapitalConstructEconomicCharacteristicsCRUDService.remove(item, char);
		}

		// Technical Characteristics
		$scope.addTechnicalCharacteristic = function (item) {
			CapitalConstructTechnicalCharacteristicsCRUDService.add(item);
		}
		$scope.editTechnicalCharacteristic = function (char) {
			CapitalConstructTechnicalCharacteristicsCRUDService.edit(char);
		}
		$scope.removeTechnicalCharacteristic = function (item, char) {
			CapitalConstructTechnicalCharacteristicsCRUDService.remove(item, char);
		}

		// Constructive Elements
		$scope.addConstructiveElement = function (item) {
			CapitalConstructsConstructiveElementCRUDService.add(item);
		}
		$scope.editConstructiveElement = function (element) {
			CapitalConstructsConstructiveElementCRUDService.edit(element);
		}
		$scope.removeConstructiveElement = function (item, element) {
			CapitalConstructsConstructiveElementCRUDService.remove(item, element);
		}
	})
;
