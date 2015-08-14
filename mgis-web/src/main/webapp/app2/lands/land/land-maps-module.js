angular.module("mgis.lands.maps", ["ui.router", "ui.bootstrap", "ui.select", "openlayers-directive"//, //
//	"mgis.commons", //
//	"mgis.lands.services",
//	"mgis.nc.services",
//	"mgis.common.executive_person.service",
//	"mgis.terr-zones.zone.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("lands.maps", {
				url: "/maps/",
				templateUrl: "app2/lands/land/land-maps.htm"
			});
	})
//
	.controller("LandsMapsController", function ($scope, $state) {

		ol.ProxyHost = "/proxy.jsp?url=";

		$scope.displayOnTheMap = function () {
			$state.go("^.lands");
		}


		//$scope.london = {
		//	lat: 51.505,
		//	lon: -0.09,
		//	zoom: 3
		//}

		// Layers
		//$scope.layers = [
		//	{
		//		name: 'OpenStreetMap',
		//		active: true,
		//		source: {
		//			type: 'OSM'
		//		}
		//	},
		//	{
		//		name: 'OpenCycleMap',
		//		active: false,
		//		source: {
		//			type: 'OSM',
		//			url: 'http://{a-c}.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png',
		//			attribution: 'All maps &copy; <a href="http://www.opencyclemap.org/">OpenCycleMap</a>'
		//		}
		//	},
		//	{
		//		name: 'MapBox Night',
		//		active: false,
		//		source: {
		//			type: 'TileJSON',
		//			url: 'https://api.tiles.mapbox.com/v3/examples.map-0l53fhk2.jsonp'
		//		}
		//	},
		//	{
		//		name: 'MapBox Terrain',
		//		active: false,
		//		source: {
		//			type: 'TileJSON',
		//			url: 'https://api.tiles.mapbox.com/v3/examples.map-i86nkdio.jsonp'
		//		}
		//	},
		//	{
		//		name: 'Mapbox Geography Class',
		//		active: false,
		//		source: {
		//			type: 'TileJSON',
		//			url: 'http://api.tiles.mapbox.com/v3/mapbox.geography-class.jsonp'
		//		}
		//	},
		//	{
		//		name: "MGIS2 Lands",
		//		active: true,
		//		source: {
		//			type: "",
		//			url: "http://localhost:8081/"
		//		}
		//	}
		//];
		//$scope.layers = {
		//	main: {
		//		source: {
		//			type: 'BingMaps',
		//			key: 'Aj6XtE1Q1rIvehmjn2Rh1LR2qvMGZ-8vPS9Hn3jCeUiToM77JFnf-kFRzyMELDol',
		//			imagerySet: 'Aerial'
		//		}
		//	}
		//}
		$scope.changeLayer = function (layer) {
			$scope.layers.map(function (l) {
				l.active = (l === layer);
			});
		}

		// Controls
		var rotateNorthControl = function (opt_options) {
			var options = opt_options || {};
			var rotation = 0;
			var button = document.createElement('button');
			button.innerHTML = 'N';
			var this_ = this;
			var handleRotateNorth = function (e) {
				rotation += 90;
				this_.getMap().getView().setRotation(rotation);
			};
			button.addEventListener('click', handleRotateNorth, false);
			button.addEventListener('touchstart', handleRotateNorth, false);
			var element = document.createElement('div');
			element.className = 'rotate-north ol-unselectable ol-control';
			element.appendChild(button);
			ol.control.Control.call(this, {
				element: element,
				target: options.target
			});
		}

		ol.inherits(rotateNorthControl, ol.control.Control);
		//$scope.rotateNorth = {
		//	control: new rotateNorthControl()
		//}


		angular.extend($scope, {
			center: {
				lat: 43.88,
				lon: 7.57,
				zoom: 4
			},
			layers: [
				{
					active: true,
					source: {
						type: 'BingMaps',
						key: 'Aj6XtE1Q1rIvehmjn2Rh1LR2qvMGZ-8vPS9Hn3jCeUiToM77JFnf-kFRzyMELDol',
						imagerySet: 'Aerial'
					}
				},
				{
					name: 'OpenStreetMap',
					active: true,
					source: {
						type: 'OSM'
					}
				}, {
					name: 'Countries',
					active: true,
					source: {
						type: "TileWMS",
						url: 'http://demo.opengeo.org/geoserver/wms',
						params: {'LAYERS': 'ne:ne_10m_admin_1_states_provinces_lines_shp'},
						serverType: 'geoserver'
					}
				}
			],
			rotateNorth: {
				control: new rotateNorthControl()
			},
			layerSwitcher: {
				control: new ol.control.LayerSwitcher({})
			}
		});
	})
;
