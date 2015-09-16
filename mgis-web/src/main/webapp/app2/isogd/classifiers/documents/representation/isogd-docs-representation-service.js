/**
 * Created by Alexander Arakelyan on 24.06.15.
 */
angular.module("mgis.isogd.classifiers.documents.representation.service", ["ngResource",
	"mgis.error.service"
]) //
	.factory("ISOGDClassfiersDocumentsRepresentationFormService", function ($resource, $q, MGISErrorService) {
		var res = $resource("rest/isogd/classifiers/documents/representations/forms/:id.json");
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id}, {
					id: item.id,
					code: item.code,
					name: item.name,
					representationFormats: item.representationFormats
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
	}) //
	.factory("ISOGDClassfiersDocumentsRepresentationFormatService", function ($resource, $q, MGISErrorService) {
		var res = $resource("rest/isogd/classifiers/documents/representations/formats/:id.json");
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id}, {
					id: item.id,
					code: item.code,
					name: item.name,
					representationForm: item.representationForm,
					formats: item.formats
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	}) //

;
