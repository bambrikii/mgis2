angular.module("mgis.isogd.documents", ["ui.router", "ui.bootstrap", "ngFileUpload", //
	"mgis.commons",
	"mgis.isogd.documents.service", //
	"mgis.isogd.classifiers.documents.types.service", //
	"mgis.isogd.classifiers.documents.structure.services" //
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$stateProvider //
			.state("isogd.structure.documents", {
				data: {
					displayName: "Documents"
				},
				url: "/sections/:sectionId/books/:bookId/volumes/:volumeId/documents/",
				templateUrl: "app2/isogd/document/isogd-documents-list.htm",
				controller: function ($scope, $state, $stateParams, ISOGDDocumentCRUDService, ISOGDDocumentsService, $modal, $rootScope, MGISCommonsModalForm, ISOGDClassifiersDocumentsTypesService) {
					$scope.stateParams = $stateParams;

					function updateGrid() {
						ISOGDDocumentsService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage, $stateParams.volumeId).then(function (data) {
							$scope.documentsPager = data;
						});
					}

					$scope.itemsPerPage = 15;
					$scope.currentPage = 1;
					$scope.pageChanged = function () {
						updateGrid();
					}

					updateGrid();

					// Document
					$scope.addDocument = function (volumeId) {
						ISOGDDocumentCRUDService.addItem(volumeId, updateGrid);
					}

					$scope.editDocument = function (id) {
						ISOGDDocumentCRUDService.editItem(id, updateGrid);
					}

					$scope.removeDocument = function (id) {
						ISOGDDocumentCRUDService.removeItem(id, updateGrid);
					}

				}
			})
	}) //
	.factory("ISOGDDocumentCRUDService", function ($rootScope, MGISCommonsModalForm, ISOGDDocumentsService, ISOGDClassifiersDocumentsTypesService) {
		function openEditDocumentForm(modalScope, volumeId, updateGrid) {
			ISOGDDocumentsService.loadDocumentClassByVolumeId(volumeId).then(function (documentClass) {
				if (documentClass.hasCommonPart) {
					modalScope.commonPartTab = {
						open: true
					}
					modalScope.commonPartUploadComplete = function (data) {
						modalScope.document.commonPart = modalScope.document.commonPart || {};
						modalScope.document.commonPart.documentContents = modalScope.document.commonPart.documentContents || new Array();
						modalScope.document.commonPart.documentContents.push({id: data.id, fileName: data.fileName});
					}
					modalScope.removeCommonContent = function (content) {
						var documentContents = modalScope.document.commonPart.documentContents;
						for (var i = 0; i < documentContents.length; i++) {
							if (documentContents[i] == content) {
								documentContents.splice(i, 1);
							}
						}
					}
				}
				if (documentClass.hasSpecialPart) {
					modalScope.specialPartTab = {
						open: true
					}
					modalScope.specialPartUploadComplete = function (data) {
						modalScope.document.specialPart = modalScope.document.specialPart || {};
						modalScope.document.specialPart.documentContents = modalScope.document.specialPart.documentContents || new Array();
						modalScope.document.specialPart.documentContents.push({id: data.id, fileName: data.fileName});
					}
					modalScope.removeSpecialContent = function (content) {
						var documentContents = modalScope.document.specialPart.documentContents;
						for (var i = 0; i < documentContents.length; i++) {
							if (documentContents[i] == content) {
								documentContents.splice(i, 1);
							}
						}
					}
				}
				MGISCommonsModalForm.edit("app2/isogd/document/isogd-document-form.htm", modalScope,
					function ($scope, $modalInstance) {
						ISOGDDocumentsService.save($scope.document).then(function (data) {
							if (updateGrid) {
								updateGrid();
							}
							$modalInstance.close();
						});
					}, {
						windowClass: "mgis-document-modal-form"
					});
			});
		}

		function addItem(volumeId, updateGrid) {
			ISOGDClassifiersDocumentsTypesService.get().then(function (documentTypes) {
				ISOGDDocumentsService.listDocumentSubObjectsByVolumeId(volumeId).then(function (documentSubObjects) {
					var modalScope = $rootScope.$new();
					modalScope.document = {
						id: 0,
						name: "",
						documentType: {},
						documentSubObject: {},
						volume: {
							id: volumeId
						}
					}
					modalScope.availableDocumentTypes = documentTypes.list;
					modalScope.availableDocumentSubObjects = documentSubObjects.list;
					openEditDocumentForm(modalScope, volumeId, updateGrid)
				});
			});
		}

		function editItem(id, updateGrid) {
			ISOGDClassifiersDocumentsTypesService.get().then(function (documentTypes) {
				ISOGDDocumentsService.get(id).then(function (document) {
					ISOGDDocumentsService.listDocumentSubObjectsByVolumeId(document.volume.id).then(function (documentSubObjects) {
						var modalScope = $rootScope.$new();
						modalScope.document = document;
						modalScope.availableDocumentTypes = documentTypes.list;
						modalScope.availableDocumentSubObjects = documentSubObjects.list;
						openEditDocumentForm(modalScope, document.volume.id, updateGrid);
					});
				});
			});
		}

		function removeItem(id, updateGrid) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				ISOGDDocumentsService.remove(id).then(function (document) {
					if (updateGrid) {
						updateGrid();
					}
					$modalInstance.close();
				});
			});
		}

		return {
			addItem: addItem,
			editItem: editItem,
			removeItem: removeItem
		}
	})
;

