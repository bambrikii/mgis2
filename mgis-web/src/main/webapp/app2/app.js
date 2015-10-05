angular.module("mgis", //
	["ui.router", 'pascalprecht.translate', //
		"mgis.isogd",
		"mgis.menu.main",
		"mgis.oks",
		"mgis.admin",
		"mgis.lands",
		"mgis.terr-zones",
		"mgis.address",
		"mgis.error",
		"mgis.settings",
		"mgis.test",
		"mgis.geo"
	])
	.config(function ($stateProvider, $urlRouterProvider, $translateProvider) {
		$translateProvider.preferredLanguage('ru_RU');
		$translateProvider.useStaticFilesLoader({
			prefix: 'app2/i18n/locale-',
			suffix: '.json'
		});

	});
