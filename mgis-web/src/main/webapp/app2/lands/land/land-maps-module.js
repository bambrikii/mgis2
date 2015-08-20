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

        var landsVectorSource = new ol.source.ServerVector({
            format: new ol.format.GeoJSON(),
            loader: function (extent, resolution, projection) {
                var url = 'proxy?' + ('http://localhost:8081/geoserver/mgis2/wfs?service=WFS&version=1.1.0&layers=mgis2:lands_land&request=GetFeature&typeNames=lands_land&srsName=EPSG:3857&bbox=' + extent.join(','));
                $.ajax({
                    url: url,
                    success: function (data) {
                        landsVectorSource.addFeatures(landsVectorSource.readFeatures(data));
                    },
                    error: function () {
                        console.log("error:");
                    }
                });
            },
            projection: 'EPSG:3857',
            strategy: ol.loadingstrategy.createTile(new ol.tilegrid.XYZ({
                maxZoom: 19
            }))
        });

        //
        var map = new ol.Map({
            target: 'map',
            layers: [
                new ol.layer.Group({
                    'title': 'Base maps',
                    layers: [
                        new ol.layer.Tile({
                            title: 'Water color',
                            type: 'base',
                            visible: false,
                            source: new ol.source.Stamen({
                                layer: 'watercolor'
                            })
                        }),
                        new ol.layer.Tile({
                            title: 'OSM',
                            type: 'base',
                            visible: true,
                            source: new ol.source.OSM()
                        }),
                        new ol.layer.Tile({
                            title: 'Satellite',
                            type: 'base',
                            visible: false,
                            source: new ol.source.MapQuest({layer: 'sat'})
                        })
                    ]
                }),
                new ol.layer.Group({
                    title: 'Overlays',
                    layers: [
                        new ol.layer.Tile({
                            title: 'Countries',
                            source: new ol.source.TileWMS({
                                url: 'http://demo.opengeo.org/geoserver/wms',
                                params: {'LAYERS': 'ne:ne_10m_admin_1_states_provinces_lines_shp'},
                                serverType: 'geoserver'
                            })
                        })
                    ]
                }),
                new ol.layer.Group({
                    title: 'MGIS2',
                    layers: [
                        new ol.layer.Tile({
                            title: 'Lands',
                            source: new ol.source.TileWMS({
                                url: 'http://demo.opengeo.org/geoserver/wms',
                                params: {'LAYERS': 'ne:ne_10m_admin_1_states_provinces_lines_shp'},
                                serverType: 'geoserver'
                            })
                        }),
                        new ol.layer.Vector({
                            source: landsVectorSource,
                            style: new ol.style.Style({
                                image: new ol.style.Circle({
                                    radius: 3,
                                    fill: new ol.style.Fill({color: 'white'})
                                })
                            })
                        })
                    ]
                })
            ],
            view: new ol.View({
                center: ol.proj.transform([-0.92, 52.96], 'EPSG:4326', 'EPSG:3857'),
                zoom: 6
            })
        });

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
