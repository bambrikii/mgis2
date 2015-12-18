angular.module("mgis.lands.land-selector", ["ui.bootstrap",
	"mgis.lands.lands",
	"mgis.lands.services",
	"mgis.commons"
])
	.directive("landsSelector", function () {
		return {
			restrict: "E",
			scope: {
				lands: "="
			},
			templateUrl: "app2/lands/land-selector/lands-selector-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm, LandsLandCRUDService) {
				$scope.select = function () {
					var modalScope = $rootScope.$new();
					modalScope.selectedItems = new Array();
					for (var i in $scope.lands) {
						var land = $scope.lands[i];
						modalScope.selectedItems.push(land);
					}
					MGISCommonsModalForm.edit("app2/lands/land-selector/land-selector-search.htm", modalScope, function (scope, modalInstance) {
						$scope.lands = scope.selectedItems;
						modalInstance.close();
					}, {windowClass: "mgis-land-modal-form"});

				}
				$scope.editItem = function (id) {
					LandsLandCRUDService.editItem(id, function () {
						LandsLandCRUDService.reloadItemInList(id, $scope.lands);
					});
				}
				$scope.deselect = function (landId) {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						for (var i in $scope.lands) {
							if ($scope.lands[i].id == landId) {
								$scope.lands.splice(i, 1);
							}
						}
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("LandsLandSelectorController", function ($scope, LandsLandService, LandsLandCRUDService, CommonsPagerManager, MGISCommonsModalForm) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		function updateList() {
			LandsLandService.get(null, CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage, $scope.cadastralNumber)
				.then(function (data) {
					$scope.landsSelectorPager = data;
				});
		}

		$scope.addItem = function () {
			LandsLandCRUDService.addItem(updateList);
		}
		$scope.editItem = function (id) {
			LandsLandCRUDService.editItem(id, function () {
				updateList();
				LandsLandCRUDService.reloadItemInList(id, $scope.selectedItems);
			});
		}
		$scope.removeItem = function (id) {
			LandsLandCRUDService.deleteItem(id, updateList);
		}
		$scope.pageChanged = function () {
			updateList();
		}
		$scope.find = function (cadastralNumber) {
			updateList();
		}
		$scope.selectItem = function (land) {
			for (var i in $scope.selectedItems) {
				if ($scope.selectedItems[i].id == land.id) {
					return;
				}
			}
			$scope.selectedItems.push(land);
		}
		$scope.deselect = function (landId) {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				for (var i in $scope.selectedItems) {
					if ($scope.selectedItems[i].id == landId) {
						$scope.selectedItems.splice(i, 1);
					}
				}
				modalInstance.close();
			});
		}

		updateList();
	})
;
