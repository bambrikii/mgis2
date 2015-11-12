angular.module("mgis.indicators.technical-indicator", ["ui.bootstrap",
	"mgis.commons",
	"mgis.nc.services",
	"mgis.indicators.services"])
	.directive("technicalIndicator", function (MGISCommonsModalForm) {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/indicators/technical-indicator/technical-indicator-selector.htm",
			controller: function ($scope,
								  $rootScope,
								  MGISCommonsModalForm,
								  NcOKEIService) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy($scope.value, modalScope.item);
					modalScope.refreshAvailableOKEIs = function (name) {
						NcOKEIService.get("", 0, 15, null, name).then(function (okeiPager) {
							modalScope.availableOKEIs = okeiPager.list;
						});
					}
					MGISCommonsModalForm.edit("app2/indicators/technical-indicator/technical-indicator-list.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("IndicatorsTechnicalIndicatorController", function ($scope,
																	$rootScope,
																	MGISCommonsModalForm,
																	IndicatorsTechnicalIndicatorService) {
		$scope.createHandler = function (scope, onPrepare) {
			onPrepare({id: 0});
		};
		$scope.readHandler = function (id, scope, onPrepare) {
			IndicatorsTechnicalIndicatorService.get(id).then(function (item) {
				onPrepare(item);
			});
		};
		$scope.updateHandler = function (item, onComplete) {
			IndicatorsTechnicalIndicatorService.save(item).then(function () {
				onComplete();
			});
		}
		$scope.deleteHandler = function (id, onComplete) {
			IndicatorsTechnicalIndicatorService.remove(id).then(function () {
				onComplete();
			});
		};
		$scope.listHandler = function (filter, first, max, onComplete) {
			IndicatorsTechnicalIndicatorService.get("", first, max, filter.code).then(function (data) {
				onComplete(data);
			});
		};
	})
	.controller("IndicatorsTechnicalIndicatorFormController", function ($scope,
																		NcOKEIService) {
		$scope.refreshAvailableOKEIs = function (name) {
			NcOKEIService.get("", 0, 15, null, name).then(function (okeiPager) {
				$scope.availableOKEIs = okeiPager.list;
			});
		}
	})
