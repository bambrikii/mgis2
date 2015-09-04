angular.module("mgis.isogd.document.selector", ["ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.isogd.sections.service",
	"mgis.isogd.books.service",
	"mgis.isogd.volumes.service",
	"mgis.isogd.documents.service"
])
	.directive("isogdDocumentSelector", function ($rootScope, MGISCommonsModalForm) {
		return {
			restrict: "E",
			scope: {
				document: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/isogd/document-selector/document-selector-component.htm",
			controller: function ($scope) {
				$scope.openSelector = function (document) {
					var modalScope = $rootScope.$new();
					modalScope.document = angular.extend({}, document);
					var modal = MGISCommonsModalForm.edit("app2/isogd/document-selector/document-selector-form.htm", modalScope, function (scope, $modalInstance) {
						$modalInstance.close();
					}, {windowClass: "mgis-document-selector-modal-form"});
					modalScope.documentSelectClicked = function (id, name) {
						var document = {id: id, name: name};
						$scope.document = document;
						if ($scope.selectClicked) {
							$scope.selectClicked(document);
						}
						modal.close();
					}
				}
			}
		}
	})
	.controller("ISOGDDocumentSelectorController", function ($scope, ISOGDSectionsService, ISOGDBooksService, ISOGDVolumesService, ISOGDDocumentsService) {
		function emptyList(list, propertyName) {
			for (var i in list) {
				list[i][propertyName].splice(0, list[i][propertyName].length);
			}
			return list;
		}

		$scope.openSection = function (section) {
			ISOGDBooksService.get("", 0, 0, section.id).then(function (data) {
				section.books = emptyList(data.list, "volumes");
			});
		}
		$scope.closeSection = function (section) {
			section.books = new Array();
		}
		$scope.openBook = function (book) {
			ISOGDVolumesService.get("", 0, 0, book.id).then(function (data) {
				book.volumes = emptyList(data.list, "documents");
			});
		}
		$scope.closeBook = function (book) {
			book.volumes = new Array();
		}
		$scope.openVolume = function (volume) {
			ISOGDDocumentsService.get("", 0, 0, volume.id).then(function (data) {
				volume.documents = data.list;
			});
		}
		$scope.closeVolume = function (volume) {
			volume.documents = new Array();
		}

		ISOGDSectionsService.get().then(function (data) {
			$scope.sections = emptyList(data.list, "books");
		});
	})
;
