/**
 * Created by asd on 20/06/15.
 */
angular.module("mgis.admin.users.service", [ "ngResource" ]) //
    .factory("AdminUsersService", function ($resource, $q) {
        var res = $resource("rest/admin/users/:id.json", {}, {
            get: {
                method: "GET"
            },
            save: {
                method: "POST"
            },
            remove: {
                method: "DELETE"
            }
        });
        return {
            get: function (userId, first, max) {
                var deferred = $q.defer();
                res.get({id: userId, first: first, max: max}, function (user) {
                    deferred.resolve(user);
                });
                return deferred.promise;
            },
            save: function (user) {
                var deferred = $q.defer();
                res.save({id: user.id },
                    {
                        id: user.id,
                        username: user.username,
                        password: user.password,
                        active: user.active,
                        privileges: user.privileges
                    }, function (user) {
                        deferred.resolve(user);
                    });
                return deferred.promise;
            },
            remove: function (userId) {
                var deferred = $q.defer();
                res.remove({id: userId}, function (user) {
                    deferred.resolve(user);
                });
                return deferred.promise;
            }
        }
    }) //
;