angular.module("mgis.geo.map.layer", ["ui.router",
	"mgis.commons",
	"mgis.commons.forms",
	"mgis.geo.map.layer.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo.layers", {
				url: "/layers",
				templateUrl: "app2/geo/map-layer/map-layer-panel.htm"
			});
	})
	.factory("GEOMapLayerCRUDService", function ($rootScope, GEOMapLayerService, MGISCommonsModalForm, GEO_LAYER_SELECT_TYPES, GEO_LAYER_SERVICE_TYPES) {
		function editItem(item, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = item;
			modalScope.availableSelectTypes = GEO_LAYER_SELECT_TYPES;
			modalScope.availableServiceTypes = GEO_LAYER_SERVICE_TYPES;
			MGISCommonsModalForm.edit("app2/geo/map-layer/map-layer-form.htm", modalScope, function (scope, modalInstance) {
				GEOMapLayerService.save(scope.item).then(function (item) {
					modalInstance.close();
					updateFunction();
				});
			});
		}

		return {
			edit: function (item, updateFunction) {
				editItem(item, updateFunction);
			},
			add: function (parent, updateFunction) {
				var item = {id: 0, parent: {id: parent.id}};
				editItem(item, updateFunction);
			},
			remove: function (item, updateFunction) {
				MGISCommonsModalForm.confirmRemoval(function (modalInstance) {
					GEOMapLayerService.remove(item.id).then(function () {
						modalInstance.close();
						updateFunction();
					});
				})
			}
		}
	})
	.controller("GEOMapLayersController", function ($scope, $rootScope, GEOMapLayerService, MGISCommonsModalForm, GEOMapLayerCRUDService) {
		function updateTree() {
			GEOMapLayerService.get(null, 0, 0, null, null).then(function (data) {
				$scope.layers = data.list;
			});
		}

		$scope.editItem = function (item) {
			GEOMapLayerService.get(item.id).then(function (data) {
				GEOMapLayerCRUDService.edit(data, function () {
					updateTree();
				});
			});
		};
		$scope.addItem = function (parent) {
			GEOMapLayerCRUDService.add(parent, function () {
				updateTree();
			});
		};
		$scope.deleteItem = function (item) {
			GEOMapLayerCRUDService.remove(item, function () {
				updateTree();
			});
		};
		updateTree();
	})
	.controller("GEOMapLayerController", function ($scope, GEOMapLayerService, GEOMapLayerCRUDService) {
		function updateTree() {
			GEOMapLayerService.get($scope.item.id, 0, 0, null).then(function (data) {
				$scope.item = data;
			});
		}

		$scope.editItem = function (item) {
			GEOMapLayerService.get(item.id).then(function (data) {
				GEOMapLayerCRUDService.edit(data, function () {
					updateTree();
				});
			});
		};
		$scope.addItem = function (parent) {
			GEOMapLayerCRUDService.add(parent, function () {
				updateTree();
			});
		};
		$scope.deleteItem = function (item) {
			var parent = item.parent;
			GEOMapLayerCRUDService.remove(item, function () {
				//updateTree();
				//$scope.$parent.listChildren(parent);
				$scope.item = undefined;
			});
		};

		$scope.listChildren = function (parent) {
			GEOMapLayerService.get(null, 0, 0, null, parent.id).then(function (data) {
				parent.childLayers = data.list;
			});
		}

		$scope.closeChildren = function (parent) {
			parent.childLayers = null;
		}

	})
;
