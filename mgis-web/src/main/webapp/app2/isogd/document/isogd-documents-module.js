angular.module("mgis.isogd.documents", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.documents.service" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider //
	.state("isogd.documents", {
		data : {
			displayName : "Documents"
		},
		url : "/sections/:sectionId/books/:bookId/volumes/:volumeId/documents/",
		templateUrl : "app2/isogd/document/isogd-documents-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDDocumentsService, $modal) {
			$scope.stateParams = $stateParams;

			function updateGrid() {
				ISOGDDocumentsService.list($stateParams.volumeId).then(function(data) {
					$scope.documents = data.list;
				});
			}
			updateGrid();

			// Document
			$scope.addDocument = function(volumeId) {
				$scope.document = {
					id : 0,
					name : "",
					book : {
						id : volumeId
					}
				}
				var modalInstance = $modal.open({
					animation : true,
					scope : $scope,
					templateUrl : 'app2/isogd/document/isogd-document-form.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							ISOGDDocumentsService.save(volumeId, $scope.document).then(function(data) {
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

			$scope.editDocument = function(volumeId, documentId) {
				ISOGDDocumentsService.get(documentId).then(function(data) {
					$scope.document = data;
					var modalInstance = $modal.open({
						animation : true,
						scope : $scope,
						templateUrl : 'app2/isogd/document/isogd-document-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								ISOGDDocumentsService.save(volumeId, $scope.document).then(function(data) {
									$modalInstance.close();
									updateGrid();
								});
							}
							$scope.cancel = function() {
								$modalInstance.dismiss('cancel');
							}
						}
					})
				});
			}

			$scope.removeDocument = function(documentId) {
				var modalInstance = $modal.open({
					templateUrl : 'app2/common/confirm-deletion.htm',
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

