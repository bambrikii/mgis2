angular.module("mgis.oks", [ "ui.router" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider //
	.state("mgis.oks.main", {
		url : "/oks/main",
		templateUrl : "oks/oks.main.htm"
	})
}) //
.controller("OKSCtrl", function($state) {
	console.log("oks controller")
}) //
;