angular.module("mgis.isogd.document.selector", ["ui.bootstrap", "ui.select", //
	"mgis.commons",
	"mgis.isogd.documents",
	"mgis.isogd.sections.service",
	"mgis.isogd.books.service",
	"mgis.isogd.volumes.service",
	"mgis.isogd.documents.service"
])
	.directive("isogdDocumentSelector", function ($rootScope) {
		return {
			restrict: "E",
			scope: {
				document: "=",
				documents: "=",
				multipleDocuments: "@",
				selectClicked: "&"
			},
			templateUrl: "app2/isogd/document-selector/document-selector-component.htm",
			controller: function ($scope, ISOGDDocumentCRUDService, MGISCommonsModalForm) {
				$scope.openSelector = function () {
					var modalScope = $rootScope.$new();
					modalScope.selectedDocuments = new Array();
					modalScope.multipleDocuments = $scope.multipleDocuments;
					if ($scope.multipleDocuments) {
						if ($scope.documents) {
							modalScope.selectedDocuments = new Array().concat($scope.documents);
						}
					} else {
						if ($scope.document) {
							modalScope.selectedDocuments = new Array().concat($scope.document);
						}
					}

					function selectionCompleteHandler(selectedDocuments) {
						if ($scope.selectClicked) {
							var result = {};
							if ($scope.multipleDocuments) {
								$scope.documents = result.documents = new Array().concat(selectedDocuments);
							} else if (selectedDocuments.length > 0) {
								$scope.document = result.document = selectedDocuments[0];
							}
							$scope.selectClicked(result);
						}
					}

					var modal = MGISCommonsModalForm.edit("app2/isogd/document-selector/document-selector-form.htm", modalScope, function (scope, $modalInstance) {
						selectionCompleteHandler(scope.selectedDocuments);
						$modalInstance.close();
					}, {windowClass: "mgis-document-selector-modal-form"});

					modalScope.selectionCompleteHandler = function (selectedDocuments) {
						selectionCompleteHandler(selectedDocuments);
						modal.close();
					}
				}
				$scope.editDocument = function (id) {
					ISOGDDocumentCRUDService.editItem(id, function () {
						ISOGDDocumentCRUDService.reloadItemInList(id, $scope.documents);
					});
				}
				$scope.deselect = function (id) {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						for (var i in $scope.documents) {
							if ($scope.documents[i].id == id) {
								$scope.documents.splice(i, 1);
							}
						}
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("ISOGDDocumentSelectorController", function ($scope, ISOGDSectionsService, ISOGDBooksService, ISOGDVolumesService, ISOGDDocumentsService, ISOGDDocumentCRUDService) {
		function emptyListAttribute(list, attributeName) {
			for (var i in list) {
				list[i][attributeName].splice(0, list[i][attributeName].length);
			}
			return list;
		}

		$scope.selectedDocuments = $scope.selectedDocuments || new Array();

		$scope.selectSearchItemClick = function (item, updateAction) {
			$scope.documentSelectClicked(item.id, item.name);
		}

		$scope.documentSelectClicked = function (id, name) {
			var item = {id: id, name: name};
			if ($scope.multipleDocuments) {
				var found = false;
				for (var i = 0; i < $scope.selectedDocuments.length; i++) {
					if ($scope.selectedDocuments[i].id == id) {
						found = true;
						break;
					}
				}
				if (!found) {
					$scope.selectedDocuments.push(item);
				}
			} else {
				$scope.selectedDocuments.splice(0, $scope.selectedDocuments.length);
				$scope.selectedDocuments.push(item);
				if ($scope.selectionCompleteHandler) {
					$scope.selectionCompleteHandler($scope.selectedDocuments);
				}
			}
		}

		$scope.removeSelectedDocument = function (item) {
			for (var i = 0; i < $scope.selectedDocuments.length; i++) {
				if ($scope.selectedDocuments[i].id == item.id) {
					$scope.selectedDocuments.splice(i, 1);
				}
			}
		}

		$scope.openSection = function (section) {
			ISOGDBooksService.get("", 0, 0, section.id).then(function (data) {
				section.books = emptyListAttribute(data.list, "volumes");
			});
		}
		$scope.closeSection = function (section) {
			section.books = new Array();
		}
		$scope.openBook = function (book) {
			ISOGDVolumesService.get("", 0, 0, book.id).then(function (data) {
				book.volumes = emptyListAttribute(data.list, "documents");
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

		$scope.addDocument = function (volume) {
			ISOGDDocumentCRUDService.addItem(volume.id, function () {
				$scope.openVolume(volume);
			});
		}
		$scope.editDocument = function (id, volume, updateAction) {
			ISOGDDocumentCRUDService.editItem(id, function () {
				if (volume) {
					$scope.openVolume(volume);
				}
				if (updateAction) {
					updateAction({});
				}
			});
		}
		$scope.removeDocument = function (id, volume, updateAction) {
			ISOGDDocumentCRUDService.removeItem(id, function () {
				if (volume) {
					$scope.openVolume(volume);
				}
				if (updateAction) {
					updateAction({});
				}
			});
		}

		ISOGDSectionsService.get().then(function (data) {
			$scope.sections = emptyListAttribute(data.list, "books");
		});
	})
;
