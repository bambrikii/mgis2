L.Control.LayersTreeControl = L.Control.extend({
	options: {
		position: "topright",
		expand: false,
		className: "leaflet-layers-tree-control",
		layersTree: {}
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
		if (options.layersTree == undefined) {
			throw Error("Layer tree required to display");
		}
		this._layers = {};
	},
	onAdd: function (map) {
		this._map = map;
		var className = this.options.className;
		var container = this._container = L.DomUtil.create('div', className + ' leaflet-control-layers leaflet-control');
		L.DomEvent.disableClickPropagation(container);
		L.DomEvent.on(container, "wheel", L.DomEvent.stopPropagation);
		container.setAttribute("aria-haspopup", true);

		//
		L.DomEvent.on(container, "click", function (event) {

		}, this);

		//
		var me = this;

		function traverseLeaf(parentLeaf, parentContainer, leaf, parentId, order) {
			var type = '';
			var leafContainer = L.DomUtil.create("div", className + "-leaf", parentContainer);
			var leafHeader = L.DomUtil.create("div", className + "-leaf-header", leafContainer);
			var layerId = parentLeaf.code + "_" + leaf.code + "_" + order;
			switch (parentLeaf.selectType) {
				case "NONE":	//
					leafHeader.innerHTML = "<label>" + leaf.name + "</label>"
					break;
				case "SINGLE":	// radio-group
					var parentLeafCode = parentLeaf.code;
					leafHeader.innerHTML = "<label><input type='radio' name='" + parentLeafCode + "' id='" + layerId + "' parentId='" + parentId + "'>" + leaf.name + "</label>"
					L.DomEvent.on(leafHeader, "click", function (event) {
						// select a single layer
						// remove current group layers
						// add selected layer
						var sourceElementId = event.srcElement.id;
						if (sourceElementId) {
							var parentElementId = event.srcElement.attributes[3].value;
							var checked = event.srcElement.checked;
							console.log(sourceElementId + ", " + parentElementId + "," + checked);
							console.log(event);
							if (checked) {
								me.removeLayers(parentLeaf, parentElementId);
								me.addLayer(leaf, sourceElementId);
							}
						}
					});
					break;
				case "MULTIPLE":
				default:	// checkboxes
					leafHeader.innerHTML = "<label><input type='checkbox' name='" + leaf.code + "' id='" + layerId + "'>" + leaf.name + "</label>"
					L.DomEvent.on(leafHeader, "click", function (event) {
						//event.sourceElement.checked
						var sourceElementId = event.srcElement.id;
						if (sourceElementId) {
							var checked = event.srcElement.checked;
							console.log(sourceElementId + ", " + checked);
							console.log(event);
							// add or remove currently selected layer
							if (checked) {
								me.addLayer(leaf, sourceElementId);
							} else {
								me.removeLayer(sourceElementId);
							}
						}
					});
					break;
			}
			var leafContent = L.DomUtil.create("div", className + "-leaf-content", leafContainer);
			if (leaf.childLayers && leaf.childLayers.length > 0) {
				for (var i in leaf.childLayers) {
					var child = leaf.childLayers[i];
					// create a container
					if (child) {
						traverseLeaf(leaf, leafContent, child, parentId + "_" + leaf.code, i);
					}
				}
			}
		}

		console.log("array");
		var layersTree = this.options.layersTree;
		if (Object.prototype.toString.call(layersTree) === '[object Array]') {
			for (var i in layersTree) {
				var layerTree = layersTree[i];
				traverseLeaf(layerTree, container, layerTree, "", 0);
			}
		} else {
			traverseLeaf(layersTree, container, layersTree, "", 0);
		}

		return container;
	},
	onRemove: function (map) {

	},
	addLayer: function (layerSettings, layerId) {
		var map = this._map;
		var layer;
		switch (layerSettings.type) {
			case "tile":
				layer = L.tileLayer(layerSettings.params.url, {}).addTo(map);
				break;
			case "bing":
				layer = new L.BingLayer(layerSettings.params.url).addTo(map);
				break;
			case "google":
				layer = new L.Google();
				break;
			case "google-terrain":
				layer = new L.Google("TERRAIN");
				break;
			case "wms":
			{
				layer = L.tileLayer.wms(layerSettings.params.url, {
					layers: layerSettings.params.layers,
					format: layerSettings.params.format,
					transparent: layerSettings.params.transparent,
					attribution: layerSettings.params.attribution
				});
			}
				break;
			case "wfs":
			{
				var wfsLayer = new L.GeoJSON().addTo(map);
				var defaultParameters = {
					service: 'WFS',
					version: '1.0.0',
					request: 'getFeature',
					typeName: layerSettings.params.typeName,
					maxFeatures: 3000,
					outputFormat: 'application/json'
				};

				var customParams = {
					bbox: map.getBounds().toBBoxString(),
				};
				var parameters = L.Util.extend(defaultParameters, customParams);
				var wfsUrl = layerSettings.params.url + L.Util.getParamString(parameters);
				$.ajax({
					url: wfsUrl,
					dataType: 'jsonp',
					success: function (data) {
						wfsLayer.addData(data);
					}
				});
				layer = wfsLayer;
			}
				break;
			default:
				break;
		}
		if (layer) {
			this._layers[layerId] = layer;
			map.addLayer(layer);
		}
	},
	removeLayers: function (layer, parentId) {
		this.removeLayer(layer);
		if (layer.childLayers && layer.childLayers.length > 0) {
			for (var i in layer.childLayers) {
				var child = layer.childLayers[i];
				this.removeLayer(child);
			}
		}
	},
	removeLayer: function (layerId) {
		var map = this._map;
		var layer = this._layers[layerId];
		map.removeLayer(layer);
	}
})
;
