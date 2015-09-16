angular.module("mgis.error", [
	"mgis.commons",
	"mgis.error.service"

])
	.controller("MGISErrorPanelController", function ($scope, $rootScope, MGISErrorService, MGISCommonsModalForm) {
		$scope.showErrors = function () {
			var modalScope = $rootScope.$new();
			var modal = MGISCommonsModalForm.edit("app2/errors/error-list.htm", modalScope, function (data, modalInstance) {
				MGISErrorService.removeAllErrors();
				modalInstance.close();
			});
		}
		$scope.errors = MGISErrorService.errors;
	})
	.controller("MGISErrorListController", function ($scope, $rootScope, MGISErrorService, $modal) {
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

		$scope.viewErrorDetails = function (error) {
			var modalScope = $rootScope.$new();
			modalScope.status = error.status;
			var title = trimTitle(error.data);
			var data = trimStacktrace(error.data);
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

		$scope.removeError = function (error) {
			MGISErrorService.removeError(error);
		}
		$scope.errors = MGISErrorService.errors;
	})
	.directive("mgisErrorPanel", function () {
		return {
			restrict: "E",
			scope: {},
			templateUrl: "app2/errors/error-panel.htm"
		}
	})
;
