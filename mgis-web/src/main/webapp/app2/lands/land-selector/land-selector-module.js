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
			controller: function ($scope, $rootScope, MGISCommonsModalForm) {
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
			}
		}
	})
	.controller("LandsLandSelectorController", function ($scope, LandsLandService, LandsLandCRUDService, CommonsPagerManager) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		function updateList() {
			console.log($scope.currentPage + " " + $scope.itemsPerPage);
			LandsLandService.get(null, CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage, $scope.cadastralNumber)
				.then(function (data) {
					$scope.landsSelectorPager = data;
				});
		}

		$scope.addItem = function () {
			LandsLandCRUDService.add(updateList);
		}
		$scope.editItem = function (id) {
			LandsLandCRUDService.edit(id, updateList);
		}
		$scope.removeItem = function (id) {
			LandsLandCRUDService.remove(id, updateList);
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
			for (var i in $scope.selectedLands) {
				if ($scope.selectedLands[i].id == landId) {
					$scope.selectedLands.splice(i, 1);
				}
			}
		}

		updateList();
	})
;
