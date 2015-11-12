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
								  MGISCommonsModalForm,
								  IndicatorsPriceIndicatorCRUDService) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy($scope.value, modalScope.item);
					modalScope.currentPage = 1;
					modalScope.itemsPerPage = 15;
					function updateGrid() {
						IndicatorsPriceIndicatorCRUDService.load(modalScope.currentPage, modalScope.itemsPerPage, function (data) {
							modalScope.pager = data;
						});
					}

					modalScope.add = function () {
						IndicatorsPriceIndicatorCRUDService.add(function () {
							updateGrid();
						});
					}
					modalScope.edit = function (id) {
						IndicatorsPriceIndicatorCRUDService.edit(id, function () {
							updateGrid();
						});
					}
					modalScope.remove = function (id) {
						IndicatorsPriceIndicatorCRUDService.remove(id, function () {
							updateGrid();
						});
					}
					updateGrid();

					var modalInstance = MGISCommonsModalForm.edit("app2/indicators/price-indicator/price-indicator-selector-list.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
					modalScope.select = function (indicator) {
						$scope.value = indicator;
						console.log($scope.value);
						modalInstance.close();
					}
				}
			}
		}
	})
	.factory("IndicatorsPriceIndicatorCRUDService", function ($rootScope, IndicatorsPriceIndicatorService, MGISCommonsModalForm) {
		function editItem(item, completeHandler) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			MGISCommonsModalForm.edit("app2/indicators/price-indicator/price-indicator-edit-form.htm", modalScope, function (scope, modalInstance) {
				IndicatorsPriceIndicatorService.save(scope.item).then(function () {
					if (completeHandler) {
						completeHandler(scope.item);
					}
					modalInstance.close();
				})
			});
		}

		return {
			load: function (first, max, completeHandler) {
				IndicatorsPriceIndicatorService.get("", first, max).then(function (pager) {
					if (completeHandler) {
						completeHandler(pager);
					}
				})
			},
			add: function (completeHandler) {
				editItem({id: 0}, completeHandler);
			},
			edit: function (id, completeHandler) {
				IndicatorsPriceIndicatorService.get(id).then(function (item) {
					editItem(item, completeHandler);
				});
			},
			remove: function (id, completeHandler) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					IndicatorsPriceIndicatorService.remove(id).then(function () {
						if (completeHandler) {
							completeHandler();
						}
						modalInstance.close()
					});
				});
			}
		}
	})
	// Template version
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
