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
			controller: function ($scope, $rootScope, MGISCommonsModalForm, LandsLandService, LandsLandCRUDService, CommonsPagerManager) {
				$scope.select = function () {
					var modalScope = $rootScope.$new();
					modalScope.currentPage = 1;
					modalScope.itemsPerPage = CommonsPagerManager.pageSize();
					function updateList() {
						LandsLandService.get("", CommonsPagerManager.offset(modalScope.currentPage), modalScope.itemsPerPage,
							modalScope.cadastralNumber,
							ids
						).then(function (data) {
								$scope.landsSelectorPager = data;
								$scope.selectedIds = {};
								for (var i in data.list) {
									var land = data.list[i];
									if (ids.indexOf(land.id) > -1) {
										$scope.selectedIds[land.id] = {checked: true, cadastralNumber: land.cadastralNumber};
									}
								}
							}
						);
					}

					modalScope.add = function () {
						LandsLandCRUDService.add(updateList);
					}
					modalScope.edit = function (id) {
						LandsLandCRUDService.edit(id, updateList);
					}
					modalScope.remove = function (id) {
						LandsLandCRUDService.remove(id, updateList);
					}
					modalScope.pageChanged = function () {
						updateList();
					}
					modalScope.find = function (cadastralNumber) {
						updateList();
					}

					MGISCommonsModalForm.edit("app2/lands/land-selector/land-selector-search.htm", modalScope, function (scope, modalInstance) {
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("LandsLandSelectorController", function () {

	})
;
