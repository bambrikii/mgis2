angular.module("mgis.error", [
	"mgis.commons",
	"mgis.error.service"

])
	.factory("MGISErrorPreviewService", function ($rootScope, $modal, MGISCommonsModalForm, MGISErrorService) {
		function trimTitle(data) {
			var arr = /<title>([^<]*)<\/title>/.exec(data);
			if (arr.length > 1) {
				return arr[1];
			}
			return undefined;
		}

		function trimStacktrace(data) {
			var result = "";
			var arr = data.split("<pre>");
			for (var i = 1; i < arr.length; i++) {
				result += arr[i].split("<\/pre>")[0] + "\n";
			}
			return result;
		}

		function previewError(error) {
			var modalScope = $rootScope.$new();
			modalScope.status = error.status;
			var title = null;
			var data = null;
			try {
				title = trimTitle(error.data);
				data = trimStacktrace(error.data);
			} catch (ex) {
				console.log(ex);
			}
			if (title && data) {
				modalScope.title = title;
				modalScope.statusText = error.statusText;
				modalScope.data = data;
			} else {
				modalScope.statusText = error.statusText;
				modalScope.data = error.data;
			}

			var modalInstance = $modal.open({
				templateUrl: "app2/errors/error-form.htm",
				scope: modalScope,
				windowClass: "mgis-error-handling-modal",
				controller: function ($scope, $modalInstance) {
					$scope.ok = function () {
						$modalInstance.close();
					}
					$scope.cancel = function () {
						$modalInstance.dismiss('cancel');
					}
				}
			});
		}

		function previewList() {
			var modalScope = $rootScope.$new();
			var modal = MGISCommonsModalForm.edit("app2/errors/error-list.htm", modalScope, function (data, modalInstance) {
				MGISErrorService.removeAllErrors();
				modalInstance.close();
			});
		}

		return {
			preview: previewError,
			list: previewList
		}
	})
	.controller("MGISErrorListController", function ($scope, $rootScope, MGISErrorService, MGISErrorPreviewService) {
		$scope.viewErrorDetails = function (error) {
			MGISErrorPreviewService.preview(error);
		}

		$scope.removeError = function (error) {
			MGISErrorService.removeError(error);
		}
		$scope.errors = MGISErrorService.errors;
	})
	.directive("mgisErrorPanel", function () {
		return {
			restrict: "E",
			scope: {},
			templateUrl: "app2/errors/error-panel.htm",
			controller: function ($scope, $rootScope, MGISErrorService, MGISCommonsModalForm, MGISErrorPreviewService) {
				$scope.showErrors = function () {
					MGISErrorPreviewService.list();
				}
				$scope.errors = MGISErrorService.errors;
				$scope.$watch("errors.length", function (length, oldLength) {
					if (length > oldLength) {
						MGISErrorPreviewService.preview($scope.errors[$scope.errors.length - 1]);
					}
				});
			}
		}
	})
;
