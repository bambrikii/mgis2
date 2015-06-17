angular.module("mgis.isogd.books", [ "ui.router", "ui.bootstrap", //
"mgis.isogd.books.service" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider//
	.state("isogd.books", {
		url : "/sections/:sectionId/volumes/:volumeId/books/",
		templateUrl : "app2/isogd/book/isogd-books-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDBooksService, $modal) {
			console.log("books...");
			$scope.stateParams = $stateParams;

			function updateGrid() {
				return ISOGDBooksService.list($stateParams.volumeId, 0, 15).then(function(data) {
					$scope.books = data.list;
				});
			}
			updateGrid();

			// Book
			$scope.addBook = function(volumeId) {
				console.log("add Book");
				$scope.book = {
					id : 0,
					name : "",
					volume : {
						id : volumeId
					}
				}
				var modalInstance = $modal.open({
					animation : true,
					scope : $scope,
					templateUrl : 'app2/isogd/book/isogd-book-form.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close();
							ISOGDBooksService.save(volumeId, $scope.book).then(function(data) {
								updateGrid();
							});
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}

			$scope.editBook = function(volumeId, bookId) {
				console.log("edit Book");
				ISOGDBooksService.get(bookId).then(function(data) {
					$scope.book = data;
					var modalInstance = $modal.open({
						animation : true,
						scope : $scope,
						templateUrl : 'app2/isogd/book/isogd-book-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								ISOGDBooksService.save(volumeId, $scope.book).then(function(data) {
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

