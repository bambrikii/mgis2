angular.module("mgis.commons.forms", ["ui.bootstrap",
	"mgis.commons"
])
	.constant("NUMBER_PATTERN", /^\d+$/)
	.constant("INTEGER_PATTERN", /^[+-]?\d+?$/)
	.constant("FLOAT_PATTERN", /^[+-]\d+(\.\d+)?$/)
	.constant("PAGER__PAGE_SIZE", 15)
	.constant("PAGER__MAX_SIZE", 5)
	.factory("CommonsPagerManager", function (PAGER__PAGE_SIZE, PAGER__MAX_SIZE) {
		return {
			pageSize: function () {
				return PAGER__PAGE_SIZE;
			},
			maxSize: function () {
				return PAGER__MAX_SIZE;
			},
			offset: function (pageNumber) {
				return PAGER__PAGE_SIZE * (pageNumber - 1);
			}
		}
	})
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
	.directive("commonsFormsCheck", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				title: "@",
				property: "=",
				form: "=",
				name: "@"
			},
			templateUrl: "app2/commons/forms/check.htm"
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
				name: "@",
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
	.directive("commonsFormsDate", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				form: "=",
				name: "@",
				minDate: "=",
				maxDate: "=",
				required: "=",
				pattern: "="
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
	.directive("commonsFormsDropDown", function () {
		return {
			restrict: "E",
			scope: {
				title: "@",
				property: "=",
				availableElements: "=",
				form: "=",
				name: "@",
				required: "=",
				elementLabelExpression: "@"
			},
			templateUrl: "app2/commons/forms/drop-down.htm",
			controller: function ($scope) {
				$scope.property__flag = undefined;
				$scope.options = ($scope.elementLabelExpression ? $scope.elementLabelExpression : "element.name") + " for element in availableElements";
				$scope.$watch("property", function (value) {
					$scope.property__flag = !$scope.property || angular.equals({}, $scope.property) ? null : true;
				});
				$scope.emptyValue = function () {
					$scope.property = null;
				}
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
				availableElementsLoader: "&",
				form: "=",
				name: "@",
				required: "=",
				elementFilter: "@",
				elementLabelExpression: "@"
			},
			templateUrl: "app2/commons/forms/drop-down-ajax.htm",
			controller: function ($scope) {
				$scope.item = {
					value: $scope.property
				}
				$scope.choices = "element in availableElements | filter: {" + ($scope.elementFilter ? $scope.elementFilter : "name") + ": $select.search}"
				var displayLabel;
				var matchLabel;
				if ($scope.elementFilter) {
					matchLabel = "item.value." + $scope.elementFilter;
				}
				if ($scope.elementLabelExpression) {
					if (/^[\d\w_]+$/.test($scope.elementLabelExpression)) {
						displayLabel = "element." + $scope.elementLabelExpression;
						if (!matchLabel) {
							matchLabel = "item.value." + $scope.elementLabelExpression;
						}
					} else {
						displayLabel = $scope.elementLabelExpression;
					}
				} else if ($scope.elementFilter) {
					displayLabel = "element." + $scope.elementFilter;
				} else {
					displayLabel = "element.name";
				}
				if (!matchLabel) {
					matchLabel = "item.value.name";
				}
				$scope.displayLabel = displayLabel;
				$scope.matchLabel = matchLabel;
				$scope.refreshAvailableElements = function (name) {
					if ($scope.availableElementsLoader) {
						$scope.availableElementsLoader({search: name});
					}
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
;
