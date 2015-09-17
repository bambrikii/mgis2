angular.module("mgis.settings", ["ui.router",
	"mgis.settings.gis.server"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("settings.gis", {
				url: "/gis",
				templateUrl: "app2/settings/gis/gis.htm"
			})
			.state("settings", {
				url: "/settings",
				templateUrl: "app2/settings/settings.htm"
			});
	})
;
