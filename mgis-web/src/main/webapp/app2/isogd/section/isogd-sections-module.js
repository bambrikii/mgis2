angular.module("mgis.isogd.sections", [ "ui.router", "ui.bootstrap",//
"mgis.isogd.sections.service"//
]) //
.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider//
	.state("isogd.sections", {
		url : "/sections/:sectionId",
		templateUrl : "app2/isogd/section/isogd-sections-list.htm",
		controller : function($scope, $state, $stateParams, ISOGDSectionsService, $modal, $q) {
			console.log("sections...");
			$scope.stateParams = $stateParams;

			var promise = ISOGDSectionsService.list(0, 15);
			promise.then(function(data) {
				console.log("aaaaa");
				console.log($scope.sections = data.list);
			});

			// Section
			$scope.addSection = function() {
				console.log("add section");
				var modalInstance = $modal.open({
					animation : true,
					templateUrl : 'app2/isogd/section/isogd-section-form.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close(/* $scope.selected.item */);
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}// ,
				// size : size,
				// resolve : {
				// items : function() {
				// return $scope.items;
				// }
				// }
				});

			}
			$scope.editSection = function(sectionId) {
				console.log("edit section");
				ISOGDSectionsService.get(sectionId).then(function(data) {
					$scope.section = data;
					var modalInstance = $modal.open({
						scope : $scope,
						templateUrl : 'app2/isogd/section/isogd-section-form.htm',
						controller : function($scope, $modalInstance) {
							$scope.ok = function() {
								$modalInstance.close(/* $scope.selected.item */);
								ISOGDSectionsService.save($scope.section);
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
					templateUrl : 'app2/isogd/isogd-confirm-deletion.htm',
					controller : function($scope, $modalInstance) {
						$scope.ok = function() {
							$modalInstance.close("");
							console.log("remove section");
							// TODO:
						}
						$scope.cancel = function() {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			}

		}
	})//
	.state("isogd.sections.edit", {
		url : "edit/:sectionId",
		templateUrl : "app2/isogd/section/isogd-section-form.htm",
		controller : function($scope, $state) {
			console.log("edit section...");
		}
	}) //
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

