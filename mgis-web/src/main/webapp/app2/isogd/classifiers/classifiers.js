angular.module("mgis.isogd.classifiers", ["ui.router", //
    "mgis.common", "mgis.isogd.classifiers.documents.structure", "mgis.isogd.classifiers.documents.types" //
]) //
    .config(function ($stateProvider, $urlRouterProvider) {
//        $urlRouterProvider.when("/isogd/classifiers", "/isogd/classifiers/structure");
        $stateProvider //
            .state("isogd.classifiers", {
                url: "/classifiers",
                templateUrl: "app2/isogd/classifiers/classifiers.htm",
                controller: function ($scope, $state, $stateParams) {
                    console.log("classifiers controller");
                }
            }) //
            .state("isogd.classifiers.doctypes", {
                url: "/classifiers/doctypes",
                templateUrl: "app2/isogd/classifiers/documents/types/list.htm",
                controller: function ($scope, $state, $stateParams) {
                    console.log("doctypes");
                }
            }) //
            .state("isogd.classifiers.docstructure", {
                url: "/classifiers/structure",
                templateUrl: "app2/isogd/classifiers/documents/structure/isogd-docs-structure.htm",
                controller: function ($scope, $state, $stateParams) {
                    console.log("structure: classes, objects and subobjects");
                }
            }) //
            .state("isogd.classifiers.docrepresentations", {
                url: "/classifiers/representations",
                templateUrl: "app2/isogd/classifiers/representation/list.htm",
                controller: function ($scope, $state, $stateParams) {
                    console.log("document representations' classifier");
                }
            })
    }) //
;
