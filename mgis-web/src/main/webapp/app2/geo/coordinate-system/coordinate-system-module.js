angular.module("mgis.geo.coordinate-system", [
	"mgis.geo.coordinate-system.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo.coordinate-system", {
				url: "/coordinate-systems",
				templateUrl: "app2/geo/coordinate-system/coordinate-system-panel.htm"
			});
	})
	.controller("GEOCoordinateSystemsController", function ($scope, GEOCoordinateSystemService) {
		$scope.createHandler = function (scope, onPrepare) {
			onPrepare({id: 0});
		};
		$scope.readHandler = function (id, scope, onPrepare) {
			GEOCoordinateSystemService.get(id).then(function (item) {
				onPrepare(item);
			});
		};
		$scope.updateHandler = function (item, onComplete) {
			GEOCoordinateSystemService.save(item).then(function () {
				onComplete();
			});
		}
		$scope.deleteHandler = function (id, onComplete) {
			GEOCoordinateSystemService.remove(id).then(function () {
				onComplete();
			});
		};
		$scope.listHandler = function (filter, first, max, onComplete) {
			GEOCoordinateSystemService.get("", first, max, filter.code).then(function (data) {
				onComplete(data);
			});
		};
	})
;
