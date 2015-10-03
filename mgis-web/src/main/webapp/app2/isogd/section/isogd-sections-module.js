angular.module("mgis.isogd.sections", ["ui.router", "ui.bootstrap",//
	"mgis.commons",
	"mgis.isogd.sections.service",
	"mgis.isogd.classifiers.documents.structure.services",
	"mgis.isogd.search.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("isogd.structure.sections", {
				url: "/sections/:sectionId",
				templateUrl: "app2/isogd/section/isogd-sections-list.htm",
				controller: function ($scope, $rootScope, $state, $stateParams, ISOGDSectionsService, $modal, $q, MGISCommonsModalForm, ISOGClassifiersDocumentsClassesService) {
					$scope.stateParams = $stateParams;

					function updateGrid() {
						ISOGDSectionsService.get("", 0, 15).then(function (data) {
							$scope.sections = data.list;
						});
					}

					function editSectionModal(modalScope) {
						MGISCommonsModalForm.edit("app2/isogd/section/isogd-section-form.htm", modalScope, function (scope, $modalInstance) {
							ISOGDSectionsService.save(scope.section).then(function (data) {
								$modalInstance.close();
								updateGrid();
							});
						});
					}

					updateGrid();

					// Section
					$scope.addSection = function () {
						ISOGClassifiersDocumentsClassesService.get().then(function (documentClassesData) {
							var modalScope = $rootScope.$new();
							modalScope.section = {
								id: 0,
								name: "",
								documentClass: {}
							};
							modalScope.availableDocumentClasses = documentClassesData.list;
							editSectionModal(modalScope);
						});
					}
					$scope.editSection = function (sectionId) {
						ISOGDSectionsService.get(sectionId).then(function (sectionsData) {
							ISOGClassifiersDocumentsClassesService.get().then(function (documentClassesData) {
								var modalScope = $rootScope.$new();
								modalScope.section = sectionsData;
								modalScope.availableDocumentClasses = documentClassesData.list;
								editSectionModal(modalScope);
							});
						});
					}
					$scope.removeSection = function (sectionId) {
						MGISCommonsModalForm.confirmRemoval(
							function ($modalInstance) {
								$modalInstance.close();
								ISOGDSectionsService.remove(sectionId).then(function (data) {
									updateGrid();
								});
							}
						);
					}
				}
			});
	})
	.controller("ISOGDSectionsController", function ($scope, ISOGDDocumentSearchConstants) {
		$scope.showSections = true;
		$scope.searchFilterChanged = function (state) {
			$scope.showSections = state == ISOGDDocumentSearchConstants.FILTER_EMPTY;
		}
	})
;

