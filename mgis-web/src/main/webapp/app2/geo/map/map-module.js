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

			var map = L.map("geo-map");
			map.attributionControl.setPrefix('');
			map.setView(new L.LatLng(0, 0), 1);

			var layerBuilders = {
				BING: function (layerSettings) {
					return new L.BingLayer(layerSettings.params.url);
				},
				GOOGLE: function (layerSettings) {
					return new L.Google();
				},
				GOOGLE_TERRAIN: function (layerSettings) {
					return new L.Google("TERRAIN");
				}
			};

			new L.Control.LayerTreeControl({
				layerTree: layers,
				openByDefault: true,
				layerBuilders: layerBuilders,
				featureBuilders: {
					WFS: {
						zoom: L.Control.LayerTreeControl.WFSZoomFeature
					}
				}
			}).addTo(map);
		});
	})
;
