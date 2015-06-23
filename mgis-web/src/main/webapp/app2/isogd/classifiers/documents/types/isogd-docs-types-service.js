/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
angular.module("mgis.isogd.classifiers.documents.types.service", ["ngResource"]) //
    .factory("ISOGDDocTypesService", function ($resource, $q) {
        var res = $resource("rest/isogd/classifiers/doctypes/:id.json");
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
                    code: item.code
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
    });
