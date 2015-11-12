angular.module("mgis.indicators.technical-indicator", ["ui.bootstrap",
	"mgis.commons",
	"mgis.nc.services",
	"mgis.indicators.services"])
	.directive("technicalIndicator", function () {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/indicators/technical-indicator/technical-indicator-selector.htm",
			controller: function ($scope,
								  $rootScope,
								  MGISCommonsModalForm,
								  NcOKEIService,
								  IndicatorsTechnicalIndicatorCRUDService) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy($scope.value, modalScope.item);
					modalScope.currentPage = 1;
					modalScope.itemsPerPage = 15;
					function updateGrid() {
						IndicatorsTechnicalIndicatorCRUDService.load(modalScope.currentPage, modalScope.itemsPerPage, function (data) {
							modalScope.pager = data;
						});
					}

					modalScope.add = function () {
						IndicatorsTechnicalIndicatorCRUDService.add(function () {
							updateGrid();
						});
					}
					modalScope.edit = function (id) {
						IndicatorsTechnicalIndicatorCRUDService.edit(id, function () {
							updateGrid();
						});
					}
					modalScope.remove = function (id) {
						IndicatorsTechnicalIndicatorCRUDService.remove(id, function () {
							updateGrid();
						});
					}
					updateGrid();
					var modalInstance = MGISCommonsModalForm.edit("app2/indicators/technical-indicator/technical-indicator-selector-list.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
					modalScope.select = function (indicator) {
						$scope.value = indicator;
						modalInstance.close();
					}
				}
			}
		}
	})
	.factory("IndicatorsTechnicalIndicatorCRUDService", function ($rootScope, IndicatorsTechnicalIndicatorService, MGISCommonsModalForm) {
		function editItem(item, completeHandler) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			MGISCommonsModalForm.edit("app2/indicators/technical-indicator/technical-indicator-edit-form.htm", modalScope, function (scope, modalInstance) {
				IndicatorsTechnicalIndicatorService.save(scope.item).then(function () {
					if (completeHandler) {
						completeHandler(scope.item);
					}
					modalInstance.close();
				})
			});
		}

		return {
			load: function (first, max, completeHandler) {
				IndicatorsTechnicalIndicatorService.get("", first, max).then(function (pager) {
					if (completeHandler) {
						completeHandler(pager);
					}
				})
			},
			add: function (completeHandler) {
				editItem({id: 0}, completeHandler);
			},
			edit: function (id, completeHandler) {
				IndicatorsTechnicalIndicatorService.get(id).then(function (item) {
					editItem(item, completeHandler);
				});
			},
			remove: function (id, completeHandler) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					IndicatorsTechnicalIndicatorService.remove(id).then(function () {
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
