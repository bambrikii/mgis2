angular.module("mgis.test", [
	"mgis.commons.forms"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("tests-test1",
			{
				url: "/tests-test1",
				views: {
					"": {
						templateUrl: "app2/test/test-page-1.htm"
					}
				}
			});
	})
	.controller("TestController", function ($scope) {
		$scope.item = {
			text: "text1",
			text2: "text2",
			dropDown: {id: 1, name: "name1"},
			dropDownAjax: {id: 1, name: "name1"},
			date: new Date(),
			chooseOne: null,
			chooseMany: null
		};
		var dropDownElements = new Array();
		for (var i = 0; i < 50; i++) {
			dropDownElements.push({id: i, name: "SomeName - " + i});
		}
		$scope.availableDropDownElements = dropDownElements;
		$scope.availableDropDownAjaxElements = new Array();
		$scope.refreshAvailableDropDownAjaxElements = function (name) {
			var arr = new Array();
			for (var i = 0; i < 100; i++) {
				arr.push({id: i, name: name + "_" + i});
			}
			$scope.availableDropDownAjaxElements = arr;
		}
	})
;
