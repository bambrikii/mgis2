L.Control.MGIS2LandsSelector = L.Control.extend({
	options: {
		position: 'topright',
		collapsed: false,
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
	},
	onAdd: function (map) {
		this._map = map;
		var className = 'leaflet-control-mgis-lands';
		var container = this._container = L.DomUtil.create('div', className + ' leaflet-control-layers leaflet-control');
		//if (!L.Browser.touch) {
		L.DomEvent.disableClickPropagation(container);
		L.DomEvent.on(container, 'wheel', L.DomEvent.stopPropagation);
		//} else {
		//L.DomEvent.on(container, 'click', L.DomEvent.stopPropagation);
		//}
		container.setAttribute("aria-haspopup", true);

		L.DomEvent
			.on(container, 'mouseover', this._expand, this)
			.on(container, 'mouseout', this._collapse, this);

		var toggleLink = this._toggleLink = L.DomUtil.create('a', className + '-toggle', container);
		toggleLink.href = "#/lands/maps/";
		toggleLink.title = "Lands";
		toggleLink.text = " ";

		var me = this;
		L.DomEvent
			.on(toggleLink, 'click', function () {
				me._toggleExpandCollapse(L.DomUtil.addClass(this._container, 'leaflet-control-mgis-lands-collapsed'));
			});

		var landsList = this._landsList = L.DomUtil.create("div", className + "-list", container);

		map.on("mgislandadd", this._onLandAdd, this);
		map.on("mgislandremove", this._onLandRemove, this);

		this._update();
		this._toggleExpandCollapse(this.options.collapsed);

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
		// TODO:
	},
	addLand: function (land) {
		var label = document.createElement('span');
		label.innerHTML = land.cadastralnumber;
		var removeButton = document.createElement('button');
		removeButton.innerHTML = "---";
		removeButton.landId = land.id;
		removeButton.landCadastralNumber = land.cadastralnumber;
		L.DomEvent.on(removeButton, 'click', this._onRemoveButtonClick, this);
		var container = document.createElement("div");
		container.appendChild(label);
		container.appendChild(removeButton);
		this._landsList.appendChild(container);
	},
	removeLand: function (land) {
		for (var land2 in this._landsList.getElementsByTagName("button")) {
			if ((land.id && land.id == land2.lanId) ||
				(land.cadastralnumber && land.cadastralnumber == land2.landCadastralNumber)) {
				this._removeLand(land2)
				return true;
			}
		}
	},
	clearLands: function () {
		this._landsList.innerHTML = "";
	},
	_onRemoveButtonClick: function (event) {
		this._removeLand(event.currentTarget);
	},
	_removeLand: function (button) {
		var parent = button.parentNode;
		parent.parentNode.removeChild(parent);
	},
	_toggleExpandCollapse: function (collapsed) {
		if (collapsed) {
			this._collapse();
		} else {
			this._expand();
		}
	},
	_expand: function () {
		L.DomUtil.addClass(this._container, 'leaflet-control-mgis-lands-expanded');
		L.DomUtil.removeClass(this._container, 'leaflet-control-mgis-lands-collapsed');
	},
	_collapse: function () {
		L.DomUtil.addClass(this._container, 'leaflet-control-mgis-lands-collapsed');
		L.DomUtil.removeClass(this._container, 'leaflet-control-mgis-lands-expanded');
	}
})
;
