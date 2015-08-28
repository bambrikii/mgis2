angular.module("mgis.oks.person", [
	"mgis.commons",
	"mgis.oks.person.natural",
	"mgis.oks.person.legal"
])
	//.controller("PersonController", function ($scope) {
	//})
	.directive("PersonSelector", function (MGISCommonsModalForm) {
		//<person-selector ng-selected="selected(personObject, personType)"></person-selector>
		return {
			restrict: "E",
			scope: {
				personObject: "=",
				personType: "=",
				selectedCallback: null
			},
			templateUrl: "app2/oks/person/person-selector.htm",
			controller: function ($scope) {
				$scope.personObject = {}
				$scope.personType = "";
				$scope.openSelector = function (personObject, personType) {
					$scope.personObject = angular.extend({}, personObject);
					$scope.personType = "" + personType;
					MGISCommonsModalForm.edit("app2/oks/person/person-selector-form.htm", $scope, function (data, $modalInstance) {
						$scope.personObject = data.personObject;
						$scope.personType = data.personType;
						$modalInstance.close();
					});
				}
			}
		}
	})
;
