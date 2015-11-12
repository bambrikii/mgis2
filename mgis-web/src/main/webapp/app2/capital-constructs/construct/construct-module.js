angular.module("mgis.capital-constructs.construct", ["ui.router", "ui.bootstrap",
	"mgis.commons",
	"mgis.capital-constructs.construct.service",
	"mgis.property",
	"mgis.capital-constructs.characteristics"
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
																  MGISCommonsModalForm) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		function updateGrid() {
			CapitalConstructsConstructService.get("", $scope.first, $scope.max).then(function (data) {
				$scope.constructsPager = data;
			});
		}

		function editItem(modalScope) {
			CapitalConstructsConstructTypeService.get().then(function (constructTypePager) {
				modalScope.availableTypes = constructTypePager.list;
				MGISCommonsModalForm.edit("app2/capital-constructs/construct/construct-form.htm", modalScope, function (scope, modalInstance) {
					CapitalConstructsConstructService.save(scope.item).then(function () {
						modalInstance.close();
					});
				}, {windowClass: "mgis-capital-construct-modal-form"});
			});
		}

		updateGrid();

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0};
			editItem(modalScope);
		}

		$scope.editItem = function (id) {
			var modalScope = $rootScope.$new();
			CapitalConstructsConstructService.get(id).then(function (data) {
				modalScope.item = data;
				editItem(modalScope);
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

	})
;
