angular.module("mgis.oks.address.selector", ["ui.bootstrap",
	"mgis.commons",
	"mgis.oks.address",
	"mgis.oks.address.service"
])
	.directive("addressSelector", function () {
		return {
			restrict: "E",
			scope: {
				address: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/oks/address-selector/address-selector-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm, OKSAddressService) {
				$scope.open = function () {
					var modalScope = $rootScope.$new();
					var address = $scope.address;
					if (address && address.id) {
						OKSAddressService.get(address.id).then(function (data) {
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

					var modal = MGISCommonsModalForm.edit("app2/oks/address-selector/address-selector-form.htm", modalScope, function (scope, $modalInstance) {
						selectionCompleteHandler(scope.address);
						$modalInstance.close();
					});

					modalScope.selectClicked = function (address) {
						selectionCompleteHandler(address);
						modal.close();
					}
				}
			}
		}
	})
	.controller("OKSAddressSelectController", function ($scope, OKSAddressService, OKSAddressModule) {
		$scope.first = 0;
		$scope.max = 15;
		function updateList() {
			OKSAddressService.get("", $scope.first, $scope.max).then(function (data) {
				$scope.list = data.list;
			});
		}

		updateList();

		$scope.find = function () {
			updateList();
		}
		$scope.add = function () {
			OKSAddressModule.add(function () {
				updateList();
			});
		}
		$scope.edit = function (id) {
			OKSAddressModule.edit(id, function () {
				updateList();
			});
		}
		$scope.remove = function (id) {
			OKSAddressService.remove(id).then(function () {
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
