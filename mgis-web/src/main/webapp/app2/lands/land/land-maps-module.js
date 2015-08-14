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
		$scope.displayOnTheMap = function () {
			$state.go("^.lands");
		}

		$scope.london = {
			lat: 51.505,
			lon: -0.09,
			zoom: 3
		}
		$scope.layers = [
			{
				name: 'OpenStreetMap',
				active: true,
				source: {
					type: 'OSM'
				}
			},
			{
				name: 'OpenCycleMap',
				active: false,
				source: {
					type: 'OSM',
					url: 'http://{a-c}.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png',
					attribution: 'All maps &copy; <a href="http://www.opencyclemap.org/">OpenCycleMap</a>'
				}
			},
			{
				name: 'MapBox Night',
				active: false,
				source: {
					type: 'TileJSON',
					url: 'https://api.tiles.mapbox.com/v3/examples.map-0l53fhk2.jsonp'
				}
			},
			{
				name: 'MapBox Terrain',
				active: false,
				source: {
					type: 'TileJSON',
					url: 'https://api.tiles.mapbox.com/v3/examples.map-i86nkdio.jsonp'
				}
			},
			{
				name: 'Mapbox Geography Class',
				active: false,
				source: {
					type: 'TileJSON',
					url: 'http://api.tiles.mapbox.com/v3/mapbox.geography-class.jsonp'
				}
			}
		];
		$scope.changeLayer = function (layer) {
			$scope.layers.map(function (l) {
				l.active = (l === layer);
			});
		}
	})
;
