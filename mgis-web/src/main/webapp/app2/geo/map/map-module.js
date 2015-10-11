angular.module("mgis.geo.map", ["ui.router",
	"mgis.geo.map.layer.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo.map", {
				url: "/map",
				templateUrl: "app2/geo/map/map.htm"
			});
	})
	.controller("GEOMapController", function (GEOMapLayerService) {
		GEOMapLayerService.get().then(function (data) {
			var layers = data.list;
			//var layers = GEOMapLayerService.sampleTree();
			//var layers = {
			//	base: layers.childLayers[0].childLayers,
			//	overlays: layers.childLayers[1].childLayers
			//};
			var map = L.map("geo-map");
			map.setView(new L.LatLng(0, 0), 1);
			var baseLayers = {};
			var groupedOverlays = {};
			var baseLayersObj = {};
			var groupedOverlaysObj = {};
			baseLayers["Base Layers"] = baseLayersObj;
			groupedOverlays["Overlays"] = groupedOverlaysObj;

			function traverseLayers(parent, layers) {
				for (var i in layers) {
					var layerSettings = layers[i];
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
						parent[layerSettings.name] = layer;
					}
					if (layerSettings.childLayers && layerSettings.childLayers.length > 0) {
						traverseLayers(parent, layerSettings.childLayers);
					}
				}
			}

			//traverseLayers(baseLayersObj, layers.base);
			//traverseLayers(groupedOverlaysObj, layers.overlays);

			map.attributionControl.setPrefix('');
			map.setView(new L.LatLng(0, 0), 1);

			var options = {};

			//L.control.groupedLayers(baseLayersObj, groupedOverlays, options).addTo(map);
			new L.Control.LayersTreeControl({layersTree: layers}).addTo(map);
		});
	})
;
