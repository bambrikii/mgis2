angular.module("mgis.terr-zones", ["ui.router", "ui.bootstrap", //
	"mgis.commons", //
	"mgis.terr-zones.zone", //
	"mgis.terr-zones.zone.service" //
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider
			.when("/terr-zones", "/terr-zones/zones/")
			.when("/terr-zones/zones", "/terr-zones/zones/")
		;
		$stateProvider
			.state("terr-zones", {
				url: "/terr-zones",
				views: {
					"": {
						templateUrl: "app2/terr-zones/terr-zones.htm",
						controller: "TerrZonesController"
					}
				}

			}) //
	})
	.controller("TerrZonesController", function ($state) {
	});
