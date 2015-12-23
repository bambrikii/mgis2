angular.module("mgis.persons.person", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.persons.person.natural",
	"mgis.persons.person.legal"
])
	.constant("NATURAL_PERSON_TYPE", "NaturalPerson")
	.constant("LEGAL_PERSON_TYPE", "LegalPerson")
	.filter("personFormatter", function ($filter) {
		return function (person) {
			if (person) {
				if (person.name) {
					return person.name + " (" + $filter("translate")("LegalPerson.Short") + ")";
				} else {
					return person.surname + " " + person.firstName + " " + person.patronymic + " (" + $filter("translate")("NaturalPerson.Short") + ")";
				}
			}
			return undefined;
		}
	})
	.directive("personSelector", function (MGISCommonsModalForm, $rootScope) {
		//<person-selector ng-selected="selected(person)"></person-selector>
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/persons/person-selector/person-selector-component.htm",
			controller: function ($scope) {
				$scope.openSelector = function (person) {
					var modalScope = $rootScope.$new();
					modalScope.person = {};
					angular.copy(person, modalScope.person);
					var isLegalPerson = person && person.name;
					modalScope.naturalPersonTabActive = !isLegalPerson;
					modalScope.legalPersonTabActive = isLegalPerson;
					var modal = MGISCommonsModalForm.edit("app2/persons/person-selector/person-selector-form.htm", modalScope, function (scope, $modalInstance) {
						$scope.person = scope.person;
						$modalInstance.close();
					});
					modalScope.personSelectClicked = function (person) {
						$scope.person = person;
						if ($scope.selectClicked) {
							$scope.selectClicked(person);
						}
						modal.close();
					}
				}
				$scope.clearSelection = function () {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						$scope.person = null;
						modalInstance.close();
					});
				}
			}
		}
	})
	.directive("personsSelector", function (MGISCommonsModalForm, $rootScope) {
		return {
			restrict: "E",
			scope: {
				person: "=",
				persons: "=",
				multiple: "="
			},
			templateUrl: "app2/persons/person-selector/persons-selector-component.htm",
			controller: function ($scope) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.multiple = $scope.multiple;
					var persons = new Array();
					if (modalScope.multiple) {
						if (!$scope.persons) {
							$scope.persons = new Array();
						}
						angular.copy($scope.persons, persons);
					} else {
						var person = {};
						angular.copy($scope.person, person);
						persons.push(person);
					}
					modalScope.persons = persons;
					var modal = MGISCommonsModalForm.edit("app2/persons/person-selector/persons-selector-form.htm", modalScope, function (scope, $modalInstance) {
						if (modalScope.multiple) {
							angular.copy(scope.persons, $scope.persons);
						} else {
							if (!$scope.person) {
								$scope.person = {};
							}
							if ($scope.persons.length > 0) {
								angular.copy(scope.person, $scope.person);
							}
						}
						$modalInstance.close();
					});
				}
			}
		}
	})
	.controller("PersonsSelectorController", function ($scope, MGISCommonsModalForm) {
		function personExists(id) {
			var persons = $scope.persons;
			for (var i in persons) {
				if (persons[i].id == id) {
					return true;
				}
			}
			return false;
		}

		$scope.personSelectClicked = function (person) {
			if (!$scope.multiple) {
				$scope.persons.slice(0, $scope.persons.length);
			}
			if (!personExists(person.id)) {
				$scope.persons.push(person);
			}
		}
		$scope.legalPersonSelectClicked = function (person) {
			if (!$scope.multiple) {
				$scope.persons.slice(0, $scope.persons.length);
			}
			if (!personExists(person.id)) {
				$scope.persons.push(person);
			}
		}
		$scope.removePerson = function (id) {
			MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
				for (var i in $scope.persons) {
					var p = $scope.persons[i];
					if (p.id == id) {
						$scope.persons.splice(i, 1);
					}
				}
				modalInstance.close();
			});
		}
	})
;
