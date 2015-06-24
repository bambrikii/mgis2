/**
 * Created by Alexander Arakelyan on 24.06.15.
 */
angular.module("mgis.isogd.classifiers.documents.representation.service", ["ngResource"
]) //
    .factory("ISOGDClassfiersDocumentsRepresentationFormService", function ($resource, $q) {
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
                });
                return deferred.promise;
            },
            remove: function (id) {
                var deferred = $q.defer();
                res.remove({id: id}, function (result) {
                    deferred.resolve(result);
                });
                return deferred.promise;
            }
        }
    }) //
    .factory("ISOGDClassfiersDocumentsRepresentationFormatService", function ($resource, $q) {
        var res = $resource("rest/isogd/classifiers/documents/representations/formats/:id.json");
        return {
            get: function (id, first, max, representationFormId) {
                var deferred = $q.defer();
                res.get({
                    id: id,
                    first: first,
                    max: max,
                    representationForm: {
                        id: representationFormId
                    }
                }, function (result) {
                    deferred.resolve(result);
                });
                return deferred.promise;
            },
            save: function (item) {
                var deferred = $q.defer();
                res.save({id: item.id}, {
                    id: item.id,
                    code: item.code,
                    name: item.name,
                    representationForm: item.representationForm
                }, function (result) {
                    deferred.resolve(result);
                });
                return deferred.promise;
            },
            remove: function (id) {
                var deferred = $q.defer();
                res.remove({
                    id: id
                }, function (result) {
                    deferred.resolve(result);
                });
                return deferred.promise;
            }
        }
    }) //

;
