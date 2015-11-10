angular.module("mgis.nc.services", ["ngResource",
	"mgis.error.service"
])
	.factory("NcPriceTypeService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/price-types/:id.json');
		return {
			get: function (id, first, max, code, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, code: code, name: name}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id}, land, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
;
