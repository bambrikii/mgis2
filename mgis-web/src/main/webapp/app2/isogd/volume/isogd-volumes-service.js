angular.module("mgis.isogd.volumes.service", ["ui.router", "ui.bootstrap"]) //
    .factory("ISOGDVolumesService", function ($resource, $q) {

        var factory = {};
        factory.list = function (sectionId, first, max) {
            console.log("list: " + sectionId);
            var deferred = $q.defer();
            $resource('rest/isogd/volumes/list.json', {
                sectionId: sectionId
            }, {
                get: {
                    method: 'GET'
                }
            }).get({
                first: first,
                max: max
            }, function (data) {
                deferred.resolve(data);
            })
            return deferred.promise;
        }

        factory.get = function (volumeId) {
            var deferred = $q.defer();
            $resource('rest/isogd/volumes/:volumeId.json', {
                volumeId: volumeId
            }).get({}, function (data) {
                deferred.resolve(data);
            })
            return deferred.promise;
        }

        factory.save = function (sectionId, volume) {
            var deferred = $q.defer();
            $resource('rest/isogd/volumes/:volumeId.json', {
                volumeId: volume.id
            }, {
                save: {
                    method: 'POST'
                }
            }).save({
                id: volume.id,
                name: volume.name,
                section: {
                    id: sectionId
                }
            }, function (data) {
                deferred.resolve(data);
            })
            return deferred.promise;
        }

        factory.remove = function (sectionId, volumeId) {
            $deferred = $q.defer();
            $resource('rest/isogd/volumes/:volumeId.json', {
                sectionId: sectionId,
                volumeId: volumeId
            }, {
                remove: {
                    method: 'DELETE'
                }
            }).remove({
                id: volumeId,
                section: {
                    id: sectionId
                }
            });
            return deferred.promise;
        }

        return factory;
    });
