angular.module("mgis.capital-constructs.constructive-element-type", [])
	.directive("constructiveElementType", function () {
		return {
			scope: {
				value: "="
			},
			templateUrl: "app2/capital-constructs/constructive-element/constructive-element-type-selector.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					MGISCommonsModalForm.edit("app2/capital-constructs/constructive-element-type-selector-list.htm")
				}
			}
		}
	})
