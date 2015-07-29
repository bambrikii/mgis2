angular.module("mgis.lands.lands.service", ["ui.router", 'ngResource'])
	.factory("LandsLandService", function ($resource, $q) {
		var res = $resource('rest/lands/land/:id.json');
		return {
			get: function (id, first, max, cadastralNumber) {
				var deferred = $q.defer();
				res.get({
					id: id,
					cadastralNumber: cadastralNumber
				}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var land = {
					id: item.id,
					cadastralNumber: item.cadastralNumber,
					stateRealEstateCadastreaStaging: item.stateRealEstateCadastreaStaging,
					landAreas: item.landAreas,
					landCategory: item.landCategory ? {id: item.landCategory.id} : null,
					allowedUsageByDictionary: item.allowedUsageByDictionary ? {id: item.allowedUsageByDictionary.id} : null,
					allowedUsageByDocument: item.allowedUsageByDocument,
					allowedUsageByTerritorialZone: item.allowedUsageByTerritorialZone ? {id: item.allowedUsageByTerritorialZone.id} : null,
					addressOfMunicipalEntity: item.addressOfMunicipalEntity ? {id: item.addressOfMunicipalEntity.id} : null,
					addressOfPlacementType: item.addressOfPlacementType ? {id: item.addressOfPlacementType.id} : null,
					addressPlacement: item.addressPlacement,
					address: item.address ? {id: item.address.id} : null
				};
				res.save({id: item.id}, land, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}

	})
;
