angular.module("mgis.lands.lands.service", ["ui.router", 'ngResource'])
	.factory("LandsLandService", function ($resource, $q) {
		var res = $resource('rest/lands/land/:id.json');
		return {
			get: function (id, first, max, cadastralNumber) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max,
					cadastralNumber: cadastralNumber,
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id,}, {
					id: item.id,
					cadastralNumber: item.cadastralNumber
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}

	})
;
