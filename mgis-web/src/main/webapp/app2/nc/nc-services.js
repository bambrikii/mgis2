/**
 * Created by asd on 29.07.15.
 */
angular.module("mgis.nc.services", ["ngResource"])
	.factory("NcOKTMOService", function ($resource, $q) {
		var res = $resource('rest/nc/oktmo/:id.json');
		return {
			get: function (id, first, max, code, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, code: code, name: name}, {}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcOKATOService", function ($resource, $q) {
		var res = $resource('rest/nc/okato/:id.json');
		return {
			get: function (id, first, max, code, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, code: code, name: name}, {}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcTerritorialZoneTypeService", function ($resource, $q) {
		var res = $resource('rest/nc/territorial_zone_types/:id.json');
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
	})
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
	})
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
	})
	.factory("NcLandOwnershipFormService", function ($resource, $q) {
		var res = $resource('rest/nc/land_ownership_forms/:id.json');
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
	})
	.factory("NcLandRightKindService", function ($resource, $q) {
		var res = $resource('rest/nc/land_right_kinds/:id.json');
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
	})
	.factory("NcLandEncumbranceService", function ($resource, $q) {
		var res = $resource('rest/nc/land_encumbrances/:id.json');
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
