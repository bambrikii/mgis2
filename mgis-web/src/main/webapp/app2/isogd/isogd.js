angular.module(
		"mgis.isogd",
		[ "ui.router", //
		"mgis.isogd.sections", "mgis.isogd.volumes", "mgis.isogd.books",
				"mgis.isogd.documents" //
		]) //
.controller(
		"ISOGDCtrl",
		function($scope, ISOGDSectionsService, ISOGDVolumesService,
				ISOGDBooksService, ISOGDDocumentsService) {
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