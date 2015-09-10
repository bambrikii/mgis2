angular.module("mgis.address.selector", ["ui.bootstrap",
	"mgis.commons",
	"mgis.address",
	"mgis.address.service"
])
	.directive("addressSelector", function () {
		return {
			restrict: "E",
			scope: {
				address: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/address/address-selector/address-selector-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm, AddressService) {
				$scope.open = function () {
					var modalScope = $rootScope.$new();
					var address = $scope.address;
					if (address && address.id) {
						AddressService.get(address.id).then(function (data) {
							address = data;
						});
					} else {
						address = {}
					}
					modalScope.address = angular.extend({}, address);
					function selectionCompleteHandler(selectedAddress) {
						$scope.address = angular.extend({}, selectedAddress);
						if ($scope.selectClicked) {
							$scope.selectClicked({address: selectedAddress});
						}
					}

					var modal = MGISCommonsModalForm.edit("app2/address/address-selector/address-selector-form.htm", modalScope, function (scope, $modalInstance) {
						selectionCompleteHandler(scope.address);
						$modalInstance.close();
					}, {windowClass: "mgis-address-modal-form"});

					modalScope.selectClicked = function (address) {
						selectionCompleteHandler(address);
						modal.close();
					}
				}
			}
		}
	})
	.controller("AddressSelectController", function ($scope, AddressService, AddressModule) {
		$scope.first = 0;
		$scope.max = 15;
		function updateList() {
			AddressService.get("", $scope.first, $scope.max, $scope.name).then(function (data) {
				$scope.list = data.list;
			});
		}

		updateList();

		$scope.find = function () {
			updateList();
		}
		$scope.add = function () {
			AddressModule.add(function () {
				updateList();
			});
		}
		$scope.edit = function (id) {
			AddressModule.edit(id, function () {
				updateList();
			});
		}
		$scope.remove = function (id) {
			AddressModule.remove(id, function () {
				updateList();
			});
		}
		$scope.select = function (selectedAddress) {
			if ($scope.selectClicked) {
				$scope.selectClicked(selectedAddress);
			}
		}
	})
;
