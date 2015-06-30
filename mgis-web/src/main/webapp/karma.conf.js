// Karma configuration
// Generated on Sun Jun 28 2015 14:03:33 GMT+0300 (MSK)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [


		"bower_components/angular/angular.js",
		'node_modules/angular-mocks/angular-mocks.js',
		"bower_components/angular-resource/angular-resource.js",
		// <!-- bower_components/angular-route/angular-route.js, -->
		"bower_components/angular-ui-router/release/angular-ui-router.js",
		"bower_components/jquery/jquery.js",
		"bower_components/angular-bootstrap/ui-bootstrap.js",
		"bower_components/bootstrap/dist/js/bootstrap.js",
		"bower_components/angular-translate/angular-translate.js",

		"bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js",
		"bower_components/angular-utils-ui-breadcrumbs/uiBreadcrumbs.js",
		"bower_components/angular-bootstrap-multiselect/angular-bootstrap-multiselect.js",

/*
		app2/app.js,
		app2/menu/app-menu.js,
		app2/commons/app-confirmation.js,
		app2/commons/app-modal-forms.js,
		app2/isogd/isogd.js,
		app2/isogd/section/isogd-sections-service.js,
		app2/isogd/section/isogd-sections-module.js,
		app2/isogd/volume/isogd-volumes-service.js,
		app2/isogd/volume/isogd-volumes-module.js,
		app2/isogd/book/isogd-books-service.js,
		app2/isogd/book/isogd-books-module.js,
		app2/isogd/document/isogd-documents-service.js,
		app2/isogd/document/isogd-documents-module.js,
		app2/isogd/isogd.js,
		app2/isogd/classifiers/classifiers.js,
		app2/isogd/classifiers/documents/structure/isogd-docs-structure-service.js,
		app2/isogd/classifiers/documents/structure/isogd-docs-structure-module.js,
		app2/isogd/classifiers/documents/types/isogd-docs-types-service.js,
		app2/isogd/classifiers/documents/types/isogd-docs-types-module.js,
		app2/isogd/classifiers/documents/representation/isogd-docs-representation-service.js,
		app2/isogd/classifiers/documents/representation/isogd-docs-representation-module.js,
		app2/admin/admin.js,
		app2/admin/users/admin-users-service.js,
		app2/admin/users/admin-users-module.js,
		app2/admin/privileges/admin-privileges-service.js,
		app2/admin/privileges/admin-privileges-module.js,
		app2/oks/oks.js
*/
		"app2/**/*.js",
		"app2-tests/**/*.js"

    ],


    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false
  })
}
