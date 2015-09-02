angular.module("mgis.lands.land.map", [])
	.factory("LandsLandMapService", function () {
		var mapContainer = {
			map: undefined,
			mapElement: undefined
		}
		return {
			checkMap: function () {
				return mapContainer.map == undefined ? undefined : {
					map: mapContainer.map,
					mapElement: mapContainer.mapElement
				};
			},
			setMap: function (map, mapElement) {
				mapContainer.map = map;
				mapContainer.mapElement = mapElement;
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
				console.log(mapContainer.childNodes[1]);
				var mapIsJustCreated = false;
				var map;
				var mapCheck = LandsLandMapService.checkMap();
				if (mapCheck == undefined) {
					var mapElement = document.createElement("div");
					map = L.map(mapElement);
					LandsLandMapService.setMap(map, mapElement);
					mapContainer.appendChild(mapElement);
					mapIsJustCreated = true;
				} else {
					map = mapCheck.map;
					mapContainer.appendChild(mapCheck.mapElement);
				}
				var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
					attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
					maxZoom: 18,
					zoomControl: false
				}).addTo(map);
				map.attributionControl.setPrefix('');
				var landsLayer = new L.GeoJSON();

				var reloadLands0 = function (loadComplete) {
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
						cql_filter: "id in (" + $scope.landId + ")"
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
				var reloadLands = function () {
					reloadLands0(function () {
						map.fitBounds(landsLayer.getBounds());
					});
				}
				if (mapIsJustCreated) {
					map.on("load", function () {
						reloadLands();
					});
					map.setView(new L.LatLng(0, 0), 1);
				} else {
					map.setView(new L.LatLng(0, 0), 1);
					reloadLands();
				}
			}
		}
	})
;
