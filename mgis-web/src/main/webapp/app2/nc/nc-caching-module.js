/**
 * Created by asd on 29.07.15.
 */
// TODO:
angular.module("mgis.nc.cache", [
	"mgis.nc.oktmo.service",
	"mgis.nc.okato.service",
	"mgis.nc.territorial_zone.service",
	"mgis.nc.land_allowed_usage.service"
])
	.factory("NC_CachingModule", function (NcOKTMOService, NcOKATOService, NcTerritorialZoneService, NcLandAllowedUsageService) {
		var okatoList;
		var oktmoList;
		var terrZoneList;
		var landAllowedUsageList;
		return {
			okato: function (id, from, max) {
				if (okatoList) {
					return {
						then: function (func) {
							return func(okatoList);
						}
					}
				}
				return NcOKATOService.get(id, from, max).then(function (data) {
					okatoList = data;
				});
			},
			oktmo: function (id, from, max) {
				if (oktmoList) {
					return {
						then: function (func) {
							return func(oktmoList);
						}
					}
				}
				return NcOKTMOService.get(id, from, max).then(function (data) {
					oktmoList = data;
				});
			},
			terrZone: function (id, from, max) {
				if (terrZoneList) {
					return {
						then: function (func) {
							return func(terrZoneList);
						}
					}
				}
				return NcTerritorialZoneService.get(id, from, max).then(function (data) {
					terrZoneList = data;
				})
			},
			landAllowedUsage: function (id, from, max) {
				if (landAllowedUsageList) {
					return {
						list: function () {
							return landAllowedUsageList;
						}

					}
				}
				return NcLandAllowedUsageService.get(id, from, max).then(function (data) {
					landAllowedUsageList = data;
				})
			}

		}
	})
