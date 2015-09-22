angular.module("mgis.lands", ["ui.router", "ui.bootstrap", //
	"mgis.commons",
	"mgis.lands.lands",
	"mgis.lands.maps",
	"mgis.lands.services"
])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/lands", "/lands/lands/").when("/lands/lands", "/lands/lands/");
		$stateProvider
			.state("lands", {
				url: "/lands",
				views: {
					"": {
						templateUrl: "app2/lands/lands.htm",
						controller: "LandsController"
					}
				}
			});
	})
	.controller("LandsController", function ($scope, LandsLandService) {

	})
;
