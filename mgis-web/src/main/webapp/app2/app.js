var mgisApp = angular.module("mgis", //
[ "ui.router", //
"mgis.isogd", "mgis.menu.main", "mgis.oks" ]);

mgisApp.config(function($stateProvider, $urlRouterProvider) {
	// $urlRouterProvider //
	// .when("isogd", function() {
	// console.log("isogd...");
	// }) //
	// .otherwise("/");

	$stateProvider //
	.state("isogd", {
		url : "/isogd",
		templateUrl : "app2/isogd/isogd.htm",
		controller : "ISOGDCtrl"
	}) //
	.state("oks", {
		url : "/oks",
		templateUrl : "app2/oks/oks.htm",
		controller : "OKSCtrl"
	}) //
	;

});
