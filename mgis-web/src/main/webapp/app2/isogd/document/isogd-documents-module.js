angular.module("mgis.isogd.documents", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.documents.service" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider //
	.state("isogd.documents", {
		url : "/sections/:sectionId/volumes/:volumeId/books/:bookId/documents/",
		templateUrl : "app2/isogd/document/isogd-documents-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDDocumentsService, $modal) {
			$scope.stateParams = $stateParams;

			function updateGrid() {
				ISOGDDocumentsService.list($stateParams.bookId).then(function(data) {
					$scope.documents = data.list;
				});
			}
			updateGrid();

			// Document
			$scope.addDocument = function(bookId) {
				$scope.document = {
					id : 0,
					name : "",
					book : {
						id : bookId
					}
				}
				var modalInstance = $modal.open({
					animation : true,
					scope : $scope,
					templateUrl : 'app2/isogd/document/isogd-document-form.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							ISOGDDocumentsService.save(bookId, $scope.document).then(function(data) {
								$modalInstance.close();
								updateGrid();
							});
						}
						$scope.cance = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}

			$scope.editDocument = function(bookId, documentId) {
				ISOGDDocumentsService.get(documentId).then(function(data) {
					$scope.document = data;
					var modalInstance = $modal.open({
						animation : true,
						scope : $scope,
						templateUrl : 'app2/isogd/document/isogd-document-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								ISOGDDocumentsService.save(bookId, $scope.document).then(function(data) {
									$modalInstance.close();
									updateGrid();
								});
							}
							$scope.cancel = function() {
								$modalinstance.dismiss('cancel');
							}
						}
					})
				});
			}

			$scope.removeDocument = function(documentId) {
				var modalInstance = $modal.open({
					templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							ISOGDDocumentsService.remove(documentId).then(function(data) {
								$modalInstance.close();
								updateGrid();
							});
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}

		}
	})
}).controller("ISOGDDocumentsCtrl", function($scope) {

}) //
;

