angular.module("mgis.indicators.services", ["ngResource",
		"mgis.error.service"
	])
	.factory("IndicatorsPriceIndicatorService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/indicators/price-indicators/:id.json');
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
	.factory("IndicatorsTechnicalIndicatorService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/indicators/technical-indicators/:id.json');
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
