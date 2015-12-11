angular.module("mgis", //
	["ui.router", 'pascalprecht.translate', //
		"mgis.isogd",
		"mgis.menu.main",
		"mgis.capital-constructs",
		"mgis.admin",
		"mgis.lands",
		"mgis.terr-zones",
		"mgis.address",
		"mgis.error",
		"mgis.settings",
		"mgis.test",
		"mgis.geo",
		"mgis.indicators",
		"mgis.data-exchange",
		"mgis.reports",
		"mgis.about"
	])
	.config(function ($stateProvider, $urlRouterProvider, $translateProvider) {
		$translateProvider.preferredLanguage('ru_RU');
		$translateProvider.useStaticFilesLoader({
			prefix: 'app2/i18n/locale-',
			suffix: '.json'
		});

	});
