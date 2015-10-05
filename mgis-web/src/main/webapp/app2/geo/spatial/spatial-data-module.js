angular.module("mgis.geo.spatial.data", [
	"mgis.commons"
])
	.directive("geoSpatialData", function () {
		return {
			restrict: "E",
			scope: {
				spatialGroups: "="
			},
			templateUrl: "app2/geo/spatial/spatial-data-component.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm) {
				if (!$scope.spatialGroups) {
					$scope.spatialGroups = new Array();
				}
				var editCoordinateFunction = function (item, postAction) {
					var modalScope = $rootScope.$new();
					modalScope.item = {}
					angular.copy(item, modalScope.coordinate);
					MGISCommonsModalForm.edit("app2/geo/spatial/coordinate-form.htm", modalScope, function (scope, modalInstance) {
						modalInstance.close();
						postAction(scope.item, item);
					});
				};
				var editElementFunction = function (item, postAction) {
					var modalScope = $rootScope.$new();
					modalScope.item = {}
					angular.copy(item, modalScope.item);
					modalScope.editCoordinate = editCoordinateFunction;
					MGISCommonsModalForm.edit("app2/geo/spatial/spatial-element-form.htm", modalScope, function (scope, modalInstance) {
						modalInstance.close();
						postAction(scope.item, item);
					});
				};
				var editGroupFunction = function (item, postAction) {
					var modalScope = $rootScope.$new();
					modalScope.item = {}
					angular.copy(item, modalScope.item);
					modalScope.editElement = editElementFunction;
					MGISCommonsModalForm.edit("app2/geo/spatial/spatial-group-form.htm", modalScope, function (scope, modalInstance) {
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

				$scope.editElement = function (item) {
					editElementFunction(item, function (scopeItem, item2) {
						angular.copy(scopeItem, item2);
					});
				}
				$scope.addElement = function (group) {
					editElementFunction({coordinates: new Array()}, function (scopeItem, item2) {
						angular.copy(scopeItem, item2);
						group.spatialElements.push(item2);
					});
				}

				// Group
				$scope.editGroup = function (item) {
					editGroupFunction(item, function (scopeItem, item2) {
						angular.copy(scopeItem, item2);
					});
				}
				$scope.addGroup = function () {
					editGroupFunction({spatialElements: new Array()}, function (scopeItem, item2) {
						angular.copy(scopeItem, item2);
						$scope.spatialGroups.push(item2)
					});
				}
			}
		}
	})
;
