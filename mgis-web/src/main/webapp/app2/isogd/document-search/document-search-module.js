angular.module("mgis.isogd.documents.search", ["ui.router",
	"mgis.commons",
	"mgis.isogd.sections.service",
	"mgis.isogd.search.service",
	"mgis.isogd.documents"
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
														   ISOGDDocumentSearchConverterService,
														   ISOGDDocumentCRUDService) {
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
		function updateGrid() {
			var filter = $scope.searchFilter;
			ISOGDDocumentSearchService.get(null, ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage, filter.section, filter.docName, filter.docDate, filter.docNumber).then(function (data) {
				$scope.pager = data;
			});
		}

		$scope.list = function () {
			updateGrid();
		}
		$scope.searchClick = function () {
			$scope.searchFilter = ISOGDDocumentSearchConverterService.toObject($scope.searchText);
			updateGrid();
		}
		$scope.searchFilterChanged = function () {
			$scope.searchText = ISOGDDocumentSearchConverterService.toString($scope.searchFilter);
			updateGrid();
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
			updateGrid();
		}
		updateGrid();


		$scope.editDocument = function (id) {
			ISOGDDocumentCRUDService.editItem(id, updateGrid);
		}
		$scope.removeDocument = function (id) {
			ISOGDDocumentCRUDService.removeItem(id, updateGrid);
		}

		$scope.documentItemClicked = function (item, action) {
			switch (action) {
				case 'edit':
					ISOGDDocumentCRUDService.editItem(item.id, updateGrid);
					break;
				case 'remove':
					ISOGDDocumentCRUDService.removeItem(item.id, updateGrid);
					break;
			}
		}
	})
	.directive("isogdDocumentSearch", function () {
		return {
			restrict: "E",
			transclude: true,
			//scope: {
			//	buttonsTemplateUrl: "@",
			//	itemClick: "&"
			//},
			templateUrl: "app2/isogd/document-search/document-search-component.htm"//,
			//controller: function ($scope) {
			//}
		}
	})
	.directive("isgodDocumentSearchButton", function () {
		return {
			restrict: "E",
			require: '^parent',
			scope: {
				label: "@",
				action: "@",
				item: "=item",
				documentItemClick: "&"
			},
			template: '<button class="btn btn-warning btn-sm" item="item" ng-click="buttonClicked(item,action)" translate>{{label}}</button>',
			controller: function ($scope) {
				$scope.buttonClicked = function (item, action) {
					$scope.documentItemClick({item: item, action: action});
				}
			}
		}
	})
;
