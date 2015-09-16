/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
angular.module("mgis.isogd.classifiers.documents.types.service", ["ngResource", //
	"mgis.error.service" //
]) //
	.factory("ISOGDClassifiersDocumentsTypesService", function ($resource, $q, MGISErrorService) {
		var res = $resource("rest/isogd/classifiers/documents/types/:id.json");
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				console.log(item);
				res.save({id: item.id}, {
					id: item.id,
					code: item.code,
					name: item.name
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({id: id}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	});
