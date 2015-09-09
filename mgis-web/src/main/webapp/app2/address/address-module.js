angular.module("mgis.address", ["ui.bootstrap", "ui.select",
	"mgis.commons",
	"mgis.address.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("addresses", {
				url: "/addresses",
				templateUrl: "app2/address/address-list.htm"
			})
	})
	.factory("AddressModule", function ($rootScope, MGISCommonsModalForm, AddressService) {

		function editItem0(modalScope, updateFunction) {
			MGISCommonsModalForm.edit("app2/address/address-form.htm", modalScope, function (scope, $modalInstance) {
				AddressService.save(scope.address).then(function () {
					$modalInstance.close();
					if (updateFunction) {
						updateFunction();
					}
				});
			});
		}

		function addItem(updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.address = {id: 0};
			editItem0(modalScope, updateFunction);
		}

		function editItem(id, updateFunction) {
			AddressService.get(id).then(function (data) {
				var modalScope = $rootScope.$new();
				modalScope.address = data;
				editItem0(modalScope, updateFunction);
			});
		}

		function removeItem(id, updateFunction) {
			MGISCommonsModalForm.confirmRemoval(function () {
				AddressService.remove(id).then(function () {
					if (updateFunction) {
						updateFunction();
					}
				});
			});
		}

		return {
			add: addItem,
			edit: editItem,
			remove: removeItem
		}
	})
	.controller("AddressController", function ($scope, AddressModule, AddressService) {

		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;

		function updateGrid() {
			AddressService.list("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage).then(function (data) {

			});
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			AddressModule.add(updateGrid);
		}

		$scope.editItem = function (id, updateFunction) {
			AddressModule.edit(id, updateFunction);
		}

		$scope.removeItem = function (id, updateFunction) {
			AddressModule.remove(id, updateFunction);
		}
	})
;
