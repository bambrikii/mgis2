angular.module("mgis.persons.person.natural", ["ui.router", "ui.bootstrap", //
	"mgis.persons.person.natural.service",
	"mgis.commons"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("persons/natural-persons", {
				url: "/persons/natural-persons",
				templateUrl: "app2/persons/natural-person/natural-persons-list.htm"
			})
	})
	.controller("NaturalPersonSelectorController", function ($scope, $rootScope, NaturalPersonService, MGISCommonsModalForm) {
		$scope.list = new Array();
		$scope.first = 0;
		$scope.max = 15;
		$scope.name = $scope.person ? $scope.person.name : "";
		$scope.find = function () {
			NaturalPersonService.get("", $scope.first, $scope.max, $scope.name).then(function (data) {
				$scope.list = data.list;
			});
		}
		function editItem(modalScope) {
			MGISCommonsModalForm.edit("app2/persons/natural-person/natural-person-form.htm", modalScope, function (scope, $modalInstance) {
				NaturalPersonService.save(scope.item).then(function (data) {
					$modalInstance.close();
					$scope.name = scope.item.name;
					$scope.find(scope.item.name);
				}, {
					
				});
			});
		}

		$scope.edit = function (id) {
			NaturalPersonService.get(id).then(function (item) {
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
				NaturalPersonService.remove(id).then(function () {
					$modalInstance.close();
					$scope.find($scope.name);
				});
			});
		}
		$scope.select = function (id) {
			NaturalPersonService.get(id).then(function (item) {
				if ($scope.selectClicked) {
					$scope.selectClicked({id: id, name: item.name});
				}
			});
		}
		$scope.find();

	})
	.directive("naturalPersonSelector", function () {
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/persons/natural-person/natural-person-selector.htm"
		}
	})
;
