angular.module("mgis.reports.report", [
	"mgis.commons",
	"mgis.commons.forms",
	"mgis.reports.report.service"
])
	.constant("REPORT_GENERATION_IN_PROGRESS", "InProgress")
	.constant("REPORT_GENERATION_COMPLETE", "Complete")
	.factory("ReportsReportCRUDService", function ($rootScope, $filter, ReportsReportService, MGISCommonsModalForm, NUMBER_PATTERN) {
		var edit = function (item, refresh) {
			var modalScope = $rootScope.$new();
			modalScope.NUMBER_PATTERN = NUMBER_PATTERN;
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			var landFilter = {id: "Lands", name: "Lands"};
			var occFilter = {id: "CapitalConstructs", name: "CapitalConstructs"};
			modalScope.itemId = modalScope.item.id;
			modalScope.availableFilters = new Array();
			modalScope.availableFilters.push(landFilter);
			modalScope.availableFilters.push(occFilter);
			modalScope.currentFilter = undefined;
			modalScope.addFilter = function (filters, filter) {
				if (filter) {
					for (var i in filters) {
						if (filters[i] == filter.id) {
							return
						}
					}
					filters.push(filter.id);
				}
			}
			modalScope.removeFilter = function (filters, filter) {
				if (filter) {
					for (var i in filters) {
						if (filters[i] == filter) {
							filters.splice(i, 1);
						}
					}
				}
			}
			modalScope.uploadComplete = function (data) {
				if (!modalScope.item.id) {
					modalScope.item.id = data;
					modalScope.itemId = modalScope.item.id;
				} else {
					modalScope.itemId = "0" + modalScope.item.id;
				}
			}
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
			add: function (refresh) {
				var item = {id: 0, filters: new Array()}
				edit(item, refresh);
			},
			edit: function (id, refresh) {
				ReportsReportService.get(id).then(function (item) {
					edit(item, refresh)
				});
			},
			remove: function (id, refresh) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					ReportsReportService.remove(id).then(function () {
						modalInstance.close();
						if (refresh) {
							refresh();
						}
					});
				})
			}
		}
	})
	.controller("ReportsReportListController", function ($scope, ReportsReportCRUDService, ReportsReportService, CommonsPagerManager) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = CommonsPagerManager.pageSize();
		$scope.pagerMaxSize = CommonsPagerManager.maxSize();
		var refresh = function () {
			ReportsReportService.get(null, CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage).then(function (data) {
				$scope.reportsPager = data;
			});
		}
		$scope.pageChanged = function () {
			refresh();
		}
		$scope.addItem = function () {
			ReportsReportCRUDService.add(refresh);
		}
		$scope.editItem = function (id) {
			ReportsReportCRUDService.edit(id, refresh);
		}
		$scope.removeItem = function (id) {
			ReportsReportCRUDService.remove(id, refresh);
		}
		refresh();
	})
	.directive("reportsGenerator", function () {
		return {
			restrict: "E",
			scope: {
				filter: "@",
				data: "=",
				generateHandler: "&"
			},
			templateUrl: "app2/reports/report-generator.htm",
			link: function (scope, elem, attrs) {

				scope.hasGenerateHandler = function () {
					return angular.isDefined(attrs.generateHandler);
				}
			},
			controller: function ($scope, ReportsReportService, BytesConversionService, REPORT_GENERATION_IN_PROGRESS, REPORT_GENERATION_COMPLETE) {
				$scope.reportStatus = {};
				ReportsReportService.get(null, 0, 0, $scope.filter).then(function (data) {
					$scope.availableReports = data.list;
				});

				function doGenerateReport(bytes, filename) {
					var array = BytesConversionService.base64ToBlob(bytes);
					var blob = new Blob([array], {type: "application/octet-stream"});
					var a = window.document.createElement('a');
					var objectUrl = window.URL.createObjectURL(blob);
					a.href = objectUrl;
					a.download = filename;
					document.body.appendChild(a)
					a.click();
					document.body.removeChild(a)
				}

				$scope.generateReport = function (reportId, format) {
					var json = JSON.stringify($scope.data);
					if ($scope.hasGenerateHandler()) {
						$scope.reportStatus[reportId] = {status: REPORT_GENERATION_IN_PROGRESS};
						var startDate = new Date();
						var data = $scope.generateHandler({reportId: reportId, format: format});
						doGenerateReport(data.bytes, data.filename);
						$scope.reportStatus[reportId] = {status: REPORT_GENERATION_COMPLETE, time: new Date() - startDate};
					} else {
						$scope.reportStatus[reportId] = {status: REPORT_GENERATION_IN_PROGRESS};
						var startDate = new Date();
						ReportsReportService.generate(json, reportId, format).then(function (data) {
							doGenerateReport(data.bytes, data.filename);
							$scope.reportStatus[reportId] = {status: REPORT_GENERATION_COMPLETE, time: new Date() - startDate};
						});
					}
				}
			}
		}
	})
;
