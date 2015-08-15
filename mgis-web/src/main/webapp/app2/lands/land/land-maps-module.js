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


    })
;
