var mgisApp = angular.module("mgis", //
[ "ui.router", //
"mgis.isogd", "mgis.menu.main" ]);

mgisApp.config(function($stateProvider, $urlRouterProvider) {
	// $urlRouterProvider //
	// .when("isogd", function() {
	// console.log("isogd...");
	// }) //
	// .otherwise("/");

	$stateProvider.state("isogd", {
		url : "/isogd",
		templateUrl : "isogd/isogd.htm",
		controller : "ISOGDCtrl"
	})

});
