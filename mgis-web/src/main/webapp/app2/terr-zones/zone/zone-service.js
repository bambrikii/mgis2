angular.module("mgis.terr-zones.zone.service", [])
	.factory("TerrZonesZoneService", function ($resource, $q) {
		var res = $resource('rest/terr-zones/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id, name: name}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	}) //
;
