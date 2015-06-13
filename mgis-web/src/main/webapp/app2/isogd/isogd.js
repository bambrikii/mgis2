angular.module("mgis.isogd", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.sections", "mgis.isogd.volumes", "mgis.isogd.books", "mgis.isogd.documents" //
]) //
.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider.state("isogd.sections", {
		url : "/isogd/sections",
		templateUrl : "isogd/isogd.section.list.htm",
		controller : function($scope, $state) {
			console.log("sections...");
		}
	})//
	.state("isogd.sections.edit", {
		url : "/isogd/sections/:sectionId",
		templateUrl : "isogd/isogd.section.edit.htm",
		controller : function($scope, $state) {
			console.log("sections.edit...");
		}
	}) //
	; //
}) //
.controller("ISOGDCtrl", function($scope, ISOGDSectionsService, ISOGDVolumesService, ISOGDBooksService, ISOGDDocumentsService) {
	console.log("isogd");
	$scope.sayA = function() {
		alert("aaa");
	}
	$scope.sections = ISOGDSectionsService.list();

	$scope.volumes = function(sectionId) {
		return ISOGDVolumesService.list(sectionId);
	}
	$scope.books = function(volumeId) {
		return ISOGDBooksService.list(volumeId);
	}
	$scope.documents = function(bookId) {
		return ISOGDDocumentsService.list(bookId);
	}
});