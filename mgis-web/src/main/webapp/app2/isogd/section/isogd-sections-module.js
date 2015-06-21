angular.module("mgis.isogd.sections", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.sections.service"//
]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider//
	.state("isogd.sections", {
		url : "/sections/:sectionId",
		templateUrl : "app2/isogd/section/isogd-sections-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDSectionsService, $modal, $q) {
			$scope.stateParams = $stateParams;

			function updateGrid() {
				ISOGDSectionsService.list(0, 15).then(function(data) {
					$scope.sections = data.list;
				});
			}
			updateGrid();

			// Section
			$scope.addSection = function() {
				$scope.section = {
					id : 0,
					name : ""
				};
				var modalInstance = $modal.open({
					animation : true,
					scope : $scope,
					templateUrl : 'app2/isogd/section/isogd-section-form.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							ISOGDSectionsService.save($scope.section).then(function(data) {
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
			$scope.editSection = function(sectionId) {
				ISOGDSectionsService.get(sectionId).then(function(data) {
					$scope.section = data;
					var modalInstance = $modal.open({
						scope : $scope,
						templateUrl : 'app2/isogd/section/isogd-section-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								$modalInstance.close();
								ISOGDSectionsService.save($scope.section).then(function(data) {
									updateGrid();
								});
							}
							$scope.cancel = function() {
								$modalInstance.dismiss('cancel');
							}
						}
					});
				});
			}
			$scope.removeSection = function(sectionId) {
				var modalInstance = $modal.open({
					templateUrl : 'app2/common/confirm-deletion.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close();
							ISOGDSectionsService.remove(sectionId).then(function(data) {
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
	})//
	;
}) //
.controller("ISOGDSectionCtrl", function($scope, ISOGDSectionsService) {
	$scope.sectionIsOpen = false;
	$scope.volumes1 = [];
	$scope.$watch("sectionIsOpen", function(newValue, oldValue, scope) {
		console.log("isOpenChanged: " + newValue + ", " + oldValue + ", " + scope.section.id);
		if (newValue) {
			// TODO: load volumes
			$scope.volumes1.push("asd");
			console.log($scope.volumes1);
		}
	})
})//
;

angular.module("mgis.isogd.sections.module", [ "ui.router", "ui.bootstrap" ]) //
.config(function($stateProvider, $urlRouterProvider) {

}) // https://angular-ui.github.io/bootstrap/
;

