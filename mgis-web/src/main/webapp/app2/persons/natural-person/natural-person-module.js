angular.module("mgis.persons.person.natural", ["ui.router", "ui.bootstrap", //
	"mgis.persons.person.natural.service",
	"mgis.commons",
	"mgis.nc.services"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("persons/natural-persons", {
				url: "/persons/natural-persons",
				templateUrl: "app2/persons/natural-person/natural-person-list.htm"
			})
	})
	.factory("NaturalPersonModule", function (NaturalPersonService, MGISCommonsModalForm, $rootScope, NcOKVEDService) {

		function editItem0(modalScope, updateFunction) {
			modalScope.refreshAvailableActivityTypes = function (name) {
				NcOKVEDService.get("", 0, 15, name).then(function (okveds) {
					modalScope.availableActivityTypes = okveds.list;
				});
			}
			MGISCommonsModalForm.edit("app2/persons/natural-person/natural-person-form.htm", modalScope, function (scope, $modalInstance) {
				NaturalPersonService.save(scope.item).then(function (data) {
					$modalInstance.close();
					if (updateFunction) {
						updateFunction();
					}
				});
			}, {windowClass: "mgis-person-modal-form"});
		}

		function addItem(name, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0, name: name}
			editItem0(modalScope, updateFunction);
		}

		function editItem(id, updateFunction) {
			var modalScope = $rootScope.$new();
			NaturalPersonService.get(id).then(function (item) {
				modalScope.item = item;
				editItem0(modalScope, updateFunction);
			});
		}

		function removeItem(id, updateFunction) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				NaturalPersonService.remove(id).then(function () {
					$modalInstance.close();
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
	.controller("NaturalPersonSelectorController", function ($scope, $rootScope, NaturalPersonService, MGISCommonsModalForm, NcOKVEDService, NaturalPersonModule) {
		$scope.list = new Array();
		$scope.first = 0;
		$scope.max = 15;
		$scope.name = $scope.person ? $scope.person.name : "";
		$scope.find = function () {
			updateGrid();
		}
		function updateGrid() {
			NaturalPersonService.get("", $scope.first, $scope.max, $scope.name).then(function (data) {
				$scope.list = data.list;
			});
		}

		$scope.edit = function (id) {
			NaturalPersonModule.edit(id, updateGrid);
		}
		$scope.add = function (name) {
			NaturalPersonModule.add(name, updateGrid);
		}
		$scope.remove = function (id) {
			NaturalPersonModule.remove(id, updateGrid);
		}
		$scope.select = function (id) {
			NaturalPersonService.get(id).then(function (item) {
				if ($scope.selectClicked) {
					$scope.selectClicked({id: id, name: item.surname + " " + item.firstName + " " + item.patronymic});
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

	.controller("NaturalPersonsController", function ($scope, NaturalPersonModule, NaturalPersonService) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		function updateGrid() {
			NaturalPersonService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage).then(function (data) {
				$scope.naturalPersonsPager = data;
			})
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			NaturalPersonModule.add("", updateGrid);
		}

		$scope.editItem = function (id) {
			NaturalPersonModule.edit(id, updateGrid);
		}

		$scope.removeItem = function (id) {
			NaturalPersonModule.remove(id, updateGrid);
		}

		updateGrid();

	})

;
