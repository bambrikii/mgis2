angular.module("mgis.isogd", [ "ui.router", "ui.bootstrap", "angularUtils.directives.uiBreadcrumbs", //
"mgis.common", //
"mgis.isogd.sections", "mgis.isogd.sections.service", //
"mgis.isogd.volumes", "mgis.isogd.volumes.service", //
"mgis.isogd.books", "mgis.isogd.books.service",//
"mgis.isogd.documents", "mgis.isogd.documents.service" //
]) //
.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.when("/isogd/sections", "/isogd/sections/").when("/isogd", "/isogd/sections/");

	// $stateProvider//
	// .state("isogd.sections", {
	// url: "/sections/",
	// templateUrl: "app2/isogd/section/isogd-sections-list.htm",
	// controller: function ($scope, $state) {
	// console.log("sections...");
	// }
	// }) //
	// ;
}) //
.controller("ISOGDCtrl", function($scope, $modal, ISOGDSectionsService, ISOGDVolumesService, ISOGDBooksService, ISOGDDocumentsService) {
}) //
.directive("breadcrumb", function() {
	// element, ui-sref/href
	return {
		restrict : "E",
		scope : {
			section : "&",
			book : "&",
			volume : "&"
		},
		template : "<ol class=\"breadcrumb\"><li><a href=\"#\">Home</a></li><li><a href=\"#\">Library</a></li><li class=\"active\">Data</li></ol>"
	}
});
