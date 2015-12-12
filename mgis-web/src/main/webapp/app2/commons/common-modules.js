/**
 * Created by Alexander Arakelyan on 24.06.15.
 */
angular.module("mgis.commons", ["ui.bootstrap",
	"mgis.error"
])
	.factory("MGISCommonsModalForm", function ($modal) {
		return {
			confirmRemoval: function (acceptMethod) {
				var modalInstance = $modal.open({
					animation: false,
					templateUrl: "app2/commons/confirm-deletion.htm",
					controller: function ($scope, $modalInstance) {
						$scope.ok = function () {
							acceptMethod($modalInstance);
						};
						$scope.cancel = function () {
							$modalInstance.dismiss('cancel');
						}
					}
				});
			},
			edit: function (templateUrl, modalScope, applyMethod, params) {

				var params2 = {
					animation: false,
					scope: modalScope,
					templateUrl: templateUrl,
					controller: function ($scope, $modalInstance) {
						$scope.ok = function () {
							applyMethod($scope, $modalInstance);
						}
						$scope.cancel = function () {
							$modalInstance.dismiss("cancel");
						}
					}
				}
				if (params) {
					angular.extend(params2, params);
				}
				return $modal.open(params2);
			}
		}
	})
	.controller("MGISDateTimeController", function ($scope) {

		$scope.showWeeks = true;
		$scope.toggleWeeks = function () {
			$scope.showWeeks = !$scope.showWeeks;
		}

		$scope.clear = function () {
			$scope.dt = null;
		}

		// Disable weekend selection
		$scope.disabled = function (date, mode) {
			return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
		}

		$scope.open = function ($event) {
			$event.preventDefault();
			$event.stopPropagation();

			$scope.opened = true;
		}

		$scope.dateOptions = {
			'year-format': "'yy'",
			'starting-day': 1
		}

		$scope.format = "dd.MM.yyyy";
		$scope.valueChangedInternal = function () {
			if ($scope.valueChanged) {
				$scope.valueChanged({newValue: $scope.value});
			}
		}
	})
	.directive("commonsDate", function () {
		return {
			restrict: "E",
			scope: {
				value: "=",
				minDate: "=",
				maxDate: "=",
				valueChanged: "&",
				name: "=",
				required: "="
			},
			templateUrl: "app2/commons/commons-date-directive.htm",
			controller: function ($scope) {

				$scope.showWeeks = true;
				$scope.toggleWeeks = function () {
					$scope.showWeeks = !$scope.showWeeks;
				}

				$scope.clear = function () {
					$scope.dt = null;
				}

				// Disable weekend selection
				$scope.disabled = function (date, mode) {
					return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
				}

				$scope.open = function ($event) {
					$event.preventDefault();
					$event.stopPropagation();

					$scope.opened = !$scope.opened;
				}

				$scope.dateOptions = {
					'year-format': "'yy'",
					'starting-day': 1
				}

				$scope.format = "dd.MM.yyyy";
				$scope.valueChangedInternal = function ($event) {
					if ($scope.valueChanged) {
						$scope.valueChanged({event: $event, newValue: $scope.value});
					}
				}
			}
		}
	})
	.controller("MGISUploadFileController", function ($scope, Upload) {
		$scope.init = function (uploadUrl, uploadFields, uploadProgress, uploadComplete) {
			$scope.uploadUrl = uploadUrl;
			$scope.uploadFields = uploadFields;
			$scope.uploadProgress = uploadProgress;
			$scope.uploadComplete = uploadComplete;
		}

		$scope.$watch('files', function () {
			$scope.upload($scope.files);
		});

		$scope.upload = function (files) {
			if (files && files.length) {
				for (var i = 0; i < files.length; i++) {
					var file = files[i];
					Upload.upload({
						url: $scope.uploadUrl,
						headers: {
							'Content-Type': 'multipart/form-data; charset=utf-8'
						},
						fields: $scope.uploadFields,
						file: file
					}).progress(function (event) {
						var progressPercentage = parseInt(100.0 * event.loaded / event.total);
						if ($scope.uploadProgress) {
							$scope.uploadProgress(event);
						}
					}).success(function (data, status, headers, config) {
						if ($scope.uploadComplete) {
							$scope.uploadComplete(data);
						}
					});
				}
			}
		};
	})
// String module
	.directive("stringsCollectionDirective", function () {
		return {
			scope: {
				strings: "="
			},
			templateUrl: "app2/commons/strings/string-collection-component.htm"
		}
	})
	.controller("MGISCommonsStringCollectionController", function ($scope, $rootScope, MGISCommonsModalForm) {
		if (!$scope.strings) {
			$scope.strings = new Array();
		}
		$scope.openSelector = function () {
			var modalScope = $rootScope.$new();
			modalScope.strings = new Array();
			angular.copy($scope.strings, modalScope.strings);
			modalScope.str = "";
			modalScope.addStr = function (str) {
				if (str) {
					var found = false;
					for (var i in modalScope.strings) {
						var item1 = modalScope.strings[i];
						if (item1 == str) {
							found = true;
							break;
						}
					}
					if (!found) {
						modalScope.strings.push(str);
					}
				}
			}
			modalScope.removeStr = function (str) {
				var indexes = new Array();
				for (var i in modalScope.strings) {
					var item1 = modalScope.strings[i];
					if (item1 == str) {
						modalScope.strings.splice(i, 1);
					}
				}
			}
			MGISCommonsModalForm.edit("app2/commons/strings/string-collection-form.htm", modalScope, function (scope, modalInstance) {
				angular.copy(modalScope.strings, $scope.strings);
				modalInstance.close();
			});
		}
	})
	.filter('mgisCommonsReverse', function () {
		return function (items) {
			return items ? items.slice().reverse() : null;
		};
	})
	//
	.directive("mgisShortenedMessage", function () {
		return {
			restrict: "E",
			scope: {
				message: "=",
				enableWindow: "@"
			},
			templateUrl: "app2/commons/shortened-message.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm) {
				$scope.isPreviewOpen = false;
				$scope.displayFullMessage = function () {
					$scope.isPreviewOpen = false;
					MGISCommonsModalForm.edit("app2/commons/shortened-message-form.htm", $scope, function (scope, modalInstance) {
						modalInstance.close();
					});
				}
				$scope.previewMessage = function () {
					$scope.isPreviewOpen = $scope.isPreviewOpen ? false : true;
				}
			}
		}
	})
;
