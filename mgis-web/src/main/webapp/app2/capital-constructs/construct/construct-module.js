angular.module("mgis.capital-constructs.construct", ["ui.router", "ui.bootstrap",
	"mgis.commons",
	"mgis.capital-constructs.construct.service",
	"mgis.property",
	"mgis.capital-constructs.characteristics",
	"mgis.capital-constructs.constructive-elements"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("constructs", {
				url: "/capital-constructs/constructs/",
				templateUrl: "app2/capital-constructs/construct/construct-list.htm"
			});
	})
	.controller("CapitalConstructsConstructController", function ($scope,
																  $rootScope,
																  CapitalConstructsConstructService,
																  CapitalConstructEconomicCharacteristicsCRUDService,
																  CapitalConstructTechnicalCharacteristicsCRUDService,
																  CapitalConstructsConstructTypeService,
																  MGISCommonsModalForm,
																  CapitalConstructsConstructiveElementCRUDService) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		function updateGrid() {
			CapitalConstructsConstructService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage).then(function (data) {
				$scope.constructsPager = data;
			});
		}

		function editItem(modalScope, updateHandler) {
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

		updateGrid();

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0};
			editItem(modalScope, function () {
				updateGrid();
			});
		}

		$scope.editItem = function (id) {
			var modalScope = $rootScope.$new();
			CapitalConstructsConstructService.get(id).then(function (data) {
				modalScope.item = data;
				editItem(modalScope, function () {
					updateGrid();
				});
			});
		}
		$scope.deleteItem = function (id) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				CapitalConstructsConstructService.remove(id).then(function () {
					$modalInstance.close();
					updateGrid();
				});
			});
		}

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
