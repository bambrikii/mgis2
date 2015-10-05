angular.module("mgis.lands.land.map", [
	"mgis.geo.geo-server.service",
	"mgis.error.service"
])
	.factory("LandsLandMapService", function (GEOGeoServerService, MGISErrorService) {
		var container = {
			map: undefined,
			mapElement: undefined,
			landsLayer: undefined,
			drawCreatedEvent: undefined,
			localWfsUrl: undefined
		}

		var reloadLand0 = function (landId, loadComplete) {
			var map = container.map;
			var landsLayer = container.landsLayer;
			var geoJsonUrl = container.localWfsUrl;
			var defaultParameters = {
				service: 'WFS',
				version: '1.0.0',
				request: 'getFeature',
				typeName: 'mgis2:lands_land',
				maxFeatures: 3000,
				outputFormat: 'application/json'
			}
			var customParams = {
				cql_filter: "id in (" + landId + ")"
			}
			var parameters = L.Util.extend(defaultParameters, customParams);
			$.ajax({
				url: geoJsonUrl + L.Util.getParamString(parameters),
				datatype: 'json',
				jsonCallback: 'getJson',
				success: function (data) {
					landsLayer.clearLayers();
					landsLayer.addData(data);
					map.addLayer(landsLayer);
					map.setView(new L.LatLng(0, 0), 1);
					if (loadComplete) {
						loadComplete();
					}
				}
			});
		}

		var reloadLand = function () {
			reloadLand0(container.landId, function () {
				container.map.fitBounds(container.landsLayer.getBounds());
			});
		}

		function loadSettings() {
			GEOGeoServerService.get("", 0, 0, "local-wfs").then(function (data) {
				if (data.totalNumberOfItems == 1) {
					container.localWfsUrl = data.list[0].url;
				} else {
					MGISErrorService.handleError({
						status: "GIS_SERVER_CONFIGURATION_REQUIRED",
						statusText: "GIS Server <local-wfs> should be configured."
					});
				}
			});
		}

		function createMap() {
			var map = container.map;
			var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
				attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
				maxZoom: 18,
				zoomControl: false
			}).addTo(map);
			map.attributionControl.setPrefix('');
			container.landsLayer = new L.GeoJSON();
			map.on("load", function () {
				reloadLand();
			});
			var drawnItems = new L.FeatureGroup();
			map.addLayer(drawnItems);
			var drawControl = new L.Control.Draw({
				edit: {
					featureGroup: drawnItems
				}, draw: {
					polyline: false,
					rectangle: false,
					circle: false,
					marker: false
				}
			});
			map.addControl(drawControl);
			map.on("draw:created", function (e) {
				if (e.layerType == "polygon") {
					if (container.drawCreatedEvent) {
						var geoJSONValue = JSON.stringify(e.layer.toGeoJSON());
						var wkt = Terraformer.WKT.convert(JSON.parse(geoJSONValue).geometry);
						container.drawCreatedEvent(container.landId, wkt);
					}
				}
			});
		}

		return {
			checkMap: function (mapContainer, landId, drawCreatedEvent) {
				container.landId = landId;
				if (container.map == undefined) {
					GEOGeoServerService.get("", 0, 0, "local-wfs").then(function (data) {
						if (data.totalNumberOfItems == 1) {
							container.localWfsUrl = data.list[0].url;
							var mapElement = document.createElement("div");
							var map = L.map(mapElement);
							container.map = map;
							container.mapElement = mapElement;
							mapContainer.appendChild(container.mapElement);
							createMap();
							map.setView(new L.LatLng(0, 0), 1);
							container.drawCreatedEvent = drawCreatedEvent;
						} else {
							MGISErrorService.handleError({
								status: "GIS_SERVER_CONFIGURATION_REQUIRED",
								statusText: "GIS Server <local-wfs> should be configured."
							});
						}
					});
				} else {
					var map = container.map;
					mapContainer.appendChild(container.mapElement);
					map.setView(new L.LatLng(0, 0), 1);
					reloadLand();
					container.drawCreatedEvent = drawCreatedEvent;
				}
			}
		}
	})
	.directive("landMap", function ($rootScope) {
		return {
			restrict: "E",
			scope: {
				landId: "@"
			},
			templateUrl: "app2/lands/land/land-map-control.htm",
			controller: function ($scope, $element, LandsLandMapService, LandsLandGeoService) {
				var mapContainer = $element[0].getElementsByClassName("land-map-container")[0];

				function createMap(landId) {
					LandsLandMapService.checkMap(mapContainer, landId, function (landId2, wktString) {
						if (landId) {
							LandsLandGeoService.save(landId2, wktString).then(function () {
								createMap(landId2);
							});
						}
					});
				}

				createMap($scope.landId);
			}
		}
	})
;
