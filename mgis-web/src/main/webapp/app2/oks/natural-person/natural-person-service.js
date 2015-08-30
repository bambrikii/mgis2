angular.module("mgis.oks.person.natural.service", ["ngResource"])
	.factory("NaturalPersonService", function ($resource, $q) {
		var res = $resource('rest/oks/natural-persons/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({
					id: id,
					name: name
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
				res.save({id: item.id}, {
					id: item.id,
					name: item.name,
					firstName: item.firstName,
					surname: item.surname,
					patronymic: item.patronymic
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
	});
