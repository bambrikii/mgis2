angular.module("mgis.data-exchange", ["ui.router",
	"mgis.data-exchange.import",
	"mgis.data-exchange.export"
])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/data-exchange", "/data-exchange/");
		$stateProvider //
			.state("data-exchange", {
				url: "/data-exchange/",
				views: {
					"": {
						templateUrl: "app2/data-exchange/data-exchange.htm"
					}
				}
			});
	}) //
;
