angular.module("mgis.lands.lands", ["ui.router", "ui.bootstrap", //
	"mgis.commons", //
	"mgis.lands.lands.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("lands.lands", {
				url: "/lands/:landId",
				templateUrl: "app2/lands/land/land-list.htm"
			})
	})
	.controller("LandsLandController", function ($scope, LandsLandService, $rootScope, MGISCommonsModalForm) {
		$scope.cadastralNumber = "";
		$scope.first = 0;
		$scope.max = 15;
		function updateGrid() {
			LandsLandService.get("", $scope.first, $scope.max, $scope.cadastralNumber).then(function (data) {
				$scope.list = data.list;
				$scope.first = data.first;
				$scope.max = data.max;
			});
		}

		function editItem(modalScope) {
			MGISCommonsModalForm.edit("app2/lands/land/land-form.htm", modalScope, function (scope, $modalInstance) {
				LandsLandService.save(scope.land).then(function (data) {
					$modalInstance.close();
					updateGrid();
				});
			});
		}

		$scope.find = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.land = {
				id: 0,
				cadastralNumber: ""
			}
			editItem(modalScope);
		}

		$scope.editItem = function (id) {
			LandsLandService.get(id).then(function (data) {
				var modalScope = $rootScope.$new();
				modalScope.land = data;
				editItem(modalScope);
			});
		}

		$scope.deleteItem = function (id) {
			LandsLandService.remove(id).then(function () {
				updateGrid();
			})
		}

		updateGrid();

		$scope.displayOnTheMap = function () {
			// TODO: display on the map
		}
	})
;
