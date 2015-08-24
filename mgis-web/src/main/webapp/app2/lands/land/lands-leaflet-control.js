L.Control.MGIS2LandsSelector = L.Control.extend({
	options: {
		position: 'topright',
		expand: false,
		className: 'leaflet-control-mgis-lands',
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
		this._addListeners = new Array();
		this._removeListeners = new Array();
	},
	onAdd: function (map) {
		this._map = map;
		var className = this.options.className;
		var container = this._container = L.DomUtil.create('div', className + ' leaflet-control-layers leaflet-control');
		//if (!L.Browser.touch) {
		L.DomEvent.disableClickPropagation(container);
		L.DomEvent.on(container, 'wheel', L.DomEvent.stopPropagation);
		//} else {
		//L.DomEvent.on(container, 'click', L.DomEvent.stopPropagation);
		//}

		container.setAttribute("aria-haspopup", true);
		var landsList = this._landsList = L.DomUtil.create("div", className + "-list", container);

		var toggleLink = this._toggleLink = L.DomUtil.create('a', className + '-toggle', container);
		toggleLink.href = "#/lands/maps/";
		toggleLink.title = "Lands";
		toggleLink.text = " ";

		var me = this;
		L.DomEvent
			.on(container, 'mouseover', function (event) {
				this._toggleExpand(event, true && me._landsList.innerHTML != "");
			}, this)
			.on(container, 'mouseout', function (event) {
				this._toggleExpand(event, false);
			}, this)
			.on(toggleLink, 'click', function (event) {
				this._toggleExpand(event, me._landsList.innerHTML != "");
			}, this)
		;


		this._update();
		this._toggleExpand({}, this.options.expand);

		return container;
	},
	onRemove: function (map) {
		this._addListeners.splice(0, this._addListeners.length);
		this._removeListeners.splice(0, this._removeListeners.length);
	},
	_update: function () {
		if (!this._container) {
			return;
		}
		// TODO:
	},
	subscribeToAddLand: function (listener) {
		this._addListeners.push(listener);
	},
	subscribeToRemoveLand: function (listener) {
		this._removeListeners.push(listener);
	},
	addLand: function (land) {
		var addResult = true;
		for (var i in this._addListeners) {
			addResult &= this._addListeners[i](land);
		}
		if (addResult) {
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
			this._toggleExpand({}, true);
		}
	},
	removeLand: function (land) {
		var removeResult = true;
		for (var i in this._removeListeners) {
			removeResult &= this._removeListeners[i](land);
		}
		if (removeResult) {
			var buttons = this._landsList.getElementsByTagName("button");
			for (var i in  buttons) {
				var button = buttons[i];
				if ((land.id && land.id == button.landId) ||
					(land.cadastralnumber && land.cadastralnumber == button.landCadastralNumber)) {
					var parent = button.parentNode;
					parent.parentNode.removeChild(parent);
					if (this._landsList.innerHTML == "") {
						this._toggleExpand({}, false);
					}
					return true;
				}
			}
		}
		return false;
	},
	clearLands: function () {
		for (var land2 in this._landsList.getElementsByTagName("button")) {
			this.removeLand({id: land2.landId, cadastralnumber: land2.cadastralnumber});
		}
	},
	_onRemoveButtonClick: function (event) {
		var target = event.currentTarget;
		this.removeLand({id: target.landId, cadastralnumber: target.landCadastralNumber});
	},
	_toggleExpand: function (event, expand) {
		if (
			(expand != undefined && expand) ||
			(expand == undefined && L.DomUtil.hasClass(this._container, 'leaflet-control-mgis-lands-collapsed'))
		) {
			L.DomUtil.addClass(this._container, 'leaflet-control-mgis-lands-expanded');
			L.DomUtil.removeClass(this._container, 'leaflet-control-mgis-lands-collapsed');
		} else {
			L.DomUtil.addClass(this._container, 'leaflet-control-mgis-lands-collapsed');
			L.DomUtil.removeClass(this._container, 'leaflet-control-mgis-lands-expanded');
		}
	}
})
;
