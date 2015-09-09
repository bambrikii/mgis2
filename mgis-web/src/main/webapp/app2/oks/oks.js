angular.module("mgis.oks", ["ui.router"
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$stateProvider //
			.state("oks", {
				url: "/oks",
				views: {
					"": {
						templateUrl: "app2/oks/oks.htm",
						controller: "OKSCtrl"
					}
				}
			}) //
		;
	}) //
	.controller("OKSCtrl", function ($state) {
		console.log("oks controller")
	}) //
;
