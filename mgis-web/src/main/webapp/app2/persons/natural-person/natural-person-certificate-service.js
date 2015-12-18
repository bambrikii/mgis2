/**
 * Created by donchenko-y on 12/17/15.
 */
angular.module("mgis.persons.person.natural.certs.service", ["ngResource",
	"mgis.error.service"
]).factory("NaturalPersonCertificateTypeService", function ($resource, $q, MGISErrorService) {
	var res = $resource('rest/persons/natural-persons/certificate-types/:id.json');
	return {
		get: function (id, first, max, name) {
			var deferred = $q.defer();
			res.get({id: id, first: first, max: max, name: name}, {}, function (data) {
				deferred.resolve(data);
			}, function (error) {
				MGISErrorService.handleError(error);
			});
			return deferred.promise;
		}
	}
});
