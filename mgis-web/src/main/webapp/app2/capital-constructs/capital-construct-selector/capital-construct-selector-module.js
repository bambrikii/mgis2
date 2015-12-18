angular.module("mgis.capital-constructs.construct-selector", ["ui.bootstrap",
	"mgis.commons",
	"mgis.commons.forms"
])
	.directive("capitalConstructsSelector", function () {
		return {
			restrict: "E",
			scope: {
				constructs: "="
			},
			templateUrl: "app2/capital-constructs/capital-construct-selector/capital-constructs-selector-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm, CapitalConstructsConstructCRUDService) {
				$scope.editItem = function (id) {
					CapitalConstructsConstructCRUDService.editItem(id, function () {
						CapitalConstructsConstructCRUDService.reloadItemInList(id, $scope.constructs);
					});
				}
				$scope.select = function () {
					var modalScope = $rootScope.$new();
					modalScope.selectedItems = new Array();
					for (var i in $scope.constructs) {
						var construct = $scope.constructs[i];
						modalScope.selectedItems.push(construct);
					}
					MGISCommonsModalForm.edit("app2/capital-constructs/capital-construct-selector/capital-construct-selector-search.htm", modalScope, function (scope, modalInstance) {
						$scope.constructs = scope.selectedItems;
						modalInstance.close();
					}, {windowClass: "mgis-capital-construct-modal-form"});
				}
				$scope.deselect = function (constructId) {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						for (var i in $scope.constructs) {
							if ($scope.constructs[i].id == constructId) {
								$scope.constructs.splice(i, 1);
							}
						}
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("CapitalConstructsConstructSelectorController", function ($scope, CapitalConstructsConstructService, CapitalConstructsConstructCRUDService, MGISCommonsModalForm, CommonsPagerManager) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		function updateList() {
			CapitalConstructsConstructService.get(null, CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage,
				null,
				$scope.cadastralNumber
			).then(function (data) {
					$scope.constructsSelectorPager = data;
				});
		}

		$scope.addItem = function () {
			CapitalConstructsConstructCRUDService.addItem(updateList);
		}
		$scope.editItem = function (id) {
			CapitalConstructsConstructCRUDService.editItem(id, function () {
				updateList();
				CapitalConstructsConstructCRUDService.reloadItemInList(id, $scope.selectedItems);
			});
		}
		$scope.removeItem = function (id) {
			CapitalConstructsConstructCRUDService.removeItem(id, updateList);
		}
		$scope.pageChanged = function () {
			updateList();
		}
		$scope.find = function (cadastralNumber) {
			updateList();
		}
		$scope.selectItem = function (construct) {
			for (var i in $scope.selectedItems) {
				if ($scope.selectedItems[i].id == construct.id) {
					return;
				}
			}
			$scope.selectedItems.push(construct);
		}
		$scope.deselect = function (constructId) {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				for (var i in $scope.selectedItems) {
					if ($scope.selectedItems[i].id == constructId) {
						$scope.selectedItems.splice(i, 1);
					}
				}
				modalInstance.close();
			});
		}

		updateList();
	})
;
