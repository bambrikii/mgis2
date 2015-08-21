angular.module("mgis.lands.maps", ["ui.router", "ui.bootstrap", "ui.select", "openlayers-directive", //
//	"mgis.commons", //
	"mgis.lands.services",
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
	.controller("LandsMapsController", function ($scope, $state, LandsLandService) {

		ol.ProxyHost = "/proxy.jsp?url=";

		$scope.displayOnTheMap = function () {
			$state.go("^.lands");
		}

		var projection2 = ol.proj.get('EPSG:3857');

		var landsVectorSource = new ol.source.ServerVector({
			format: new ol.format.GeoJSON(),
			loader: function (extent, resolution, projection) {
				console.log(extent);
				console.log(JSON.stringify(projection));
				var url = 'proxy?' + ('http://localhost:8081/geoserver/mgis2/wfs?service=WFS&version=1.1.0&layers=mgis2:lands_land&request=GetFeature&typeNames=lands_land&bbox=' + extent.join(',') + ',' + projection.a + '&outputFormat=application/json&srsName=' + /*projection.a*/ "EPSG:3857"/* "EPSG:41001"*/);
				$.ajax({
					url: url,
					success: function (data) {
						var features = landsVectorSource.readFeatures(data);
						console.log(features.length);
						landsVectorSource.addFeatures(landsVectorSource.readFeatures(data));
					},
					error: function (data) {
						console.log("error: " + JSON.stringify(data));
					}
				});
			},
			//projection: 'EPSG:4326',
			//projection: projection2,
			strategy: ol.loadingstrategy.createTile(new ol.tilegrid.XYZ({
				maxZoom: 19
			}))
		});

		var mousePositionControl = new ol.control.MousePosition({
			coordinateFormat: ol.coordinate.createStringXY(4),
			//projection: 'EPSG:3857',
			// comment the following two lines to have the mouse position
			// be placed within the map.
			className: 'custom-mouse-position',
			target: document.getElementById('mouse-position'),
			undefinedHTML: '&nbsp;'
		});

		//
		var map = new ol.Map({
			target: 'map',
			numZoomLevels: 20,
			//projection: new ol.proj.Projection({
			//	code: 'EPSG:4326',
			//	units: 'm',
			//	extent: [-180.0000, -90.0000, 180.0000, 90.0000]
			//}),
			//displayProjection: new ol.proj.Projection({
			//	code: 'EPSG:4326',
			//	units: 'm',
			//	extent: [-180.0000, -90.0000, 180.0000, 90.0000]
			//}),
			//projection: "EPSG:3857",
			//displayProjection: "EPSG:3857",
			layers: [
				//new ol.layer.Group({
				//	'title': 'Base maps',
				//	layers: [
				//new ol.layer.Tile({
				//	title: 'Water color',
				//	type: 'base',
				//	visible: false,
				//	source: new ol.source.Stamen({
				//		layer: 'watercolor'
				//	})
				//}),
				new ol.layer.Tile({
					title: 'OSM',
					type: 'base',
					visible: true,
					source: new ol.source.OSM()
				})
				//,
				//new ol.layer.Tile({
				//	title: 'Satellite',
				//	type: 'base',
				//	visible: false,
				//	source: new ol.source.MapQuest({layer: 'sat'})
				//})
				//]
				//})
				,
				//new ol.layer.Group({
				//	title: 'Overlays',
				//	layers: [
				//		new ol.layer.Tile({
				//			title: 'Countries',
				//			source: new ol.source.TileWMS({
				//				url: 'http://demo.opengeo.org/geoserver/wms',
				//				params: {'LAYERS': 'ne:ne_10m_admin_1_states_provinces_lines_shp'},
				//				serverType: 'geoserver'
				//			})
				//		})
				//	]
				//}),
				//new ol.layer.Group({
				//	title: 'MGIS2',
				//	layers: [
				//new ol.layer.Tile({
				//	title: 'Lands',
				//	source: new ol.source.TileWMS({
				//		url: 'http://demo.opengeo.org/geoserver/wms',
				//		params: {'LAYERS': 'ne:ne_10m_admin_1_states_provinces_lines_shp'},
				//		serverType: 'geoserver'
				//	})
				//}),
				new ol.layer.Vector({
					title: 'Lands Vector',
					source: landsVectorSource,
					//projection: 'EPSG:3857',
					projection: projection2,
					//projection: "EPSG:4326",
					style: new ol.style.Style({
						stroke: new ol.style.Stroke({
							color: 'blue',
							width: 3
						}),
						fill: new ol.style.Fill({
							color: 'rgba(0, 0, 255, 0.1)'
						})
					})
				})
				//]
				//})
			],
			view: new ol.View({
				//projection: projection2,
				center: ol.proj.transform([/*41.9833, 45.0500*/ 0, 0], 'EPSG:4326', 'EPSG:3857'), //ol.proj.transform([-0.92, 52.96], 'EPSG:4326', 'EPSG:3857'),
				zoom: 1
			}),
			controls: ol.control.defaults({
				attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
					collapsible: true
				})
			}).extend([new ol.control.ScaleLine({
				units: 'metric'
			}), mousePositionControl]),
		});
		console.log(map);

		var layerSwitcher = new ol.control.LayerSwitcher({
			//tipLabel: 'Légende' // Optional label for button
		});
		map.addControl(layerSwitcher);

		$scope.find = function () {
			LandsLandService.get("", $scope.first, $scope.max, $scope.cadastralNumber).then(function (data) {
				$scope.list = data.list;
				$scope.first = data.first;
				$scope.max = data.max;
			});
		}

	})
;
