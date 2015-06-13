angular.module("mgis.isogd", [ "ui.router", "ui.bootstrap", //
"mgis.isogd.sections.service", "mgis.isogd.sections.module", //
"mgis.isogd.volumes.service", "mgis.isogd.volumes.module", //
"mgis.isogd.books.service", "mgis.isogd.books.module",//
"mgis.isogd.documents.service", "mgis.isogd.documents.module" //
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
.controller("ISOGDCtrl", function($scope, $modal, ISOGDSectionsService, ISOGDVolumesService, ISOGDBooksService, ISOGDDocumentsService) {
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

	// Section
	$scope.addSection = function() {
		console.log("add section");
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'app2/isogd/section/isogd-section-form.htm',
			controller : 'ISOGDSectionsCtrl'// ,
		// size : size,
		// resolve : {
		// items : function() {
		// return $scope.items;
		// }
		// }
		});

	}
	$scope.editSection = function(sectionId) {
		console.log("edit section");
	}
	$scope.removeSection = function(sectionId) {
		console.log("remove section");
	}

	// Volume
	$scope.addVolume = function(sectionId) {
		console.log("add Volume");
	}
	$scope.editVolume = function(volumeId) {
		console.log("edit Volume");
	}
	$scope.removeVolume = function(volumeId) {
		console.log("remove Volume");
	}

	// Book
	$scope.addBook = function(sectionId) {
		console.log("add Book");
	}
	$scope.editBook = function(bookId) {
		console.log("edit Book");
	}
	$scope.removeBook = function(bookId) {
		console.log("add Book");
	}

	// Document
	$scope.addDocument = function() {
		console.log("add Document");
	}
	$scope.editDocument = function() {
		console.log("edit Document");
	}
	$scope.removeDocument = function() {
		console.log("add Document");
	}
});