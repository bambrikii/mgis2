angular.module("mgis.commons.forms", ["ui.bootstrap",
	"mgis.commons"
])
	.directive("commonsFormsForm", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				name: "@"
			},
			templateUrl: "app2/commons/forms/form.htm"
		}
	})
	.directive("commonsFormsPanel", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@"
			},
			templateUrl: "app2/commons/forms/panel.htm"
		}
	})
	.directive("commonsFormsField", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@"
			},
			templateUrl: "app2/commons/forms/field.htm"
		}
	})
	.directive("commonsFormsDate", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				validate: "@"
			},
			templateUrl: "app2/commons/forms/date.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.dateChanged = function () {
					$scope.property = $scope.item.value;
				}
			}
		}
	})
	.directive("commonsFormsText", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				textChange: "&",
				form: "=",
				name: "=",
				required: "=",
				pattern: "="
			},
			templateUrl: "app2/commons/forms/text.htm",
			controller: function ($scope) {
				$scope.valueChanged = function () {
					if ($scope.textChange) {
						$scope.textChange({event: event});
					}
				}
			}
		}
	})
	.directive("commonsFormsDropDown", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "=",
				form: "=",
				name: "=",
				required: "="
			},
			templateUrl: "app2/commons/forms/drop-down.htm",
			controller: function ($scope) {
				$scope.property__flag = undefined;
				$scope.$watch("property", function (value) {
					$scope.property__flag = angular.equals({}, $scope.property) ? null : true;
				});
			}
		}
	})
	.directive("commonsFormsDropDownAjax", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "=",
				refresh: "&",
				validate: "@"
			},
			templateUrl: "app2/commons/forms/drop-down-ajax.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.refreshAvailableElements = function (name) {
					$scope.refresh({name: name});
				}
				$scope.elementSelected = function ($item) {
					var val = {}
					angular.copy($item, val);
					$scope.property = val;
				}
			}
		}
	})
	.directive("commonsFormsChooseOne", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "=",
				availableItems: "&"
			},
			templateUrl: "app2/commons/forms/choose-one.htm"
		}
	})
	.directive("commonsFormsChooseMany", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "="
			},
			templateUrl: "app2/commons/forms/choose-many.htm"
		}
	})
	.directive("commonsFormsCheck", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "="
			},
			templateUrl: "app2/commons/forms/check.htm"
		}
	})
;
