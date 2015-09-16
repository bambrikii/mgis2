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
	})
	.controller("TerrZonesController", function ($state) {
	});
