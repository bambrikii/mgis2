angular.module("mgis.indicators.price-indicator", ["ui.bootstrap",
	"mgis.commons",
	"mgis.nc.services",
	"mgis.indicators.services"])
	.directive("priceIndicator", function () {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/indicators/price-indicator/price-indicator-selector.htm",
			controller: function ($scope,
								  $rootScope,
								  MGISCommonsModalForm) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy($scope.value, modalScope.item);

					MGISCommonsModalForm.edit("app2/indicators/price-indicator/price-indicator-list.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("IndicatorsPriceIndicatorController", function ($scope,
																$rootScope,
																MGISCommonsModalForm,
																IndicatorsPriceIndicatorService) {
		$scope.createHandler = function (scope, onPrepare) {
			onPrepare({id: 0});
		};
		$scope.readHandler = function (id, scope, onPrepare) {
			IndicatorsPriceIndicatorService.get(id).then(function (item) {
				onPrepare(item);
			});
		};
		$scope.updateHandler = function (item, onComplete) {
			IndicatorsPriceIndicatorService.save(item).then(function () {
				onComplete();
			});
		}
		$scope.deleteHandler = function (id, onComplete) {
			IndicatorsPriceIndicatorService.remove(id).then(function () {
				onComplete();
			});
		};
		$scope.listHandler = function (filter, first, max, onComplete) {
			IndicatorsPriceIndicatorService.get("", first, max, filter.code).then(function (data) {
				onComplete(data);
			});
		};
	})
	.controller("IndicatorsPriceIndicatorFormController", function ($scope,
																	NcOKEIService) {
		$scope.refreshAvailableOKEIs = function (name) {
			NcOKEIService.get("", 0, 15, null, name).then(function (okeiPager) {
				$scope.availableOKEIs = okeiPager.list;
			});
		}
	})
;
