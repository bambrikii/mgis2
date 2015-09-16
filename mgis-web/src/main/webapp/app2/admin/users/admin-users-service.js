/**
 * Created by asd on 20/06/15.
 */
angular.module("mgis.admin.users.service", ["ngResource",
	"mgis.error.service"
]) //
	.factory("AdminUsersService", function ($resource, $q, MGISErrorService) {
		var res = $resource("rest/admin/users/:id.json");
		return {
			get: function (userId, first, max) {
				var deferred = $q.defer();
				res.get({id: userId, first: first, max: max}, function (user) {
					deferred.resolve(user);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (user) {
				var deferred = $q.defer();
				res.save({id: user.id},
					{
						id: user.id,
						username: user.username,
						password: user.password,
						active: user.active,
						privileges: user.privileges
					}, function (user) {
						deferred.resolve(user);
					}, function (error) {
						MGISErrorService.handleError(error);
					});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({id: id}, function (user) {
					deferred.resolve(user);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	}) //
;
