angular.module("mgis.oks", ["ui.router", "ui.bootstrap",
	"mgis.oks.construct"
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/oks", "/oks/constructs/");
		$stateProvider //
			.state("oks", {
				url: "/oks",
				views: {
					"": {
						templateUrl: "app2/oks/oks.htm"
					}
				}
			}) //
		;
	}) //
;
