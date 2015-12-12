angular.module("mgis.reports.report.service", ["ngResource",
	"mgis.error.service"
])
	.factory("ReportsReportService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/reports/:id.json');
		var resGen = $resource('rest/reports/:id/generate.json');
		return {
			get: function (id, first, max, filter) {
				var deferred = $q.defer();
				var requestParams = {id: id, first: first, max: max, filter: filter};
				res.get(requestParams, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {};
				angular.copy(item, p);
				res.save({id: item.id}, p, function (data) {
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
			},
			generate: function (json, reportId, format) {
				var deferred = $q.defer();
				resGen.save(
					{id: reportId},
					{id: reportId, format: format, json: json},
					function (data) {
						deferred.resolve(data);
					}, function (error) {
						MGISErrorService.handleError(error);
					});
				return deferred.promise;
			}
		}
	})
;
