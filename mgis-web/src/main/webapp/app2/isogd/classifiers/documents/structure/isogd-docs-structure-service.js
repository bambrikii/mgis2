/**
 * Created by Alexander Arakelyan on 22/06/15.
 */
angular.module("mgis.isogd.classifiers.documents.structure.services", ["ngResource"]) //
    .factory("ISOGClassifiersDocumentsClassesService", function ($resource, $q) {
        var res = $resource("rest/isogd/classifiers/documents/classes/:id.json");
        return {
            get: function (id, first, max) {
                var deferred = $q.defer();
                res.get({id: id, first: first, max: max}, function (result) {
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
                    hasCommonPart: item.hasCommonPart,
                    hasSpecialPart: item.hasSpecialPart
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
    .factory("ISOGClassifiersDocumentsObjectsService", function ($resource, $q) {
        var res = $resource("rest/isogd/classifiers/documents/objects/:id.json");
        return {
            get: function (id, first, max) {
                var deferred = $q.defer();
                res.get({id: id, first: first, max: max}, function (result) {
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
                    documentClass: item.documentClass
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
    .factory("ISOGClassifiersDocumentsSubObjectsService", function ($resource, $q) {
        var res = $resource("rest/isogd/classifiers/documents/subobjects/:id.json");
        return {
            get: function (id, first, max, objectId) {
                var deferred = $q.defer();
                res.get({
                    id: id,
                    objectId: objectId,
                    first: first,
                    max: max,
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
                    documentObject: item.documentObject
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
            },
            listByVolumeId: (function (volumeId, first, max) {
                var res = $resource("rest/isogd/classifiers/documents/subobjects/listByVolumeId/:volumeId.json");
                var deferred = $q.defer();
                res.get({
                    volumeId: volumeId,
                    first: first,
                    max: max
                }, function (result) {
                    deferred.resolve(result);
                });
                return deferred.promise;
            })
        }
    }) //
;
