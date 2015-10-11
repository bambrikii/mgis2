angular.module("mgis.geo.geo-server.service", ["ngResource",
	"mgis.error.service"])
	.factory("GEOGeoServerService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/geo/geo-servers/:id.json');
		return {
			get: function (id, first, max, code) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, code: code}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {};
				angular.copy(item, p);
				res.save({id: item.id}, p, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
;
