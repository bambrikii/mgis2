angular.module("mgis.oks.address.service", ["ngResource"])
	.factory("OKSAddressService", function ($resource, $q) {
		var res = $resource('rest/oks/addresses/:id.json');
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
			}
		}
	})
;
