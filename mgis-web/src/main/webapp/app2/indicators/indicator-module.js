angular.module("mgis.indicators", ["ui.bootstrap",
		"mgis.commons",
		"mgis.nc.services"
	])
	.directive("priceIndicator", function () {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/indicators/price-indicator-selector.htm",
			controller: function ($scope,
								  $rootScope,
								  MGISCommonsModalForm,
								  NcOKEIService) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy($scope.value, modalScope.item);
					modalScope.refreshAvailableOKEIs = function (name) {
						NcOKEIService.get("", 0, 15, name).then(function (okeiPager) {
							modalScope.availableOKEIs = okeiPager.list;
						});
					}
					MGISCommonsModalForm.edit("app2/indicator/price-indicator-component.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
				}
			}
		}
	})
	.directive("technicalIndicator", function (MGISCommonsModalForm) {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/indicators/technical-indicator-selector.htm",
			controller: function ($scope,
								  $rootScope,
								  MGISCommonsModalForm,
								  NcOKEIService) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy($scope.value, modalScope.item);
					modalScope.refreshAvailableOKEIs = function (name) {
						NcOKEIService.get("", 0, 15, name).then(function (okeiPager) {
							modalScope.availableOKEIs = okeiPager.list;
						});
					}
					MGISCommonsModalForm.edit("app2/indicator/price-indicator-component.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
				}
			}
		}
	})
;
