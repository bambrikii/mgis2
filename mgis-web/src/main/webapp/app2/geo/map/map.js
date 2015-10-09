angular.module("mgis.geo.map", ["ui.router"])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo.map", {
				url: "/map",
				templateUrl: "app2/geo/map/map.htm"
			});
	})
	.controller("GEOMapController", function () {

	})
;
