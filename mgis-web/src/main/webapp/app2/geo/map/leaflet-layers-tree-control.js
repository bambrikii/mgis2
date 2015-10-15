L.Control.LayersTreeControl = L.Control.extend({
	options: {
		position: "topright",
		expand: false,
		className: "leaflet-layers-tree-control",
		layersTree: {},
		openByDefault: false
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
		if (options.layersTree == undefined) {
			throw Error("Layer tree required to display");
		}
		this._layers = {};
		this._reloadHandlers = {};
		this._minWidth = undefined;
	},
	onAdd: function (map) {
		this._map = map;
		var className = this.options.className;
		var container = this._container = L.DomUtil.create('div', className + " leaflet-control");
		var layersContainer = L.DomUtil.create('div', className + '-layers leaflet-control-layers', container);
		L.DomEvent.disableClickPropagation(container);
		L.DomEvent.on(container, "wheel", L.DomEvent.stopPropagation);
		container.setAttribute("aria-haspopup", true);

		var iconifyToggleControl = L.DomUtil.create("div", className + "-toggle-open leaflet-control-layers", container);
		var icon = L.DomUtil.create("div", className + "-toggle-link", iconifyToggleControl);

		//
		function toggleIconify() {
			if (layersContainer.style.display == "none") {
				layersContainer.style.display = "";
				iconifyToggleControl.className = className + "-toggle-open leaflet-control-layers";
			} else {
				layersContainer.style.display = "none";
				iconifyToggleControl.className = className + "-toggle-closed leaflet-control-layers";
			}
		}

		L.DomEvent.on(icon, "click", function (event) {
			toggleIconify();
		}, this);

		if (!this.options.openByDefault) {
			toggleIconify();
		}

		//
		var me = this;

		function updateMinWidth() {
			if (me._minWidth == undefined) {
				me._minWidth = layersContainer.offsetWidth;
			} else {
				if (me._minWidth < layersContainer.offsetWidth) {
					me._minWidth = layersContainer.offsetWidth;
				}
			}
			layersContainer.style.minWidth = me._minWidth + "px";
		}

		function toggleChildrenVisibility(elem, open) {
			if (
				(elem.childNodes.length >= 2 && (elem.childNodes[0].className == className + "-leaf-header")) &&
				(elem.childNodes.length >= 2 && (elem.childNodes[1].className == className + "-leaf-content"))
			) {
				var header = elem.childNodes[0];
				var content = elem.childNodes[1];
				var switcherRow = header.getElementsByClassName(className + "-leaf-switcher-row");
				if (switcherRow.length == 1) {
					var toggleButtons = switcherRow[0].getElementsByClassName(className + "-leaf-switcher");
					if (toggleButtons.length == 1) {
						var toggleButton = toggleButtons[0];
						if (open === undefined) {
							open = content.style.display == "none";
						}
						if (open) {
							content.style.display = "";
							toggleButton.className = className + "-leaf-switcher " + className + "-leaf-switcher-closed";
						} else {
							updateMinWidth();
							content.style.display = "none";
							toggleButton.className = className + "-leaf-switcher " + className + "-leaf-switcher-open";
						}
					}
				}
			}
		}

		function traverseLeaf(parentLeaf, parentContainer, leaf, parentId, order) {
			var leafContainer = L.DomUtil.create("div", className + "-leaf", parentContainer);
			var leafHeader = L.DomUtil.create("div", className + "-leaf-header", leafContainer);
			var leafTitle = L.DomUtil.create("span", className + "-leaf-title", leafHeader);
			if (leaf.childLayers != undefined && leaf.childLayers.length > 0) {
				var leafSwitcherRow = L.DomUtil.create("span", className + "-leaf-switcher-row", leafHeader);
				var leafSwitcher = L.DomUtil.create("span", className + "-leaf-switcher", leafSwitcherRow);
				L.DomEvent.on(leafSwitcher, "click", function (event) {
					toggleChildrenVisibility(event.srcElement.parentElement.parentElement.parentElement);
				});
			}
			var layerId = parentId + "_" + leaf.code + "_" + order;

			function toggleLayerMULTIPLE(sourceElementId, checked) {
				if (sourceElementId) {
					// add or remove currently selected layer
					if (checked) {
						me.addLayer(leaf, sourceElementId);
					} else {
						me.removeLayer(sourceElementId);
					}
				}
			}

			function toggleLayerSINGLE(parentElementId, sourceElementId) {
				me.removeLayers(parentLeaf, parentElementId);
				me.addLayer(leaf, sourceElementId);
			}

			if (leaf.active) {
				switch (parentLeaf.selectType) {
					case "NONE":	//
						leafTitle.innerHTML = "<label>" + leaf.name + "</label>"
						break;
					case "SINGLE":	// radio-group
					{
						var parentLeafCode = parentLeaf.code;
						var checkbox = L.DomUtil.create("input", "", leafTitle);
						checkbox.name = parentLeafCode;
						checkbox.id = layerId;
						checkbox.parentId = parentId;
						checkbox.type = "radio";
						var label = L.DomUtil.create("label", "", leafTitle);
						var labelText = L.DomUtil.create("span", "", label);
						labelText.innerHTML = leaf.name;
						L.DomEvent.on(checkbox, "change", function (event) {
							var sourceElementId = event.srcElement.id;
							if (sourceElementId) {
								var parentElementId = event.srcElement.parentId;
								var checked = event.srcElement.checked;
								if (checked) {
									toggleLayerSINGLE(parentElementId, sourceElementId);
								}
							}
						});
						L.DomEvent.on(label, "click", function (event) {
							var checkbox = event.srcElement.parentElement.parentElement.getElementsByTagName("input")[0];
							checkbox.checked = true;
							var parentElementId = checkbox.parentId;
							var sourceElementId = checkbox.id;
							toggleLayerSINGLE(parentElementId, sourceElementId);
						});
						if (leaf.selectedByDefault) {
							checkbox.checked = true;
							toggleLayerSINGLE(parentId, layerId);
						}
					}
						break;
					case "MULTIPLE":
					default:	// checkboxes
					{
						var parentLeafCode = parentLeaf.code;
						var checkbox = L.DomUtil.create("input", "", leafTitle);
						checkbox.name = parentLeafCode;
						checkbox.id = layerId;
						checkbox.parentId = parentId;
						checkbox.type = "checkbox";
						var label = L.DomUtil.create("label", "", leafTitle);
						var labelText = L.DomUtil.create("span", "", label);
						labelText.innerHTML = leaf.name;
						L.DomEvent.on(checkbox, "change", function (event) {
							toggleLayerMULTIPLE(event.srcElement.id, event.srcElement.checked);
						});
						L.DomEvent.on(label, "click", function (event) {
							var checkbox = event.srcElement.parentElement.parentElement.getElementsByTagName("input")[0];
							var checked;
							if (checkbox.checked == true) {
								checkbox.checked = undefined;
								checked = false;
							} else {
								checkbox.checked = true;
								checked = true;
							}
							var sourceElementId = checkbox.id;
							toggleLayerMULTIPLE(sourceElementId, checked);
						});
						if (leaf.selectedByDefault) {
							checkbox.checked = true;
							toggleLayerMULTIPLE(layerId, true);
						}
					}
						break;
				}
				var leafContent = L.DomUtil.create("div", className + "-leaf-content", leafContainer);
				if (leaf.childLayers && leaf.childLayers.length > 0) {
					for (var i in leaf.childLayers) {
						var child = leaf.childLayers[i];
						if (child) {
							traverseLeaf(leaf, leafContent, child, layerId, i);
						}
					}
				}

				toggleChildrenVisibility(leafContainer.parentNode.parentNode, parentLeaf.openByDefault);
			}
		}

		var layersTree = this.options.layersTree;
		if (Object.prototype.toString.call(layersTree) === '[object Array]') {
			for (var i in layersTree) {
				var layerTree = layersTree[i];
				traverseLeaf(layerTree, layersContainer, layerTree, "", 0);
			}
		} else {
			traverseLeaf(layersTree, layersContainer, layersTree, "", 0);
		}

		return container;
	},
	onRemove: function (map) {

	},
	copyParams: function (layerSettings, exceptions) {
		var params = {};
		for (var paramKey in layerSettings.params) {
			if (!exceptions || !exceptions.test(paramKey)) {
				params[paramKey] = layerSettings.params[paramKey];
			}
		}
		return params;
	},
	addLayer: function (layerSettings, layerId) {
		var map = this._map;
		switch (layerSettings.serviceType) {
			case "OSM":
				var layer = L.tileLayer(layerSettings.params.url, {});
				this._addLayer(layer, layerId);
				break;
			case "TILE":
				var layer = L.tileLayer(layerSettings.params.url, {});
				this._addLayer(layer, layerId);
				break;
			case "BING":
				var layer = new L.BingLayer(layerSettings.params.url);
				this._addLayer(layer, layerId);
				break;
			case "GOOGLE":
				layer = new L.Google();
				this.addLayer(layer, layerId);
				break;
			case "GOOGLE_TERRAIN":
				var layer = new L.Google("TERRAIN");
				this._addLayer(layer, layerId);
				break;
			case "WMS":
			{
				var params = this.copyParams(layerSettings, /\burl\b/gi);
				var layer = L.tileLayer.wms(layerSettings.params.url, params).addTo(map);
				this._addLayer(layer, layerId);
			}
				break;
			case "WFS":
			{
				var layer = new L.GeoJSON().addTo(map);
				var params = this.copyParams(layerSettings, /\b(url|style)\b/gi);

				this._addLayer(layer, layerId);
				var wfsHandler = function () {
					var customParams = {
						bbox: map.getBounds().toBBoxString(),
					};
					var params2 = L.Util.extend(params, customParams);
					var wfsUrl = layerSettings.params.url + L.Util.getParamString(params2);
					$.ajax({
						url: wfsUrl,
						dataType: 'json',
						success: function (data) {
							layer.clearLayers();
							layer.addData(data);
							if (layerSettings.params.style) {
								var style = JSON.parse(layerSettings.params.style);
								layer.eachLayer(function (layer) {
									layer.setStyle(style);
								});
							}
						},
						error: function () {
							console.error(arguments);
						}
					});
				}
				wfsHandler();
				this._reloadHandlers[layerId + "__moveend"] = wfsHandler;
				map.on("moveend", wfsHandler);
			}
				break;
			default:
				break;
		}
	},
	_addLayer: function (layer, layerId) {
		if (layer) {
			this._layers[layerId] = layer;
			this._map.addLayer(layer);
		}
	},
	removeLayers: function (layer, parentId) {
		this.removeLayer(layer);
		if (layer.childLayers && layer.childLayers.length > 0) {
			for (var i in layer.childLayers) {
				var child = layer.childLayers[i];
				this.removeLayer(parentId + "_" + child.code + "_" + i);
			}
		}
	},
	removeLayer: function (layerId) {
		var map = this._map;
		if (this._layers.hasOwnProperty(layerId)) {
			var layer = this._layers[layerId];
			map.removeLayer(layer);
			delete layer;
		}
		if (this._reloadHandlers.hasOwnProperty(layerId + "__moveend")) {
			map.off("moveend", this._reloadHandlers[layerId + "__moveend"]);
			delete this._reloadHandlers[layerId + "__moveend"];

		}
	}
})
;
