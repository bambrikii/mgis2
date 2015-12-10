angular.module("mgis.persons.person", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.persons.person.natural",
	"mgis.persons.person.legal"
])
	.constant("NATURAL_PERSON_TYPE", "NaturalPerson")
	.constant("LEGAL_PERSON_TYPE", "LegalPerson")
	.directive("personSelector", function (MGISCommonsModalForm, $rootScope) {
		//<person-selector ng-selected="selected(personObject, personType)"></person-selector>
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/persons/person-selector/person-selector-component.htm",
			controller: function ($scope, NATURAL_PERSON_TYPE, LEGAL_PERSON_TYPE) {
				if ($scope.person) {
					$scope.personType = $scope.person.surname ? NATURAL_PERSON_TYPE : LEGAL_PERSON_TYPE;
				} else {
					$scope.personType = "";
				}
				$scope.formatPersonName = function (person) {
					return person ? (person.name ? person.name : (person.surname + ' ' + person.firstName + ' ' + person.patronymic)) : "?";
				}
				$scope.openSelector = function (person, personType) {
					var modalScope = $rootScope.$new();
					modalScope.person = {};
					angular.copy(person, modalScope.person);
					modalScope.personType = personType;
					modalScope.naturalPersonTabActive = !personType || personType == NATURAL_PERSON_TYPE;
					modalScope.legalPersonTabActive = personType == LEGAL_PERSON_TYPE;
					var modal = MGISCommonsModalForm.edit("app2/persons/person-selector/person-selector-form.htm", modalScope, function (scope, $modalInstance) {
						$scope.person = scope.person;
						$scope.personType = scope.personType;
						$modalInstance.close();
					});
					modalScope.naturalPersonSelectClicked = function (id, name) {
						$scope.person = {id: id, name: name};
						$scope.personType = NATURAL_PERSON_TYPE;
						if ($scope.selectClicked) {
							$scope.selectClicked({id: id, name: name, type: NATURAL_PERSON_TYPE});
						}
						modal.close();
					}
					modalScope.legalPersonSelectClicked = function (id, name) {
						$scope.person = {id: id, name: name};
						$scope.personType = LEGAL_PERSON_TYPE;
						if ($scope.selectClicked) {
							$scope.selectClicked({id: id, name: name, type: LEGAL_PERSON_TYPE});
						}
						modal.close();
					}
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
	.controller("PersonsSelectorController", function ($scope, NATURAL_PERSON_TYPE, LEGAL_PERSON_TYPE) {
		function personExists(id) {
			var persons = $scope.persons;
			for (var i in persons) {
				if (persons[i].id == id) {
					return true;
				}
			}
			return false;
		}

		$scope.naturalPersonSelectClicked = function (id, name) {
			if (!$scope.multiple) {
				$scope.persons.slice(0, $scope.persons.length);
			}
			if (!personExists(id)) {
				$scope.persons.push({id: id, name: name, personType: NATURAL_PERSON_TYPE});
			}
		}
		$scope.legalPersonSelectClicked = function (id, name) {
			if (!$scope.multiple) {
				$scope.persons.slice(0, $scope.persons.length);
			}
			if (!personExists(id)) {
				$scope.persons.push({id: id, name: name, personType: LEGAL_PERSON_TYPE});
			}
		}
		$scope.removePerson = function (id) {
			for (var i in $scope.persons) {
				var p = $scope.persons[i];
				if (p.id == id) {
					$scope.persons.splice(i, 1);
				}
			}
		}
	})
;
