/**
 * Created by donchenko-y on 12/23/15.
 */
angular.module("mgis.persons.executive.person", ["ui.router", "ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.persons.executive.person.list"
])
	.filter("executivePersonFormatter", function ($filter) {
		return function (person) {
			if (person) {
				return person.surname + " " + person.firstName + (person.patronymic ? " " + person.patronymic : "") + " (" + $filter("translate")("Position") + " : " + person.position + ")";
			}
			return undefined;
		}
	})
	.directive("executivePersonSelector", function (MGISCommonsModalForm) {
		return {
			restrict: "E",
			scope: {
				person: "="
			},
			templateUrl: "app2/persons/executive-person/executive-person-selector-component.htm",
			controller: function ($scope, $rootScope) {
				$scope.openExecutiveSelector = function (person) {
					var modalScope = $rootScope.$new();
					modalScope.person = {};
					angular.copy(person, modalScope.person);

					var modal = MGISCommonsModalForm.edit("app2/persons/executive-person/executive-person-selector-form.htm", modalScope, function (scope, $modalInstance) {
						$scope.person = scope.person;
						$modalInstance.close();
					});
					modalScope.executivePersonSelectClicked = function (person) {
						$scope.person = person;
						if ($scope.executivePersonSelectClicked) {
							$scope.executivePersonSelectClicked(person);
						}
						modal.close();
					}
				}
				$scope.clearExecutiveSelection = function () {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						$scope.person = null;
						modalInstance.close();
					});
				}
			}
		}
	})
;
