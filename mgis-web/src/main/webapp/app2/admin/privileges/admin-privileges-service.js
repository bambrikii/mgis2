/**
 * Created by asd on 21/06/15.
 */
angular.module("mgis.admin.privileges.service", ["ngResource",
	"mgis.error.service"
]) //
	.factory("AdminPrivilegesService", function ($resource, $q, MGISErrorService) {
		var res = $resource("rest/admin/privileges/:id.json");
		return {
			get: function (privilegeId) {
				var deferred = $q.defer();
				res.get({id: privilegeId}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	}) //
