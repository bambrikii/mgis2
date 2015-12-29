/**
 * Created by donchenko-y on 12/24/15.
 */

angular.module("mgis.persons.executive.person.list.service", ["ngResource",
	"mgis.error.service"
])
	.factory("ExecutivePersonService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/persons/executive-persons/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, name: name}, {}, function (data) {
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
	});
