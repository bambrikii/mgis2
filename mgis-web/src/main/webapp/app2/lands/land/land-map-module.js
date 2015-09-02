angular.module("mgis.lands.land.map", [])
	.directive("landMap", function ($rootScope) {
		return {
			restrict: "E",
			scope: {
				landId: "@"
			},
			templateUrl: "app2/lands/land/land-map-control.htm",
			controller: function ($scope, $element) {
				///*
				var mapContainer = $element[0].getElementsByClassName("land-map-container")[0];

				function removeChildren(node) {
					while (node.firstChild) {
						removeChildren(node.firstChild);
						node.removeChild(node.firstChild);
					}
				}

				//$(mapContainer).empty();
				removeChildren(mapContainer);

				console.log(mapContainer.children);
				mapContainer.innerHTML = "";
				console.log(mapContainer);
				var mapElement = document.createElement("div");
				mapElement.setAttribute("id", "land-map");
				mapContainer.appendChild(mapElement);
				var map = L.map(mapElement);
				//*/
				//var map = L.map('land-map');
				var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
					attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
					maxZoom: 18,
					zoomControl: false
				}).addTo(map);
				map.attributionControl.setPrefix('');
				var landsLayer = new L.GeoJSON();

				var reloadLands = function (loadComplete) {
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
				//map.on("moveend", function () {
				//	reloadLands();
				//});
//				map.on("load", function () {
//					reloadLands(function () {
////						console.log("reloadLands" + JSON.stringify(landsLayer.getBounds()));
//						//map.setView(new L.LatLng(0, 0), 1);
//						map.fitBounds(landsLayer.getBounds());
//					});
//				});
				map.setView(new L.LatLng(0, 0), 1);
				map.fitWorld();
			}
		}
	})
;
