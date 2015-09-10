angular.module("mgis.address.service", ["ngResource"])
	.factory("AddressService", function ($resource, $q) {
		var res = $resource('rest/addresses/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({
					id: id
				}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id,}, {
					id: item.id
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
			},
		}
	})
	.factory("AddressElementSearchService", function ($resource, $q) {
		var res = $resource("rest/addresses/elements/:type/:id.json");
		return {
			get: function (id, first, max, filter) {
				var deferred = $q.defer();
				var params = angular.extend({id: id, first: first, max: max}, filter);
				res.get(params, {}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	})
;
