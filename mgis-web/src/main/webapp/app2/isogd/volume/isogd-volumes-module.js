angular.module("mgis.isogd.volumes", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.volumes.service" ]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider//
	.state("isogd.volumes", {
		url : "/sections/:sectionId/volumes/",
		templateUrl : "app2/isogd/volume/isogd-volumes-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDVolumesService, $modal) {
			$scope.stateParams = $stateParams;

			function updateGrid() {
				ISOGDVolumesService.list($stateParams.sectionId, 0, 15).then(function(data) {
					$scope.volumes = data.list;
				});
			}
			updateGrid();

			// Volume
			$scope.addVolume = function(sectionId) {
				console.log("add Volume");
				console.log("sectionId: " + sectionId);
				$scope.volume = {
					id : 0,
					name : "",
					section : {
						id : sectionId
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
							ISOGDVolumesService.save(sectionId, $scope.volume).then(function(data) {
								updateGrid();
							});
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}
			$scope.editVolume = function(sectionId, volumeId) {
				console.log("edit Volume");
				console.log(arguments);
				ISOGDVolumesService.get(volumeId).then(function(data) {
					$scope.volume = data;
					var modalInstance = $modal.open({
						animation : true,
						scope : $scope,
						templateUrl : 'app2/isogd/volume/isogd-volume-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								ISOGDVolumesService.save(sectionId, $scope.volume).then(function(data) {
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
			$scope.removeVolume = function(volumeId) {
				var modalInstance = $modal.open({
					templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
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

