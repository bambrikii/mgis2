angular.module("mgis.isogd.documents.service", ["ui.router"]) //
    .factory("ISOGDDocumentsService", function ($resource, $q) {
        var res = $resource('rest/isogd/documents/:id.json');
        return {
            get: function (id, first, max, volumeId) {
                var deferred = $q.defer();
                res.get({
                    id: id,
                    volumeId: volumeId,
                    first: first,
                    max: max
                }, function (data) {
                    deferred.resolve(data);
                });
                return deferred.promise;
            },
            save: function (document) {
                var deferred = $q.defer();
                res.save({id: document.id,}, {
                    id: document.id,
                    name: document.name,
                    volume: {
                        id: document.volume.id
                    },
                    documentSubObject: {
                        id: document.documentSubObject.id
                    }
                }, function (data) {
                    deferred.resolve(data);
                });
                return deferred.promise;
            },
            remove: function (documentId) {
                var deferred = $q.defer();
                res.remove({
                    id: documentId
                }, function (data) {
                    deferred.resolve(data);
                });
                return deferred.promise;
            },
            listDocumentSubObjectsByVolumeId: (function (volumeId, first, max) {
                var res = $resource("rest/isogd/documents/listDocumentSubObjectsByVolumeId/:volumeId.json");
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
    });
