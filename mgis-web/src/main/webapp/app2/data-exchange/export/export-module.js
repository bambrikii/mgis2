angular.module("mgis.data-exchange.export", [])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/data-exchange/export", "/data-exchange/export/");
		$stateProvider //
			.state("export", {
				url: "/data-exchange/export/",
				views: {
					"": {
						templateUrl: "app2/data-exchange/export/export.htm"
					}
				}
			});
	}) //
;

