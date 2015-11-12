angular.module("mgis.indicators", ["ui.bootstrap",
	"mgis.indicators.price-indicator",
	"mgis.indicators.technical-indicator"
])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/indicators", "/indicators/");
		$stateProvider
			.state("indicators",
			{
				url: "/indicators/",
				views: {
					"": {
						templateUrl: "app2/indicators/indicators.htm"
					}
				}
			});
	})
;
