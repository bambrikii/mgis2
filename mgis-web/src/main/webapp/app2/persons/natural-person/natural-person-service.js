angular.module("mgis.persons.person.natural.service", ["ngResource"])
	.factory("NaturalPersonService", function ($resource, $q) {
		var res = $resource('rest/persons/natural-persons/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, name: name}, {}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {};
				angular.copy(item, p);
				p.actualAddress = item.actualAddress && item.actualAddress.id ? {id: item.actualAddress.id} : null;
				p.legalAddress = item.legalAddress && item.legalAddress.id ? {id: item.legalAddress.id} : null;
				p.activityType = item.activityType && item.activityType.id ? {id: item.activityType.id} : null;
				res.save({id: item.id}, p, function (data) {
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
	});
