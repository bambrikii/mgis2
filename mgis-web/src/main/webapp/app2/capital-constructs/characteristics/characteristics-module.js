angular.module("mgis.capital_constructs.characteristics", ["ui.bootstrap",
	"mgis.commons",
	"mgis.nc.services",
	"mgis.capital_constructs.characteristics.services"
])
	.factory("CapitalConstructEconomicCharacteristicsCRUDService", function ($rootScope,
																			 MGISCommonsModalForm,
																			 NcPriceTypeService,
																			 NcOKOFService,
																			 NcOKEIService,
																			 CapitalConstructCharacteristicsAmortizationGroup) {
		function editItem(item, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			NcPriceTypeService.get("", 0, 0).then(function (priceTypePager) {
				modalScope.availablePriceTypes = priceTypePager.list;
				CapitalConstructCharacteristicsAmortizationGroup.get("", 0, 0).then(function (amortizationGroupPager) {
					modalScope.availableAmortizationGroups = amortizationGroupPager.list
					modalScope.refreshAvailableOKOFs = function (name) {
						NcOKOFService.get("", 0, 15, null, name).then(function (okofPager) {
							modalScope.availableOKOFs = okofPager.list;
						});
					}
					modalScope.refreshAvailableOKEIs = function (name) {
						NcOKEIService.get("", 0, 15, name).then(function (okeiPager) {
							modalScope.availableOKEIs = okeiPager.list;
						});
					}

					MGISCommonsModalForm.edit("app2/capital-constructs/characteristics/economic-characteristic-form.htm", modalScope, function (scope, modalInstance) {
						if (updateFunction) {
							updateFunction(scope.item);
						}
						modalInstance.close();
					});
				});
			});
		}

		function removeItem(construct, item) {
			for (var i in construct.economicCharacteristics) {
				var char = construct.economicCharacteristics[i];
				if (char == item) {
					construct.economicCharacteristics.splice(i, 1);
				}
			}
		}

		return {
			add: function (construct) {
				editItem({id: 0}, function (item) {
					construct.economicCharacteristics.push(item);
				});
			},
			edit: function (item) {
				editItem(item);
			},
			remove: function (construct, item) {
				removeItem(construct, item);
			}
		}
	})
	.factory("CapitalConstructTechnicalCharacteristicsCRUDService", function ($rootScope, MGISCommonsModalForm) {

		function editItem(item, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = item;
			MGISCommonsModalForm.edit("app2/capital-constructs/characteristics/technical-characteristic-form.htm", modalScope, function (scope, modalInstance) {
				if (updateFunction) {
					updateFunction(scope.item);
				}
				modalInstance.close();
			});
		}

		function removeItem(construct, item) {
			for (var i in construct.technicalCharacteristics) {
				var char = construct.technicalCharacteristics[i];
				if (char == item) {
					construct.technicalCharacteristics.splice(i, 1);
				}
			}
		}

		return {
			add: function (construct) {
				editItem({id: 0}, function (item) {
					construct.technicalCharacteristics.push(item);
				});
			},
			edit: function (item) {
				editItem(item);
			},
			remove: function (construct, item) {
				removeItem(construct, item);
			}
		}
	})
;
