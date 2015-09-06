angular.module("mgis.lands.land.map", [])
	.factory("LandsLandMapService", function () {
		var container = {
			map: undefined,
			mapElement: undefined,
			landsLayer: undefined
		}

		var reloadLand0 = function (landId, loadComplete) {
			var map = container.map;
			var landsLayer = container.landsLayer;
			var geoJsonUrl = "proxy?http://localhost:8081/geoserver/mgis2/wfs";
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

		var reloadLand = function (landId) {
			reloadLand0(landId, function () {
				container.map.fitBounds(container.landsLayer.getBounds());
			});
		}

		function createMap(landId) {
			var map = container.map;
			var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
				attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
				maxZoom: 18,
				zoomControl: false
			}).addTo(map);
			map.attributionControl.setPrefix('');
			container.landsLayer = new L.GeoJSON();
			map.on("load", function () {
				reloadLand(landId);
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
				console.log("created");
				if (e.layerType == "polygon") {
					console.log(e.layerType);
					console.log(e.layer._latlngs);
				}
			});
			//map.on("draw:edited", function (e) {
			//	console.log("edited");
			//	console.log(e);
			//});
			//map.on("draw:deleted", function (e) {
			//	console.log("deleted");
			//	console.log(e);
			//});
			//map.on("draw:drawstart", function (e) {
			//	console.log("drawstart");
			//	console.log(e);
			//});
			//map.on("draw:drawstop", function (e) {
			//	console.log("drawstop");
			//	console.log(e);
			//});
			//map.on("draw:editstart", function (e) {
			//	console.log("editstart");
			//	console.log(e);
			//});
			//map.on("draw:editstop", function (e) {
			//	console.log("editstop");
			//	console.log(e);
			//});
		}

		return {
			checkMap2: function (mapContainer, landId) {
				if (container.map == undefined) {
					var mapElement = document.createElement("div");
					var map = L.map(mapElement);
					container.map = map;
					container.mapElement = mapElement;
					mapContainer.appendChild(container.mapElement);
					createMap(landId);
					map.setView(new L.LatLng(0, 0), 1);
					return {
						map: container.map,
						mapElement: container.mapElement,
						mapIsJustCreated: true
					}
				} else {
					var map = container.map;
					mapContainer.appendChild(container.mapElement);
					map.setView(new L.LatLng(0, 0), 1);
					reloadLand(landId);
					return {
						map: container.map,
						mapElement: container.mapElement,
						mapIsJustCreated: false
					}
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
			controller: function ($scope, $element, LandsLandMapService) {
				var mapContainer = $element[0].getElementsByClassName("land-map-container")[0];
				var checkResult = LandsLandMapService.checkMap2(mapContainer, $scope.landId);
			}
		}
	})
;
