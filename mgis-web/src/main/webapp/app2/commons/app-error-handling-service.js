angular.module("mgis.commons.ErrorHandling.Service", ["ui.bootstrap"])
	.factory("MGISErrorHandlingService", function ($modal, $rootScope) {
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

		return {
			handleError: function (error) {
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
					templateUrl: "app2/commons/app-error-handling-form.htm",
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
		}
	});
