angular.module("mgis.isogd.volumes", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.volumes.service" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider//
	.state("isogd.volumes", {
        data: {
            displayName: 'Volumes'
        },
		url : "/sections/:sectionId/books/:bookId/volumes/",
		templateUrl : "app2/isogd/volume/isogd-volumes-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDVolumesService, $modal) {
			$scope.stateParams = $stateParams;

			function updateGrid() {
				ISOGDVolumesService.list($stateParams.bookId, 0, 15).then(function(data) {
					$scope.volumes = data.list;
				});
			}
			updateGrid();

			// Volume
			$scope.addVolume = function(bookId) {
				$scope.volume = {
					id : 0,
					name : "",
					book : {
						id : bookId
					}
				}
				var modalInstance = $modal.open({
					animation : true,
					scope : $scope,
					templateUrl : 'app2/isogd/volume/isogd-volume-form.htm',
					controller : function($scope, $modalInstance) {
						console.log("volume");
						$scope.ok = function() {
							$modalInstance.close();
							ISOGDVolumesService.save(bookId, $scope.volume).then(function(data) {
								updateGrid();
							});
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}

			$scope.editVolume = function(bookId, volumeId) {
				ISOGDVolumesService.get(volumeId).then(function(data) {
					$scope.volume = data;
					var modalInstance = $modal.open({
						animation : true,
						scope : $scope,
						templateUrl : 'app2/isogd/volume/isogd-volume-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								ISOGDVolumesService.save(bookId, $scope.volume).then(function(data) {
									$modalInstance.close();
									updateGrid();
								})
							}, $scope.cancel = function() {
								$modalInstance.dismiss('cancel');
							}
						}
					});
				});
			}
			$scope.removeVolume = function(volumeId) {
				var modalInstance = $modal.open({
					templateUrl : 'app2/common/confirm-deletion.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close("");
							console.log("remove Volume");
							ISOGDVolumesService.remove(volumeId).then(function(data) {
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
	})//
	.state("isogd.volumes.edit", {
		url : "edit/:volumeId",
		templateUrl : "app2/isogd/volume/isogd-volume-form.htm",
		controller : function($scope, $state) {
			console.log("edit section...");
		}
	}) //

}) //
.controller("ISOGDVolumesCtrl", function($scope) {
	console.log($scope);
}) //
;

