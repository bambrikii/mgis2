angular.module("mgis.isogd.documents.search", ["ui.router",
	"mgis.commons",
	"mgis.isogd.sections.service",
	"mgis.isogd.search.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("isogd.search", {
				url: "/search",
				templateUrl: "app2/isogd/document-search/document-list.htm"
			});
	})
	.controller("ISOGDDocumentSearchController", function ($scope, $rootScope, MGISCommonsModalForm,
														   ISOGDSectionsService,
														   ISOGDDocumentSearchService,
														   ISOGDDocumentSearchConverterService) {
		$scope.searchText = "";
		$scope.searchFilter = {
			section: {}
		}
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		$scope.availableSections = new Array();
		$scope.refreshAvailableSections = function (name) {
			ISOGDSectionsService.get(null, $scope.first, $scope.max, name).then(function (data) {
				$scope.availableSections = data.list;
			});
		}
		$scope.list = function () {
			var filter = $scope.searchFilter;
			ISOGDDocumentSearchService.get(null, ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage, filter.section, filter.docName, filter.docDate, filter.docNumber).then(function (data) {
				$scope.pager = data;
			});
		}
		$scope.searchClick = function () {
			$scope.searchFilter = ISOGDDocumentSearchConverterService.toObject($scope.searchText);
			$scope.list();
		}
		$scope.searchFilterChanged = function () {
			$scope.searchText = ISOGDDocumentSearchConverterService.toString($scope.searchFilter);
			$scope.list();
		}
		$scope.extendedSearchClick = function () {
			var sectionId = $scope.searchFilter.section != null && $scope.searchFilter.section.id ? $scope.searchFilter.section.id : null

			function openExtendedSearchForm(modalScope) {
				MGISCommonsModalForm.edit("app2/isogd/document-search/document-search-form.htm", modalScope, function (scope, modalInstance) {
						modalInstance.close();
					}, {windowClass: "mgis-document-search-modal-form"}
				)
			}

			if (sectionId) {
				ISOGDSectionsService.get(sectionId).then(function (data) {
					$scope.searchFilter.section = data;
					openExtendedSearchForm($scope);
				});
			} else {
				openExtendedSearchForm($scope);
			}
		}
		$scope.pageChanged = function () {
			$scope.list();
		}
		$scope.list();
	})
;
