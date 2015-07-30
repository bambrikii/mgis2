/**
 * Created by asd on 29.07.15.
 */
angular.module("mgis.nc.oktmo.service", ["ngResource"])
	.factory("NcOKTMOService", function ($resource, $q) {
		var res = $resource('rest/nc/oktmo/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id, name: name}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.nc.okato.service", ["ngResource"])
	.factory("NcOKATOService", function ($resource, $q) {
		var res = $resource('rest/nc/okato/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.nc.territorial_zone.service", ["ngResource"])
	.factory("NcTerritorialZoneService", function ($resource, $q) {
		var res = $resource('rest/nc/territorial_zones/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.nc.land_allowed_usage.service", ["ngResource"])
	.factory("NcLandAllowedUsageService", function ($resource, $q) {
		var res = $resource('rest/nc/lands_allowed_usage/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.nc.land_category.service", ["ngResource"])
	.factory("NcLandCategoryService", function ($resource, $q) {
		var res = $resource('rest/nc/land_categories/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});
