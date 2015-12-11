angular.module("mgis.reports", ["ui.router", "ui.bootstrap",
	"mgis.reports.report",
	"mgis.reports.report.service"
])
	.config(function ($stateProvider, $urlRouterProvider) {

		$urlRouterProvider.when("/reports", "/reports/");

		$stateProvider
			.state("reports", {
				url: "/reports/",
				views: {
					"": {
						templateUrl: "app2/reports/reports.htm"
					}
				}
			})
		;
	})
;
