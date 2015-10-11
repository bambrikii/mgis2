angular.module("mgis.geo.map.layer", ["ui.router", "ngDraggable",
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
			}, {windowClass: "mgis-crud-modal-form"});
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
			},
			removeChildren: function (sourceList) {
				var list = new Array();
				angular.copy(sourceList, list);
				for (var i in list) {
					list[i].childLayers = undefined;
				}
				return list;
			}
		}
	})
	.controller("GEOMapLayersController", function ($scope, $rootScope, GEOMapLayerService, MGISCommonsModalForm, GEOMapLayerCRUDService) {
		function updateTree() {
			GEOMapLayerService.get(null, 0, 0, null, null).then(function (data) {
				//$scope.layers = GEOMapLayerCRUDService.removeChildren(data.list);
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
	.controller("GEOMapLayerTreeLeafController", function ($scope, GEOMapLayerService, GEOMapLayerCRUDService) {
		function updateTree() {
			GEOMapLayerService.get($scope.item.id, 0, 0, null).then(function (data) {
				var item = data;
				//item.childLayers = undefined;
				$scope.item = item;
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
				$scope.item = undefined;
			});
		};

		$scope.listChildren = function (parent) {
			GEOMapLayerService.get(null, 0, 0, null, parent.id).then(function (data) {
				parent.childLayers = data.list;
				//parent.childLayers = GEOMapLayerCRUDService.removeChildren(data.list);
			});
		}

		$scope.closeChildren = function (parent) {
			parent.childLayers = null;
		}

		$scope.onLayerDragStart = function ($data, $event, item) {
			//console.log("onLayerDragStart: ");
			//console.log(arguments);
		}

		$scope.onLayerDropComplete = function ($data, $event, item) {
			//console.log("onLayerDropSuccess: ");
			//console.log(arguments);
		}

	})
	.controller("GEOMapLayerFormController", function ($scope, $rootScope, GEOMapLayerService, GEOMapLayerCRUDService, MGISCommonsModalForm) {
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
				updateTree();
			});
		};

		function editParam(item, paramKey) {
			var modalScope = $rootScope.$new();
			var newKey = "" + paramKey;
			if (item.params == undefined) {
				item.params = {};
			}
			var param = {key: newKey, value: item.params[newKey]};
			modalScope.item = param;
			modalScope.keyExists = false;
			modalScope.keyChange = function (event) {
				var alteredKey = event.srcElement.value;

				modalScope.keyExists = (alteredKey != paramKey && item.params.hasOwnProperty(alteredKey));
			}
			MGISCommonsModalForm.edit("app2/geo/map-layer/map-layer-param-form.htm", modalScope, function (scope, modalInstance) {
				modalInstance.close();
				if (paramKey != scope.item.key) {
					delete item.params[paramKey];
				}
				item.params[scope.item.key] = scope.item.value;
			});
		}

		$scope.editParam = function (item, paramKey) {
			editParam(item, paramKey);
		}
		$scope.deleteParam = function (item, paramKey) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				if (item.params && item.params.hasOwnProperty(paramKey)) {
					delete item.params[paramKey];
					$modalInstance.close();
				}
			});
		}
		$scope.addParam = function (item) {
			editParam(item, "");
		}
	})

;
