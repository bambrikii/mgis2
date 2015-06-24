angular.module("mgis.isogd.sections.service", ["ui.router", 'ngResource']) //
    .factory("ISOGDSectionsService", function ($resource, $q) {
        var res = $resource('rest/isogd/sections/:id.json');
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
            },
            save: function (section) {
                var deferred = $q.defer();
                res.save({id: section.id,}, {
                    id: section.id,
                    name: section.name,
                    documentClass: section.documentClass ? {id: section.documentClass.id} : null
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
