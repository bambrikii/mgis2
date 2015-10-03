angular.module("mgis.isogd.volumes", ["ui.router", "ui.bootstrap",//
	"mgis.commons",
	"mgis.isogd.volumes.service"]) //
	.config(function ($stateProvider, $urlRouterProvider) {
		$stateProvider//
			.state("isogd.structure.volumes", {
				data: {
					displayName: 'Volumes'
				},
				url: "/sections/:sectionId/books/:bookId/volumes/",
				templateUrl: "app2/isogd/volume/isogd-volumes-list.htm",
				controller: function ($scope, $state, $stateParams, ISOGDVolumesService, $modal, MGISCommonsModalForm, $rootScope) {
					$scope.stateParams = $stateParams;

					function updateGrid() {
						ISOGDVolumesService.get("", 0, 15, $stateParams.bookId).then(function (data) {
							$scope.volumes = data.list;
						});
					}

					function openEditVolumeForm(modalScope) {
						MGISCommonsModalForm.edit("app2/isogd/volume/isogd-volume-form.htm", modalScope, function ($scope, $modalInstance) {
							ISOGDVolumesService.save($scope.volume).then(function (data) {
								updateGrid();
								$modalInstance.close();
							});
						});
					}

					updateGrid();

					// Volume

					$scope.addVolume = function (bookId) {
						var modalScope = $rootScope.$new();
						modalScope.volume = {
							id: 0,
							name: "",
							book: {
								id: bookId
							}
						}
						openEditVolumeForm(modalScope);
					}

					$scope.editVolume = function (volumeId) {
						ISOGDVolumesService.get(volumeId).then(function (data) {
							var modalScope = $rootScope.$new();
							modalScope.volume = data;
							openEditVolumeForm(modalScope);
						});
					}
					$scope.removeVolume = function (volumeId) {
						MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
							ISOGDVolumesService.remove(volumeId).then(function (data) {
								updateGrid();
								$modalInstance.close();
							});
						});
					}
				}
			})//
			.state("isogd.volumes.edit", {
				url: "edit/:volumeId",
				templateUrl: "app2/isogd/volume/isogd-volume-form.htm",
				controller: function ($scope, $state) {
					console.log("edit section...");
				}
			}) //

	}) //
	.controller("ISOGDVolumesCtrl", function ($scope) {
		console.log($scope);
	}) //
;

