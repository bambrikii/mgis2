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
					address: item.address ? {id: item.address.id} : null,
					rights: {
						ownershipForm: item.rights.ownershipForm ? {id: item.rights.ownershipForm.id} : null,
						rightKind: item.rights.rightKind ? {id: item.rights.rightKind.id} : null,
						rightOwner: item.rights.rightOwner ? {id: item.rights.rightOwner.id} : null,
						encumbrance: item.rights.encumbrance,
						obligations: item.rights.obligations,
						ownershipDate: item.rights.ownershipDate,
						terminationDate: item.rights.terminationDate,
						comment: item.rights.comment,
						share: item.rights.share,
						annualTax: item.rights.annualTax,
						totalArea: item.rights.totalArea
					},
					characteristics: {
						cadastralCost: item.characteristics.cadastralCost,
						specificIndexOfCadastralCost: item.characteristics.specificIndexOfCadastralCost,
						marketCost: item.characteristics.marketCost,
						mortgageValue: item.characteristics.mortgageValue,
						implementationDate: item.characteristics.implementationDate,
						terminationDate: item.characteristics.terminationDate,
						standardCost: item.characteristics.standardCost,
						typeOfEngineeringSupportArea: item.characteristics.typeOfEngineeringSupportArea ? {id: item.characteristics.typeOfEngineeringSupportArea.id} : null,
						distanceToTheConnectionPoint: item.characteristics.distanceToTheConnectionPoint,
						distanceToTheConnectionPointLength: item.characteristics.distanceToTheConnectionPointLength,
						distanceToTheObjectsOfTransportInfrastructure: item.characteristics.distanceToTheObjectsOfTransportInfrastructure,
						nearestMunicipalEntity: item.characteristics.nearestMunicipalEntity ? {id: item.characteristics.nearestMunicipalEntity.id} : null,
						distanceToTheNearestMunicipalEntity: item.characteristics.distanceToTheNearestMunicipalEntity,
						countrySubject: item.characteristics.countrySubject ? {id: item.characteristics.countrySubject.id} : null,
						distanceToTheCountrySubjectCenter: item.characteristics.distanceToTheCountrySubjectCenter
					}
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

	});

angular.module("mgis.lands.inspection_kind.service", ["ngResource"])
	.factory("LandsInspectionKindService", function ($resource, $q) {
		var res = $resource('rest/lands/inspection_kinds/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.lands.inspection_type.service", ["ngResource"])
	.factory("LandsInspectionTypeService", function ($resource, $q) {
		var res = $resource('rest/lands/inspection_types/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});


angular.module("mgis.lands.inspection_reason.service", ["ngResource"])
	.factory("LandsInspectionReasonService", function ($resource, $q) {
		var res = $resource('rest/lands/inspection_reasons/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.lands.inspection_subject.service", ["ngResource"])
	.factory("LandsInspectionSubjectService", function ($resource, $q) {
		var res = $resource('rest/lands/inspection_subjects/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});

angular.module("mgis.lands.availability_of_violations.service", ["ngResource"])
	.factory("LandsAvailabilityOfViolationsService", function ($resource, $q) {
		var res = $resource('rest/lands/availability_of_violations/:id.json');
		return {
			get: function (id, first, max) {
				var deferred = $q.defer();
				res.get({id: id}, {
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			}
		}
	});
