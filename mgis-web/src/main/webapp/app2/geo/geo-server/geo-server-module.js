angular.module("mgis.geo.geo-server", ["ui.router",
	"mgis.commons.crud",
	"mgis.geo.geo-server.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo.geo-server", {
				url: "/geo-servers",
				templateUrl: "app2/geo/geo-server/geo-server-panel.htm"
			});
	})
	.controller("GEOGeoServerController", function ($scope, GEOGeoServerService) {

		$scope.createHandler = function (scope, onPrepare) {
			onPrepare({id: 0});
		};
		$scope.readHandler = function (id, scope, onPrepare) {
			GEOGeoServerService.get(id).then(function (item) {
				onPrepare(item);
			});
		};
		$scope.updateHandler = function (item, onComplete) {
			GEOGeoServerService.save(item).then(function () {
				onComplete();
			});
		}
		$scope.deleteHandler = function (id, onComplete) {
			GEOGeoServerService.remove(id).then(function () {
				onComplete();
			});
		};
		$scope.listHandler = function (filter, first, max, onComplete) {
			GEOGeoServerService.get("", first, max, filter.code).then(function (data) {
				onComplete(data);
			});
		};
	})
;
