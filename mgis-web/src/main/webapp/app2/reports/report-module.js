angular.module("mgis.reports.report", [
	"mgis.commons",
	"mgis.reports.report.service"
])
	.factory("ReportsReportCRUDService", function ($rootScope, ReportsReportService, MGISCommonsModalForm) {
		var edit = function (item, refresh) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			MGISCommonsModalForm.edit("app2/reports/report-form.htm", modalScope, function (scope, modalInstance) {
				ReportsReportService.save(scope.item).then(function () {
					if (refresh) {
						refresh();
					}
					modalInstance.close();
				});
			});
		}
		return {
			add: function () {
				var item = {id: 0}
				edit(item, refresh);
			},
			edit: function (id) {
				ReportsReportService.get(id).then(function (item) {
					edit(item, refresh)
				});
			},
			remove: function (id, refresh) {
				ReportsReportService.remove(id).then(function () {
					if (refresh) {
						refresh();
					}
				});
			}
		}
	})
	.controller("ReportsReportListController", function ($scope, ReportsReportCRUDService, ReportsReportService, CommonsPagerManager) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		var refresh = function () {
			ReportsReportService.get(null, $scope.itemsPerPage * ($scope.currentPage - 1), $scope.itemsPerPage).then(function (data) {
				$scope.pager = data;
			});
		}
		$scope.add = function () {
			ReportsReportCRUDService.add(refresh);
		}
		$scope.edit = function (id) {
			ReportsReportCRUDService.edit(id, refresh);
		}
		$scope.remove = function (id) {
			ReportsReportCRUDService.remove(id, refresh);
		}
	})
;
