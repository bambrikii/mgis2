var mgisApp = angular.module("mgis", //
    [ "ui.router", 'pascalprecht.translate', //
        "mgis.isogd", "mgis.menu.main", "mgis.oks", "mgis.admin" ]);

mgisApp.config(function ($stateProvider, $urlRouterProvider, $translateProvider) {
    $urlRouterProvider //
//        .when("/admin", "/admin/users") //
        // .otherwise("/")
    ;

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
//        .state("admin", {
//            url: "/admin",
//            views: {
//                "": {
//                    templateUrl: "app2/admin/admin.htm",
//                    controller: function ($scope) {
//                        console.log("admin")
//                    }
//                }
//            }
//        }) //
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
