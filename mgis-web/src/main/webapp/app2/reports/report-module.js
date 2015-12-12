angular.module("mgis.reports.report", [
	"mgis.commons",
	"mgis.reports.report.service"
])
	.factory("ReportsReportCRUDService", function ($rootScope, $filter, ReportsReportService, MGISCommonsModalForm) {
		var edit = function (item, refresh) {
			var modalScope = $rootScope.$new();
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
				data: "="
			},
			templateUrl: "app2/reports/report-generator.htm",
			controller: function ($scope, ReportsReportService, BytesConversionService) {
				ReportsReportService.get(null, 0, 0, $scope.filter).then(function (data) {
					$scope.availableReports = data.list;
				});
				$scope.generateReport = function (reportId, format) {
					var json = JSON.stringify($scope.data);
					ReportsReportService.generate(json, reportId, format).then(function (data) {
						var array = BytesConversionService.base64ToBlob(data.bytes);
						var blob = new Blob([array], {type: "application/octet-stream"});
						var a = window.document.createElement('a');
						var objectUrl = window.URL.createObjectURL(blob);
						a.href = objectUrl;
						a.download = data.filename;
						document.body.appendChild(a)
						a.click();
						document.body.removeChild(a)
					});
				}
			}
		}
	})
;
