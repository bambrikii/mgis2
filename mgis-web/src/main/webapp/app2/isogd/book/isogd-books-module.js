angular.module("mgis.isogd.books", [ "ui.router", "ui.bootstrap", //
"mgis.isogd.books.service" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider//
	.state("isogd.books", {
		url : "/sections/:sectionId/books/",
		templateUrl : "app2/isogd/book/isogd-books-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDBooksService, $modal) {
			$scope.stateParams = $stateParams;

			function updateGrid() {
				return ISOGDBooksService.list($stateParams.sectionId, 0, 15).then(function(data) {
					$scope.books = data.list;
				});
			}
			updateGrid();

			// Book
			$scope.addBook = function(sectionId) {
				$scope.book = {
					id : 0,
					name : "",
					volume : {
						id : sectionId
					}
				}
				var modalInstance = $modal.open({
					animation : true,
					scope : $scope,
					templateUrl : 'app2/isogd/book/isogd-book-form.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close();
							ISOGDBooksService.save(sectionId, $scope.book).then(function(data) {
								updateGrid();
							});
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}

			$scope.editBook = function(sectionId, bookId) {
				console.log("edit Book");
				ISOGDBooksService.get(bookId).then(function(data) {
					$scope.book = data;
					var modalInstance = $modal.open({
						animation : true,
						scope : $scope,
						templateUrl : 'app2/isogd/book/isogd-book-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								ISOGDBooksService.save(sectionId, $scope.book).then(function(data) {
									$modalInstance.close(/* $scope.selected.item */);
									updateGrid();
								})
							}, $scope.cancel = function() {
								$modalInstance.dismiss('cancel');
							}
						}
					});
				});
			}

			$scope.removeBook = function(bookId) {
				var modalInstance = $modal.open({
					templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close("");
							console.log("remove Book");
							ISOGDBooksService.remove(bookId).then(function(data) {
								updateGrid();
							})
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}
		}
	})

}).controller("ISOGDBooksCtrl", function($scope) {

}) //
;

