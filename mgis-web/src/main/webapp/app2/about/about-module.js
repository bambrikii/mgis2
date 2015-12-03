angular.module("mgis.about", ["ui.router",
	"mgis.commons"])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider
			.when("/about", "/about/").when("/about/", "/about/info/") //
		;
		$stateProvider //
			.state("about", {
				url: "/about",
				templateUrl: "app2/about/about.htm"
			}) //
			.state("about.info", {
				url: "/info/",
				templateUrl: "app2/about/info.htm"
			}) //
			.state("about.help", {
				url: "/help/",
				templateUrl: "app2/about/help.htm"
			}) //
			.state("about.release-notes", {
				url: "/release-notes/",
				templateUrl: "app2/about/release-notes.htm"
			}) //
		;
	}) //
	.controller("AboutReleaseNotesController", function ($scope, $http) {
		$http.get("app2/about/release-notes.json")
			.then(function (res) {
				$scope.item = res.data;
			});
	})
;
