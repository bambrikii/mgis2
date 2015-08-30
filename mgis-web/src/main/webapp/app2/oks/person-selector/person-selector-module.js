angular.module("mgis.oks.person", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.oks.person.natural",
	"mgis.oks.person.legal"
])
	.constant("NATURAL_PERSON_TYPE", "NaturalPerson")
	.constant("LEGAL_PERSON_TYPE", "LegalPerson")
	.directive("personSelector", function (MGISCommonsModalForm, $rootScope) {
		//<person-selector ng-selected="selected(personObject, personType)"></person-selector>
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&",
			},
			templateUrl: "app2/oks/person-selector/person-selector-component.htm",
			controller: function ($scope, NATURAL_PERSON_TYPE, LEGAL_PERSON_TYPE) {
				if ($scope.person) {
					$scope.personType = $scope.person.surname ? NATURAL_PERSON_TYPE : LEGAL_PERSON_TYPE;
				} else {
					$scope.personType = "";
				}
				$scope.openSelector = function (person, personType) {
					var modalScope = $rootScope.$new();
					modalScope.person = angular.extend({}, person);
					modalScope.personType = personType;
					modalScope.naturalPersonTabActive = !personType || personType == NATURAL_PERSON_TYPE;
					modalScope.legalPersonTabActive = personType == LEGAL_PERSON_TYPE;
					var modal = MGISCommonsModalForm.edit("app2/oks/person-selector/person-selector-form.htm", modalScope, function (scope, $modalInstance) {
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
;
