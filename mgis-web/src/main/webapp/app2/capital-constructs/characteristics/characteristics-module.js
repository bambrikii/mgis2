angular.module("mgis.capital-constructs.characteristics", ["ui.bootstrap",
	"mgis.commons",
	"mgis.nc.services",
	"mgis.capital-constructs.characteristics.services",
	"mgis.capital-constructs.construct.service",
	"mgis.indicators"
])
	.factory("CapitalConstructEconomicCharacteristicsCRUDService", function ($rootScope,
																			 MGISCommonsModalForm,
																			 NcOKOFService,
																			 CapitalConstructCharacteristicsAmortizationGroup) {
		function editItem(item, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			CapitalConstructCharacteristicsAmortizationGroup.get("", 0, 0).then(function (amortizationGroupPager) {
				modalScope.availableAmortizationGroups = amortizationGroupPager.list
				modalScope.refreshAvailableOKOFs = function (name) {
					NcOKOFService.get("", 0, 15, null, name, [11, 12, 13]).then(function (okofPager) {
						modalScope.availableOKOFs = okofPager.list;
					});
				}
				MGISCommonsModalForm.edit("app2/capital-constructs/characteristics/economic-characteristic-form.htm", modalScope, function (scope, modalInstance) {
					angular.copy(scope.item, item)
					if (updateFunction) {
						updateFunction(scope.item);
					}
					modalInstance.close();
				});
			});
		}

		return {
			add: function (construct) {
				editItem({id: 0}, function (item) {
					if (!construct.characteristics) {
						construct.characteristics = {};
					}
					if (!construct.characteristics.economicCharacteristics) {
						construct.characteristics.economicCharacteristics = new Array();
					}
					construct.characteristics.economicCharacteristics.push(item);
				});
			},
			edit: function (characteristic) {
				editItem(characteristic);
			},
			remove: function (construct, item) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					for (var i in construct.characteristics.economicCharacteristics) {
						var char = construct.characteristics.economicCharacteristics[i];
						if (char == item) {
							construct.characteristics.economicCharacteristics.splice(i, 1);
						}
					}
					modalInstance.close();
				});
			}
		}
	})
	.factory("CapitalConstructTechnicalCharacteristicsCRUDService", function ($rootScope,
																			  $filter,
																			  MGISCommonsModalForm,
																			  CapitalConstructsConstructTypeService) {
		function editItem(item, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			CapitalConstructsConstructTypeService.get().then(function (constructTypesPager) {
				modalScope.availableConstructTypes = constructTypesPager.list;
				MGISCommonsModalForm.edit("app2/capital-constructs/characteristics/technical-characteristic-form.htm", modalScope, function (scope, modalInstance) {
					angular.copy(scope.item, item);
					if (updateFunction) {
						updateFunction(item);
					}
					modalInstance.close();
				});
			});
		}

		return {
			add: function (construct) {
				editItem({id: 0}, function (item) {
					if (!construct.characteristics) {
						construct.characteristics = {};
					}
					if (!construct.characteristics.technicalCharacteristics) {
						construct.characteristics.technicalCharacteristics = new Array();
					}
					construct.characteristics.technicalCharacteristics.push(item);
				});
			},
			edit: function (characteristic) {
				editItem(characteristic);
			},
			remove: function (construct, characteristic) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					for (var i in construct.characteristics.technicalCharacteristics) {
						var char = construct.technicalCharacteristics[i];
						if (char == characteristic) {
							construct.technicalCharacteristics.splice(i, 1);
						}
					}
					modalInstance.close();
				});
			}
		}
	})
;
