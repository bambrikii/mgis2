angular.module("mgis.oks.person.legal", ["ui.router", "ui.bootstrap", //
	"mgis.oks.person.legal.service",
	"mgis.commons"
])
	.controller("LegalPersonSelectorController", function ($scope, $rootScope, LegalPersonService, MGISCommonsModalForm) {
		$scope.list = new Array();
		$scope.first = 0;
		$scope.max = 15;
		$scope.name = $scope.person ? $scope.person.name : "";
		$scope.find = function () {
			LegalPersonService.get("", $scope.first, $scope.max, $scope.name).then(function (data) {
				$scope.list = data.list;
			});
		}
		function editItem(modalScope) {
			MGISCommonsModalForm.edit("app2/oks/legal-person/legal-person-form.htm", modalScope, function (scope, $modalInstance) {
				LegalPersonService.save(scope.item).then(function (data) {
					$modalInstance.close();
					$scope.name = scope.item.name;
					$scope.find(scope.item.name);
				});
			});
		}

		$scope.edit = function (id) {
			LegalPersonService.get(id).then(function (item) {
				var modalScope = $rootScope.$new();
				modalScope.item = item;
				editItem(modalScope);
			});
		}
		$scope.add = function (name) {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0, name: name}
			editItem(modalScope);
		}
		$scope.remove = function (id) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				LegalPersonService.remove(id).then(function () {
					$modalInstance.close();
					$scope.find($scope.name);
				});
			});
		}
		$scope.select = function (id) {
			LegalPersonService.get(id).then(function (item) {
				if ($scope.selectClicked) {
					$scope.selectClicked({id: id, name: item.name});
				}
			});
		}
		$scope.find();
	})
	.directive("legalPersonSelector", function ($rootScope) {
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/oks/legal-person/legal-person-selector.htm"
		}
	})
;
