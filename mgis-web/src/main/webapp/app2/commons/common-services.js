/**
 * Created by asd on 31.07.15.
 */
angular.module("mgis.common.executive_person.service", ["ngResource"])
	.factory("CommonExecutivePersonService", function ($resource, $q) {
		var res = $resource('rest/common/executive_persons/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	})
	.factory("CommonLegalPersonService", function ($resource, $q) {
		var res = $resource('rest/common/legal_persons/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			save: function (data) {

			},
			remove: function (id) {

			}
		}
	})
;
