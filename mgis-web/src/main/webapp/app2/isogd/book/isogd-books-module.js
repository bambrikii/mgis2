angular.module("mgis.isogd.books", ["ui.router", "ui.bootstrap", //
	"mgis.commons",
	"mgis.isogd.books.service" //
]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$stateProvider//
			.state("isogd.structure.books", {
				url: "/sections/:sectionId/books/",
				templateUrl: "app2/isogd/book/isogd-books-list.htm",
				controller: function ($scope, $state, $stateParams, ISOGDBooksService, $modal, MGISCommonsModalForm, $rootScope) {
					$scope.stateParams = $stateParams;

					function updateGrid() {
						return ISOGDBooksService.get("", 0, 15, $stateParams.sectionId).then(function (data) {
							$scope.books = data.list;
						});
					}

					updateGrid();

					// Book
					function openEditBookForm(modalScope) {
						MGISCommonsModalForm.edit("app2/isogd/book/isogd-book-form.htm", modalScope, function ($scope, $modalInstance) {
							$modalInstance.close();
							ISOGDBooksService.save(modalScope.book).then(function (data) {
								updateGrid();
							});
						});
					}

					$scope.addBook = function (sectionId) {
						ISOGDBooksService.listDocumentObjectsBySectionId(sectionId).then(function (documentObjects) {
							var modalScope = $rootScope.$new();
							modalScope.book = {
								id: 0,
								name: "",
								section: {
									id: sectionId
								}
							}
							modalScope.availableDocumentObjects = documentObjects.list;
							openEditBookForm(modalScope);
						})
					}

					$scope.editBook = function (id) {
						ISOGDBooksService.get(id).then(function (book) {
							ISOGDBooksService.listDocumentObjectsBySectionId(book.section.id).then(function (documentObjects) {
								var modalScope = $rootScope.$new();
								modalScope.book = book;
								modalScope.availableDocumentObjects = documentObjects.list;
								openEditBookForm(modalScope);
							});
						});
					}

					$scope.removeBook = function (id) {
						MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
								$modalInstance.close();
								ISOGDBooksService.remove(id).then(function (data) {
									updateGrid();
								})
							}
						);
					}
				}
			})

	}).controller("ISOGDBooksCtrl", function ($scope) {

	}) //
;

