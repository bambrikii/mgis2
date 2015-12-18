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
			controller: function ($scope, $rootScope, MGISCommonsModalForm, LandsLandCRUDService, MGISCommonsModalForm) {
				$scope.select = function () {
					var modalScope = $rootScope.$new();
					modalScope.selectedLands = new Array();
					for (var i in $scope.lands) {
						var land = $scope.lands[i];
						modalScope.selectedLands.push(land);
					}
					MGISCommonsModalForm.edit("app2/lands/land-selector/land-selector-search.htm", modalScope, function (scope, modalInstance) {
						$scope.lands = scope.selectedLands;
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
				LandsLandCRUDService.reloadItemInList(id, $scope.selectedLands);
			});
		}
		$scope.removeItem = function (id) {
			LandsLandCRUDService.removeItem(id, updateList);
		}
		$scope.pageChanged = function () {
			updateList();
		}
		$scope.find = function (cadastralNumber) {
			updateList();
		}
		$scope.selectItem = function (land) {
			for (var i in $scope.selectedLands) {
				if ($scope.selectedLands[i].id == land.id) {
					return;
				}
			}
			$scope.selectedLands.push(land);
		}
		$scope.deselect = function (landId) {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				for (var i in $scope.selectedLands) {
					if ($scope.selectedLands[i].id == landId) {
						$scope.selectedLands.splice(i, 1);
					}
				}
				modalInstance.close();
			});
		}

		updateList();
	})
;
