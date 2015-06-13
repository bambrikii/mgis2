angular.module("mgis.isogd.sections.module", [ "ui.router", "ui.bootstrap" ]) //
.config(function($stateProvider, $urlRouterProvider) {

}) // https://angular-ui.github.io/bootstrap/
.controller("ISOGDSectionsCtrl", function($scope, $modalInstance) {
	$scope.ok = function() {
		$modalInstance.close(/* $scope.selected.item */);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}) //
;

