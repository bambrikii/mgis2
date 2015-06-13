angular.module("mgis.isogd", [ "ui.router", "ui.bootstrap", //
"mgis.common", //
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
			animation : true,
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
		var modalInstance = $modal.open({
			templateUrl : 'app2/isogd/section/isogd-section-form.htm',
			controller : 'ISOGDSectionsCtrl'
		});
	}
	$scope.removeSection = function(sectionId) {
		var modalInstance = $modal.open({
			templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
			controller : function($scope, $modalInstance) {
				$scope.ok = function() {
					$modalInstance.close("");
					console.log("remove section");
					// TODO:
				}
				$scope.cancel = function() {
					$modalInstance.dismiss('cancel');
				}
			}
		});
	}

	// Volume
	$scope.addVolume = function(sectionId) {
		console.log("add Volume");
	}
	$scope.editVolume = function(volumeId) {
		console.log("edit Volume");
	}
	$scope.removeVolume = function(volumeId) {
		var modalInstance = $modal.open({
			templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
			controller : function($scope, $modalInstance) {
				$scope.ok = function() {
					$modalInstance.close("");
					console.log("remove Volume");
					// TODO:
				}
				$scope.cancel = function() {
					$modalInstance.dismiss('cancel');
				}
			}
		});
	}

	// Book
	$scope.addBook = function(sectionId) {
		console.log("add Book");
	}
	$scope.editBook = function(bookId) {
		console.log("edit Book");
	}
	$scope.removeBook = function(bookId) {
		var modalInstance = $modal.open({
			templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
			controller : function($scope, $modalInstance) {
				$scope.ok = function() {
					$modalInstance.close("");
					console.log("remove Book");
					// TODO:
				}
				$scope.cancel = function() {
					$modalInstance.dismiss('cancel');
				}
			}
		});
	}

	// Document
	$scope.addDocument = function() {
		console.log("add Document");
	}
	$scope.editDocument = function() {
		console.log("edit Document");
	}
	$scope.removeDocument = function() {
		var modalInstance = $modal.open({
			templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
			controller : function($scope, $modalInstance) {
				$scope.ok = function() {
					$modalInstance.close("");
					console.log("remove Document");
					// TODO:
				}
				$scope.cancel = function() {
					$modalInstance.dismiss('cancel');
				}
			}
		});
	}
});