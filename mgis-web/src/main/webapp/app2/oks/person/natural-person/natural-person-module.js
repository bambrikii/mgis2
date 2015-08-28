angular.module("mgis.oks.person.natural", ["ui.router", "ui.bootstrap", //
	"mgis.oks.natural_person.service",
	"mgis.commons"
])
	.config(function ($stateProvider, $urlRouterProvider) {
		//$stateProvider
		//	.state("oks.list", {
		//		url: "/oks/:landId",
		//		templateUrl: "app2/lands/land/land-list.htm"
		//	})
		//;
	})
	.controller("NaturalPersonSelectorController", function ($scope, NaturalPersonService, MGISCommonsModalForm) {
		// Search,
		// List,
		// Add,
		// Remove
		$scope.list = new Array();
		$scope.first = 0;
		$scope.max = 15;
		$scope.name = "";
		$scope.search = function () {
			NaturalPersonService.get("", $scope.first, $scope.max, $scope.name).then(function (data) {
				$scope.list = data.list;
			});
		}
		$scope.edit = function (modalScope) {
			MGISCommonsModalForm.edit("app2/oks/natural-person/natural-person-form.htm", modalScope, function (item) {
				NaturalPersonService.save(item).then(function (data) {

				});
			});
		}
	})
;
