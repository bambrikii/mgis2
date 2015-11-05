angular.module("mgis.oks.construct", ["ui.router", "ui.bootstrap",
	"mgis.commons",
	"mgis.oks.construct.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("constructs", {
				url: "/oks/constructs/",
				templateUrl: "app2/oks/construct/construct-list.htm"
			});
	})
	.controller("OKSConstructController", function ($scope, $rootScope, OKSConstructService, MGISCommonsModalForm) {
		console.log("OKSConstructController");
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		function updateGrid() {
			OKSConstructService.get("", $scope.first, $scope.max).then(function (data) {
				$scope.constructsPager = data;
			});
		}

		function editItem(modalScope) {
			OKSConstructService.get("", 0, 15, null).then(function () {

			});
		}

		updateGrid();

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0}
			editItem(modalScope);
		}

		$scope.editItem = function (id) {
			var modalScope = $rootScope.$new();
			OKSConstructService.get(id).then(function (data) {
				modalScope.item = data;
				editItem(modalScope);
			});
		}
		$scope.deleteItem = function (id) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				OKSConstructService.remove(id).then(function () {
					$modalInstance.close();
					updateGrid();
				});
			});
		}

	})
;
