angular.module("mgis.capital-constructs.constructive-element-type", ["ui.bootstrap",
	"mgis.capital-constructs.constructive-element-type.service"
])
	.directive("constructiveElementType", function () {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/capital-constructs/constructive-element-type/constructive-element-type-selector.htm",
			controller: function ($scope,
								  $rootScope,
								  MGISCommonsModalForm,
								  CapitalConstructsConstructiveElementTypeCRUDService) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();

					modalScope.pager0 = {
						currentPage: 1,
						itemsPerPage: 15
					};

					function updateGrid() {
						CapitalConstructsConstructiveElementTypeCRUDService.load((modalScope.pager0.currentPage - 1) * modalScope.pager0.currentPage, modalScope.pager0.itemsPerPage,
							function (constructiveElementTypePager) {
								modalScope.pager = constructiveElementTypePager;
							});
					}

					modalScope.add = function () {
						CapitalConstructsConstructiveElementTypeCRUDService.add(function () {
							updateGrid();
						});
					}
					modalScope.edit = function (id) {
						CapitalConstructsConstructiveElementTypeCRUDService.edit(id, function () {
							updateGrid();
						});
					}
					modalScope.remove = function (id) {
						CapitalConstructsConstructiveElementTypeCRUDService.remove(id, function () {
							updateGrid();
						});
					}
					modalScope.pageChanged = function () {
						updateGrid();
					}

					updateGrid();

					var modalInstance = MGISCommonsModalForm.edit("app2/capital-constructs/constructive-element-type/constructive-element-type-selector-list.htm", modalScope, function (scope, modalInstance) {
						angular.copy(scope.item, $scope.value);
						modalInstance.close();
					});
					modalScope.select = function (indicator) {
						$scope.value = indicator;
						modalInstance.close();
					}
				}

				$scope.clearSelection = function () {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						$scope.value = null;
						modalInstance.close();
					})
				}
			}
		}
	})
	.factory("CapitalConstructsConstructiveElementTypeCRUDService", function ($rootScope,
																			  CapitalConstructsConstructiveElementTypeService,
																			  MGISCommonsModalForm) {
		function editItem(item, completeHandler) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			MGISCommonsModalForm.edit("app2/capital-constructs/constructive-element-type/constructive-element-type-form.htm", modalScope, function (scope, modalInstance) {
				CapitalConstructsConstructiveElementTypeService.save(scope.item).then(function () {
					if (completeHandler) {
						completeHandler(scope.item);
					}
					modalInstance.close();
				})
			});
		}

		return {
			load: function (first, max, completeHandler) {
				CapitalConstructsConstructiveElementTypeService.get("", first, max).then(function (pager) {
					if (completeHandler) {
						completeHandler(pager);
					}
				})
			},
			add: function (completeHandler) {
				editItem({id: 0}, completeHandler);
			},
			edit: function (id, completeHandler) {
				CapitalConstructsConstructiveElementTypeService.get(id).then(function (item) {
					editItem(item, completeHandler);
				});
			},
			remove: function (id, completeHandler) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					CapitalConstructsConstructiveElementTypeService.remove(id).then(function () {
						if (completeHandler) {
							completeHandler();
						}
						modalInstance.close()
					});
				});
			}
		}
	})
;
