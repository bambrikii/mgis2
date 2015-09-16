var mgisApp = angular.module("mgis", //
	["ui.router", 'pascalprecht.translate', //
		"mgis.isogd",
		"mgis.menu.main",
		"mgis.oks",
		"mgis.admin",
		"mgis.lands",
		"mgis.terr-zones",
		"mgis.address",
		"mgis.error"
	]);

mgisApp.config(function ($stateProvider, $urlRouterProvider, $translateProvider) {
	$stateProvider //
		.state("isogd", {
			url: "/isogd",
			views: {
				"": {
					templateUrl: "app2/isogd/isogd.htm",
					controller: "ISOGDCtrl"
				}
			}
		}) //
		.state("lands", {
			url: "/lands",
			views: {
				"": {
					templateUrl: "app2/lands/lands.htm",
					controller: "LandsController"
				}
			}
		}) //
		.state("terr-zones", {
			url: "/terr-zones",
			views: {
				"": {
					templateUrl: "app2/terr-zones/terr-zones.htm",
					controller: "TerrZonesController"
				}
			}

		}) //
	;

	$translateProvider.preferredLanguage('ru_RU');
	$translateProvider.useStaticFilesLoader({
		prefix: 'app2/i18n/locale-',
		suffix: '.json'
	});

});
