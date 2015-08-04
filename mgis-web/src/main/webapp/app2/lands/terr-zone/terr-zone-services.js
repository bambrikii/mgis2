angular.module("mgis.lands.terr-zone.services", [])
	.factory("LandTerrZonesTerrZoneService", function ($resource, $q) {
		var res = $resource('rest/lands/terr_zones/:id.json');
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
	})
;
