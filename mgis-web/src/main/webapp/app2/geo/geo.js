angular.module("mgis.geo", ["ui.router",
	"mgis.geo.geo-server",
	"mgis.geo.coordinate-system"
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
