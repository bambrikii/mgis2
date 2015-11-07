angular.module("mgis.capital-constructs", ["ui.router", "ui.bootstrap",
	"mgis.capital-constructs.construct"
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/capital-constructs", "/capital-constructs/constructs/");
		$stateProvider //
			.state("oks", {
				url: "/oks",
				views: {
					"": {
						templateUrl: "app2/capital-constructs/capital-constructs.htm"
					}
				}
			}) //
		;
	}) //
;
