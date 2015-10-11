angular.module("mgis.geo", ["ui.router",
	"mgis.geo.geo-server",
	"mgis.geo.coordinate-system",
	"mgis.geo.map",
	"mgis.geo.map.layer"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo", {
				url: "/geo",
				templateUrl: "app2/geo/geo.htm"
			})
		;
	})
;
