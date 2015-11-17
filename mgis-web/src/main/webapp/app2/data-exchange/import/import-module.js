angular.module("mgis.data-exchange.import", [])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/data-exchange/import", "/data-exchange/import/");
		$stateProvider //
			.state("import", {
				url: "/data-exchange/import/",
				views: {
					"": {
						templateUrl: "app2/data-exchange/import/import.htm"
					}
				}
			});
	}) //
;
