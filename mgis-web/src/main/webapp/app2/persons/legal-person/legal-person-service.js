angular.module("mgis.persons.person.legal.service", ["ngResource"])
	.factory("LegalPersonService", function ($resource, $q) {
		var res = $resource('rest/persons/legal-persons/:id.json');
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
				res.save({id: item.id,}, {
					id: item.id,
					name: item.name
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
