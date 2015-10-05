angular.module("mgis.settings", ["ui.router"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("settings", {
				url: "/settings",
				templateUrl: "app2/settings/settings.htm"
			});
	})
;
