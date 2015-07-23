var mgisApp = angular.module("mgis", //
	["ui.router", 'pascalprecht.translate', //
		"mgis.isogd",
		"mgis.menu.main",
		"mgis.oks",
		"mgis.admin",
		"mgis.lands"
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
		.state("oks", {
			url: "/oks",
			views: {
				"": {
					templateUrl: "app2/oks/oks.htm",
					controller: "OKSCtrl"
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
