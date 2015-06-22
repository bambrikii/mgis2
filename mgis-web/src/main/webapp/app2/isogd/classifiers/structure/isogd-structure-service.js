/**
 * Created by Alexander Arakelyan on 22/06/15.
 */
var structureServicesModule = angular.module("mgis.isogd.classifiers.structure.services", ["ngResource"]);

function BaseStructureService($resource, $q) {
    this.url = undefined;
    var res = $resource(url);
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
}


var ISOGDStructureObjectsService = Object.create(BaseStructureService.prototype);
ISOGDStructureObjectsService.url = "rest/isogd/classifiers/objects/:id.json";
// TODO: !!! implement inheritance for services with the same url and arguments

structureServicesModule.factory("ISOGDStructureClassesService", function () {
}) //
    .factory("ISOGDStructureObjectsService", function ($resource) {
        var res = $resource("rest/isogd/classifiers/objects/:id.json");
        return {
            // TODO:
        }
    }) //
    .factory("ISOGDStructureSubObjectsService", function () {
        $res = $resource("rest/isogd/classifiers/subobjects/:id.json");
        return {
            // TODO:
        }
    }) //
;
