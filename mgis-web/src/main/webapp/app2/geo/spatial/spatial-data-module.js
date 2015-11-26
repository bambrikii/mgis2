angular.module("mgis.geo.spatial.data", [
	"mgis.commons",
	"mgis.geo.coordinate-system.service"
])
	.directive("geoSpatialData", function () {
		return {
			restrict: "E",
			scope: {
				spatialGroup: "="
			},
			templateUrl: "app2/geo/spatial/spatial-data-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm, GEOCoordinateSystemService) {
				if (!$scope.spatialGroup) {
					$scope.spatialGroup = {};
				}
				GEOCoordinateSystemService.get().then(function (data) {
					$scope.availableCoordinateSystems = data.list;
				});
				var editElementFunction = function (item, postAction) {
					var modalScope = $rootScope.$new();
					modalScope.item = {};
					angular.copy(item, modalScope.item);
					MGISCommonsModalForm.edit("app2/geo/spatial/spatial-element-form.htm", modalScope, function (scope, modalInstance) {
						modalInstance.close();
						postAction(scope.item, item);
					});
				};

				$scope.editElement = function (item) {
					editElementFunction(item, function (scopeItem, item2) {
						angular.copy(scopeItem, item2);
					});
				}
				$scope.addElement = function () {
					editElementFunction({coordinates: new Array()}, function (scopeItem, item2) {
						angular.copy(scopeItem, item2);
						if (!$scope.spatialGroup.spatialElements) {
							$scope.spatialGroup.spatialElements = new Array();
						}
						$scope.spatialGroup.spatialElements.push(item2);
					});
				}
				$scope.removeElement = function (element) {
					MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
						for (var i in $scope.spatialGroup.spatialElements) {
							if (element == $scope.spatialGroup.spatialElements[i]) {
								$scope.spatialGroup.spatialElements.splice(i, 1);
							}
						}
						modalInstance.close();
					});
				}
			}
		}
	})
	.controller("GEOSpatialCoordinateController", function ($scope, $rootScope, MGISCommonsModalForm) {
		var editCoordinateFunction = function (item, postAction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(item, modalScope.item);
			MGISCommonsModalForm.edit("app2/geo/spatial/coordinate-form.htm", modalScope, function (scope, modalInstance) {
				modalInstance.close();
				postAction(scope.item, item);
			});
		};
		$scope.editCoordinate = function (item) {
			editCoordinateFunction(item, function (scopeItem, item2) {
				angular.copy(scopeItem, item2);
			})
		}
		$scope.addCoordinate = function (item) {
			editCoordinateFunction({coordinates: new Array()}, function (scopeItem, item2) {
				angular.copy(scopeItem, item2);
				item.coordinates.push(item2);
			})
		}
		$scope.removeCoordinate = function (coordinate) {
			MGISCommonsModalForm.confirmRemoval(function () {
				var element = $scope.item;
				for (var i in element.coordinates) {
					if (coordinate == element.coordinates[i]) {
						element.coordinates.splice(i, 1);
					}
				}
			});
		}
	})
;
