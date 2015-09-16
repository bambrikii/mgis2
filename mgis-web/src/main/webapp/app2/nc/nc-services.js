/**
 * Created by asd on 29.07.15.
 */
angular.module("mgis.nc.services", ["ngResource",
	"mgis.error.service"
])
	.factory("NcOKTMOService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/oktmo/:id.json');
		return {
			get: function (id, first, max, code, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, code: code, name: name}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcOKATOService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/okato/:id.json');
		return {
			get: function (id, first, max, code, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, code: code, name: name}, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcTerritorialZoneTypeService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/territorial_zone_types/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcLandAllowedUsageService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/lands_allowed_usage/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcLandCategoryService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/land_categories/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcOKFSService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/okfs/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcLandRightKindService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/land_right_kinds/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcLandEncumbranceService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/land_encumbrances/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("NcOKVEDService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/okved/:id.json');
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
	})
	.factory("NcOKOGUService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/okogu/:id.json');
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
	})
	.factory("NcOKOPFService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/nc/okopf/:id.json');
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
	})
;
