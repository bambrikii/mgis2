angular.module("mgis.lands", ["ui.router", "ui.bootstrap", //
	"mgis.commons", //
	"mgis.lands.lands", //
	"mgis.lands.maps", //
	"mgis.lands.services" //
])
	.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.when("/lands", "/lands/lands/").when("/lands/lands", "/lands/lands/");
	})
	.controller("LandsController", function ($scope, LandsLandService) {

	})
;
