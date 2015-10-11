angular.module("mgis.geo.map.layer.service", ["ngResource",
	"mgis.error.service"
])
	.constant("GEO_LAYER_SELECT_TYPES", ["NONE", "SINGLE", "MULTIPLE"])
	.constant("GEO_LAYER_SERVICE_TYPES", ["OSM", "TILE", "WFS", "WMS"])
	.factory("GEOMapLayerService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/geo/layers/:id.json');
		return {
			get: function (id, first, max, code, parentId) {
				var deferred = $q.defer();
				var requestParams = {id: id, first: first, max: max, code: code};
				if (parentId) {
					requestParams.parentId = parentId;
				}
				res.get(requestParams, {}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {};
				angular.copy(item, p);
				res.save({id: item.id}, p, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			sampleTree: function () {
				var layersTree = {
					id: -1,
					code: "root-group",
					name: "Root Group",
					selectType: "NONE",
					childLayers: [
						{
							id: -1,
							code: "base-layers",
							name: "Base Layers",
							selectType: "SINGLE",
							childLayers: [
								{
									id: 1,
									type: "tile",
									code: "osm",
									name: "OpenStreetMap (открытый WMS-сервис)",
									active: true,
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

							]
						},
						{
							id: -1,
							code: "overlays",
							name: "Overlays",
							selectType: "MULTIPLE",
							childLayers: [
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
						}
					]
				}
				return layersTree;
			}
		}
	})
;
