L.Control.NestedLayersSwitcher = L.Control.extend({
	options: {
		position: "topright",
		expand: false,
		className: "leaflet-nested-layers-switcher"
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
		return container;
	},
	onRemove: function(map){

	}
})
