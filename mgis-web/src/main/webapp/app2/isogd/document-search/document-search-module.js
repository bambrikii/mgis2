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
	.controller("ISOGDDocumentSearchListController", function ($scope, ISOGDDocumentCRUDService) {
		$scope.editDocument = function (id, updateAction) {
			ISOGDDocumentCRUDService.editItem(id, function () {
				updateAction({});
			});
		}
		$scope.removeDocument = function (id, updateAction) {
			ISOGDDocumentCRUDService.removeItem(id, function () {
				updateAction({});
			});
		}

	})
	.directive("isogdDocumentSearch", function () {
		return {
			restrict: "E",
			transclude: true,
			scope: {
				hideGridWhenFilterEmpty: "@",
				filterStateChanged: "&"
			},
			templateUrl: "app2/isogd/document-search/document-search-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm,
								  ISOGDSectionsService,
								  ISOGDDocumentSearchService,
								  ISOGDDocumentSearchConverterService,
								  ISOGDDocumentSearchConstants) {
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
					var filterState = ISOGDDocumentSearchConverterService.filterState(filter);
					if ($scope.hideGridWhenFilterEmpty && filterState == ISOGDDocumentSearchConstants.FILTER_EMPTY) {
						$scope.pager = {};
					} else {
						ISOGDDocumentSearchService.get(null, ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage,
							filter.section,
							filter.docName,
							filter.docDateFrom,
							filter.docDateTill,
							filter.docNumber
						).then(function (data) {
								$scope.pager = data;
							});
					}
					$scope.filterStateChanged({state: filterState});
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
					$scope.filterStateChanged({state: $scope.searchText ? ISOGDDocumentSearchConstants.FILTER_FILLED : ISOGDDocumentSearchConstants.FILTER_EMPTY});
					updateGrid();
				}
				$scope.extendedSearchClick = function () {
					var sectionId = $scope.searchFilter.section != null && $scope.searchFilter.section.id ? $scope.searchFilter.section.id : null;

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
				$scope.resetSearchClick = function () {
					$scope.searchText = "";
					$scope.searchClick();
				}
				$scope.pageChanged = function () {
					updateGrid();
				}
				updateGrid();
			}
		}
	})
	.directive("isogdDocumentSearchFilter", function () {
		return {
			restrict: "E",
			scope: false,
			templateUrl: "app2/isogd/document-search/document-search-component-filter.htm"
		}
	})
	.directive("isgodDocumentSearchButtonContainer", function () {
		return {
			restrict: "AE",
			scope: {
				item: "=",
				updateAction: "&"
			},
			controller: function ($scope) {
				this.item = $scope.item;
				this.updateAction = $scope.updateAction;
			}
		}
	})
	.directive("isgodDocumentSearchButton", function () {
		return {
			restrict: "E",
			require: '^isgodDocumentSearchButtonContainer',
			scope: {
				label: "@",
				class: "@",
				documentItemClick: "&"
			},
			template: '<button class="btn btn-{{class}} btn-sm" ng-click="buttonClicked(item, updateAction)" translate>{{label}}</button>',
			link: function (scope, elem, attrs, isgodDocumentSearchButtonContainerController) {
				scope.item = isgodDocumentSearchButtonContainerController.item;
				scope.updateAction = isgodDocumentSearchButtonContainerController.updateAction;
			},
			controller: function ($scope) {
				$scope.buttonClicked = function (item, updateAction) {
					$scope.documentItemClick({item: item, updateAction: updateAction});
				}
			}
		}
	})
;
