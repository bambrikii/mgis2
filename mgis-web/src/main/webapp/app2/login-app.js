angular.module("mgis.auth.app", ["pascalprecht.translate",
	"mgis.auth.login"])
	.config(function ($translateProvider) {
		$translateProvider.preferredLanguage('ru_RU');
		$translateProvider.useStaticFilesLoader({
			prefix: 'app2/i18n/locale-',
			suffix: '.json'
		});
	})
;
