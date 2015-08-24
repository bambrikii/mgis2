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
    .controller("LandsMapsController", function ($scope, $state, LandsLandService, $compile, $rootScope) {

        ol.ProxyHost = "/proxy.jsp?url=";

        $scope.displayOnTheMap = function () {
            $state.go("^.lands");
        }

        $scope.find = function () {
            LandsLandService.get("", $scope.first, $scope.max, $scope.cadastralNumber).then(function (data) {
                $scope.list = data.list;
                $scope.first = data.first;
                $scope.max = data.max;
            });
        }

        var map = L.map('map');
        var osmLayer = L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors',
            maxZoom: 18,
            zoomControl: true
        }).addTo(map);

        map.attributionControl.setPrefix(''); // Don't show the 'Powered by Leaflet' text. Attribution overload

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
                bbox: map.getBounds().toBBoxString(),
            }
            var parameters = L.Util.extend(defaultParameters, customParams);
            console.log(geoJsonUrl + L.Util.getParamString(parameters));

            $.ajax({
                url: geoJsonUrl + L.Util.getParamString(parameters),
                datatype: 'json',
                jsonCallback: 'getJson',
                success: function (data) {
                    landsLayer.clearLayers();
                    console.log("data: " + JSON.stringify(data));
                    landsLayer.addData(data);
//                    var markers = new Array();
                    landsLayer.eachLayer(function (layer) {
                        var popupScope = $rootScope.$new();
                        popupScope.edit = function (id) {
                            alert("edit.id: " + id);
                        }
                        popupScope.addToSelected = function (id) {
                            alert("addToSelected.id: " + id);
                        }
                        popupScope.remove = function (id) {
                            alert("remove.id: " + id);
                        }
                        var content = '<div><h2 translate>Lands.Land</h2>' +
                                '<span translate>CadastralNumber</span>: ' + layer.feature.properties.cadastralnumber + '<br />' +
                                '<span translate>Lands.Land.StateRealEstateCadastralStaging</span>: ' + layer.feature.properties.staterealestatecadastreastaging + '<br />'
                                + '<button ng-click="edit(' + layer.feature.properties.id + ')" translate>Edit</button>' //
                                + '<button ng-click="addToSelected(' + layer.feature.properties.id + ')" translate>Add</button>' //
                                + '<button ng-click="remove(' + layer.feature.properties.id + ')" translate>Remove</button>' //
                                + '</div>'
                            ;
                        var linkFunction = $compile(angular.element(content));
                        layer.bindPopup(linkFunction(popupScope)[0]);
//                        markers.push(layer.getBounds());
                    });
                    map.addLayer(landsLayer);
                    if (loadComplete) {
                        loadComplete();
                    }
                }
            });
        };
        map.on("moveend", function () {
            reloadLands();
        });
        map.on("load", function () {
            reloadLands(function () {
                console.log("bounds: " + JSON.stringify(landsLayer.getBounds()));
                map.fitBounds(landsLayer.getBounds());
            });
        });
        map.setView(new L.LatLng(0, 0), 1);

        //var baseLayers = {
        //    'Mapnik': osmLayer
        //}
        //var overlayLayers = {
        //    'Clouds': landsLayer
        //}
        //var control = L.control.activeLayers(baseLayers, overlayLayers)
        //control.addTo(map)

        var baseLayers = {
            "Base Layers": {
                "OpenStreetMap": osmLayer
            }
        }
        var groupedOverlays = {
            "Lands": {
                "Restaurants": landsLayer
            }
        };

        var options = {exclusiveGroups: ["Landmarks"]};

        L.control.groupedLayers(baseLayers, groupedOverlays, options).addTo(map);
    })
;
