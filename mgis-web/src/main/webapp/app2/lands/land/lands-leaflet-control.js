L.Control.MGIS2LandsSelector = L.Control.extend({
	options: {
		position: 'topright',
		//width: '50px',
		placeholder: "Search ..."//,
		//collapsed: true,
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
		this._lands = new Array();
		this._lands.push({
			cadastralnumber: "000123"
		});
		this._lands.push({
			cadastralnumber: "000124"
		});
	},
	onAdd: function (map) {
		this._map = map;
		var className = 'leaflet-control-mgis-lands'
		var container = this._container = L.DomUtil.create('div', className + ' leaflet-draw-toolbar leaflet-bar leaflet-control leaflet-control-layers-expanded');
		//L.DomEvent
		//	.addListener(container, 'click', L.DomEvent.stopPropagation)
		//	.addListener(container, 'click', L.DomEvent.preventDefault)
		//	.addListener(container, 'click', function () {
		//		//drawnItems.clearLayers();
		//	});
		//if (!L.Browser.touch) {
		L.DomEvent.disableClickPropagation(container);
		L.DomEvent.on(container, 'wheel', L.DomEvent.stopPropagation);
		//} else {
		//L.DomEvent.on(container, 'click', L.DomEvent.stopPropagation);
		//}
		container.setAttribute("aria-haspopup", true);

		var toggleLink = this._toggleLink = L.DomUtil.create('a', className + '-toggle', container);
		toggleLink.href = "#/lands/maps/";
		toggleLink.title = "Lands";
		toggleLink.text = "Lands";

		var landsList = this._landsList = L.DomUtil.create("div", className + "-lands-list", container);

		//container.appendChild(toggleLink);
		//container.appendChild(landsList);

		map.on("mgislandadd", this._onLandAdd, this);
		map.on("mgislandremove", this._onLandRemove, this);

		this._update();

		return container;
	},
	onRemove: function (map) {
		map.off("mgislandadd", this._onLandAdd);
		map.off("mgislandremove", this._onLandRemove);
	},
	_onLandAdd: function (e) {

	},
	_onLandRemove: function (e) {

	},
	_update: function () {
		if (!this._container) {
			return;
		}
		//this._container.innerHTML = "";
		this._landsList.innerHTML = "";
		for (i in this._lands) {
			var land = this._lands[i];
			this._addLand(land);
		}
	},
	_addLand: function (land) {
		var label = document.createElement('span');
		label.innerHTML = "Land: " + land.cadastralnumber;
		var removeButton = document.createElement('button');
		removeButton.innerHTML = "Remove";
		L.DomEvent.on(removeButton, 'click', this._onRemoveButtonClick, this);
		var container = document.createElement("div");
		container.appendChild(label);
		container.appendChild(removeButton);
		this._landsList.appendChild(container);
	},
	_onRemoveButtonClick: function () {
	}
})
;
