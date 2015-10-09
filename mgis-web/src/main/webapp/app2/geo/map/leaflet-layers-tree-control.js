L.Control.LayersTreeControl = L.Control.extend({
	options: {
		position: "topright",
		expand: false,
		className: "leaflet-nested-layers-switcher",
		layersTree: {}
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
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
		function traverseLeaf(parentLeaf, parentContainer, leaf) {
			var parentLeafCode = parentLeaf.code;
			var type = '';
			var leafContainer = L.DomUtil.create("div", className + "-leaf", parentContainer);
			var leafHeader = L.DomUtil.create("div", className + "-leaf-header", leafContainer);
			if (leaf.singleSelect) { // radio-group
				leafHeader.innerHTML = "<input type='checkbox' name='" + parentLeafCode + "'><label>" + leaf.name + "</label>"
				L.DomEvent.on(leafHeader, "click", function (event) {
					// select a single layer
					// remove current group layers
					// add selected layer
				});
			} else { // checkboxes
				leafHeader.innerHTML = "<input type='radio' name='" + leaf.code + "'><label>" + leaf.name + "</label>"
				L.DomEvent.on(leafHeader, "click", function (event) {
					// add or remove currently selected layer
				});
			}
			var leafContent = L.DomUtil.create("div", className + "leaf-content", leafContainer);
			for (var i in leaf) {
				var child = leaf[i];
				// create a container

				if (child.childLayers && child.childLayers.length > 0) {
					traverseLeaf(leaf, leafContent, child);
				}
			}
		}

		var layersTree = this.options.layersTree;
		traverseLeaf(layersTree, container, layersTree);

		return container;
	},
	onRemove: function (map) {

	}
})
;
