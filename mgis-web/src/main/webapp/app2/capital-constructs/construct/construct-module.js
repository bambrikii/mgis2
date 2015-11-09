angular.module("mgis.capital-constructs.construct", ["ui.router", "ui.bootstrap",
	"mgis.commons",
	"mgis.capital-constructs.construct.service",
	"mgis.property"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("constructs", {
				url: "/capital-constructs/constructs/",
				templateUrl: "app2/capital-constructs/construct/construct-list.htm"
			});
	})
	.controller("CapitalConstructsConstructController", function ($scope, $rootScope, CapitalConstructsConstructService, MGISCommonsModalForm) {
		console.log("CapitalConstructsConstructController");
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		function updateGrid() {
			CapitalConstructsConstructService.get("", $scope.first, $scope.max).then(function (data) {
				$scope.constructsPager = data;
			});
		}

		function editItem(modalScope) {
			MGISCommonsModalForm.edit("app2/capital-constructs/construct/construct-form.htm", modalScope, function (scope, $modalInstance) {

			}, {windowClass: "mgis-capital-construct-modal-form"});
		}

		updateGrid();

		$scope.addItem = function () {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0};
			editItem(modalScope);
		}

		$scope.editItem = function (id) {
			var modalScope = $rootScope.$new();
			CapitalConstructsConstructService.get(id).then(function (data) {
				modalScope.item = data;
				editItem(modalScope);
			});
		}
		$scope.deleteItem = function (id) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				CapitalConstructsConstructService.remove(id).then(function () {
					$modalInstance.close();
					updateGrid();
				});
			});
		}

	})
;
