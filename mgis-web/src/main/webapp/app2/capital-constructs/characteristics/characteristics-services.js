angular.module("mgis.capital-constructs.characteristics.services", ["ngResource",
	"mgis.error.service"
])
	.factory("CapitalConstructCharacteristicsAmortizationGroup", function ($q, $resource, MGISErrorService) {
		var res = $resource('rest/capital-constructs/amortization-groups/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
;
