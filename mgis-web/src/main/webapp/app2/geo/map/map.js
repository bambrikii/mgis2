angular.module("mgis.geo.map", ["ui.router"])
	.config(function ($stateProvider) {
		$stateProvider
			.state("geo.map", {
				url: "/map",
				templateUrl: "app2/geo/map/map.htm"
			});
	})
	.controller("GEOMapController", function () {
		var layers = {
			base: [
				{
					id: 1,
					type: "tile",
					code: "osm",
					name: "OpenStreetMap (открытый WMS-сервис)",
					params: {
						url: "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
					}
				},
				//{
				//	id: 2,
				//	type: "bing",
				//	code: "bing",
				//	name: "Bing",
				//	params: {
				//	url: "Anqm0F_JjIZvT0P3abS6KONpaBaKuTnITRrnYuiJCE0WOhH6ZbE4DzeT6brvKVR5"
				//  }
				//},
				//{
				//	id: 3,
				//	type: "google",
				//	code: "google",
				//	name: "Google"
				//},
				//{
				//	id: 4,
				//	type: "google-terrain",
				//	code: "google-terrain",
				//	name: "Google Terrain"
				//},
				{
					id: -1,
					type: "tile",
					code: "cosmoShooting",
					name: "Космическая съемка",
					params: {
						url: "http://geoportal.sovzond.ru:10090/geoserver/gwc/service/wms"
					}
				},
				{
					id: -1,
					type: "tile",
					code: "addressPlan",
					name: "Адресный план",
					params: {
						url: "http://geoportal.sovzond.ru:10090/geoserver/gwc/service/wms"
					}
				},
				{
					id: -1,
					type: "tile",
					code: "topo1-25000",
					name: "Топооснова (вектор в масштабах 1:25000, 1:10000)",
					params: {
						url: "http://geoportal.sovzond.ru:10090/geoserver/gwc/service/wms"
					}
				}

			],
			overlays: [
				{
					id: -1,
					code: "cadastral-info",
					name: "Кадастровая информация",
					childLayers: [
						{
							id: -1,
							type: "wms",
							code: "public-cadastral-map",
							name: "1. Публичная кадастровая карта",
							params: {
								url: "http://maps.rosreestr.ru/arcgis/services/Cadastre/CadastreWMS/MapServer/WmsServer"
							}
						},
						{
							id: -1,
							type: "wfs",
							code: "lands",
							name: "2. Земельные участки",
							params: {
								url: ""
							},
							childLayers: [
								{
									id: -1,
									type: "wfs",
									code: "state-realty-cadastre",
									name: "Земельные участки ГКН",
									params: {
										url: ""
									}
								},
								{
									id: -1,
									type: "wfs",
									code: "",
									name: "Проектируемые",
									params: {
										url: ""
									}
								},
								{
									id: -1,
									type: "wfs",
									code: "",
									name: "Ожидающие постановки на учет",
									params: {
										url: ""
									}
								},
								{
									id: -1,
									type: "wfs",
									code: "",
									name: "Поставленные на учет",
									params: {
										url: ""
									}
								},
								{
									id: -1,
									type: "wfs",
									code: "",
									name: "Удаленные",
									params: {
										url: ""
									}
								}
							]
						}
					]
				},
				{
					id: -1,
					type: "wfs",
					code: "urban-planning-info",
					name: "Градостроительная информация",
					params: {
						url: ""
					},
					childLayers: [
						{
							id: -1,
							name: "1. Административно-территориальное деление"
						},
						{
							id: -1,
							name: "2. Генеральный план"
						},
						{
							id: -1,
							name: "3. Территориальные зоны правил землепользования и застройки (ПЗЗ)"
						},
						{
							id: -1,
							name: "4. Особо охраняемые территории (территориальные зоны, имеющие статус особо охраняемых территорий)"
						},
						{
							id: -1,
							name: "5. Зоны охраны объектов культурного наследия"
						},
						{
							id: -1,
							name: "6. Санитарно-защитные зоны"
						}
					]
				}
			]
		};
		var map = L.map("geo-map");
		map.setView(new L.LatLng(0, 0), 1);
		var baseLayers = {};
		var groupedOverlays = {};
		var baseLayersObj = {};
		var groupedOverlaysObj = {};
		baseLayers["Base Layers"] = baseLayersObj;
		groupedOverlays["Overlays"] = groupedOverlaysObj;

		function traverseLayers(parent, layers) {
			for (var i in layers) {
				var layerSettings = layers[i];
				var layer;
				switch (layerSettings.type) {
					case "tile":
						layer = L.tileLayer(layerSettings.params.url, {}).addTo(map);
						break;
					case "bing":
						layer = new L.BingLayer(layerSettings.params.url).addTo(map);
						break;
					case "google":
						layer = new L.Google();
						break;
					case "google-terrain":
						layer = new L.Google("TERRAIN");
						break;
					case "wms":
					{
						layer = L.tileLayer.wms(layerSettings.params.url, {
							layers: layerSettings.params.layers,
							format: layerSettings.params.format,
							transparent: layerSettings.params.transparent,
							attribution: layerSettings.params.attribution
						});
					}
						break;
					case "wfs":
					{
						var wfsLayer = new L.GeoJSON().addTo(map);
						var defaultParameters = {
							service: 'WFS',
							version: '1.0.0',
							request: 'getFeature',
							typeName: layerSettings.params.typeName,
							maxFeatures: 3000,
							outputFormat: 'application/json'
						};

						var customParams = {
							bbox: map.getBounds().toBBoxString(),
						};
						var parameters = L.Util.extend(defaultParameters, customParams);
						var wfsUrl = layerSettings.params.url + L.Util.getParamString(parameters);
						$.ajax({
							url: wfsUrl,
							dataType: 'jsonp',
							success: function (data) {
								wfsLayer.addData(data);
							}
						});
						layer = wfsLayer;
					}
						break;
					default:
						break;
				}
				if (layer) {
					parent[layerSettings.name] = layer;
				}
				if (layerSettings.childLayers && layerSettings.childLayers.length > 0) {
					traverseLayers(parent, layerSettings.childLayers);
				}
			}
		}

		traverseLayers(baseLayersObj, layers.base);
		traverseLayers(groupedOverlaysObj, layers.overlays);

		map.attributionControl.setPrefix('');
		map.setView(new L.LatLng(0, 0), 1);

		var options = {};

		L.control.groupedLayers(baseLayersObj, groupedOverlays, options).addTo(map);
		var layersTree = {
			id: -1,
			code: "root-group",
			name: "Root Group",
			childLayers: [
				{
					base: {
						id: -1,
						code: "base-layers",
						name: "Base Layers",
						childLayers: layers.base
					},
					overlays: {
						id: -1,
						code: "overlays",
						name: "Overlays",
						childLayers: layers.overlays
					}
				}
			]
		}
		L.control.LayersTreeControl(layersTree).addTo(map);
	})
;
