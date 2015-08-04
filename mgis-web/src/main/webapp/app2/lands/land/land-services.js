angular.module("mgis.lands.services", ["ui.router", 'ngResource'])
	.factory("LandsLandService", function ($resource, $q) {
		var res = $resource('rest/lands/land/:id.json');

		function buildLandAreas(landAreas) {
			var landAreas2 = [];
			for (var i in landAreas) {
				var area = landAreas[i];
				landAreas2.push({id: area.id, value: area.value, landAreaType: {id: area.landAreaType.id}});
			}
			return landAreas2;
		}

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
				var rights = item.rights;
				var characteristics = item.characteristics;
				var control = item.control;
				var landAreas = item.landAreas;
				var landAreas2 = buildLandAreas(landAreas);
				var land = {
					id: item.id,
					cadastralNumber: item.cadastralNumber,
					stateRealEstateCadastreaStaging: item.stateRealEstateCadastreaStaging,
					landAreas: landAreas2,
					landCategory: item.landCategory ? {id: item.landCategory.id} : null,
					allowedUsageByDictionary: item.allowedUsageByDictionary ? {id: item.allowedUsageByDictionary.id} : null,
					allowedUsageByDocument: item.allowedUsageByDocument,
					allowedUsageByTerritorialZone: item.allowedUsageByTerritorialZone ? {id: item.allowedUsageByTerritorialZone.id} : null,
					addressOfMunicipalEntity: item.addressOfMunicipalEntity ? {id: item.addressOfMunicipalEntity.id} : null,
					addressOfPlacementType: item.addressOfPlacementType ? {id: item.addressOfPlacementType.id} : null,
					addressPlacement: item.addressPlacement,
					address: item.address ? {id: item.address.id} : null,
					rights: {
						ownershipForm: rights.ownershipForm ? {id: rights.ownershipForm.id} : null,
						rightKind: rights.rightKind ? {id: rights.rightKind.id} : null,
						rightOwner: rights.rightOwner ? {id: rights.rightOwner.id} : null,
						encumbrance: rights.encumbrance,
						obligations: rights.obligations,
						ownershipDate: rights.ownershipDate,
						terminationDate: rights.terminationDate,
						comment: rights.comment,
						share: rights.share,
						annualTax: rights.annualTax,
						totalArea: rights.totalArea
					},
					characteristics: {
						cadastralCost: characteristics.cadastralCost,
						specificIndexOfCadastralCost: characteristics.specificIndexOfCadastralCost,
						marketCost: characteristics.marketCost,
						mortgageValue: characteristics.mortgageValue,
						implementationDate: characteristics.implementationDate,
						terminationDate: characteristics.terminationDate,
						standardCost: characteristics.standardCost,
						typeOfEngineeringSupportArea: characteristics.typeOfEngineeringSupportArea ? {id: characteristics.typeOfEngineeringSupportArea.id} : null,
						distanceToTheConnectionPoint: characteristics.distanceToTheConnectionPoint,
						distanceToTheConnectionPointLength: characteristics.distanceToTheConnectionPointLength,
						distanceToTheObjectsOfTransportInfrastructure: characteristics.distanceToTheObjectsOfTransportInfrastructure,
						nearestMunicipalEntity: characteristics.nearestMunicipalEntity ? {id: characteristics.nearestMunicipalEntity.id} : null,
						distanceToTheNearestMunicipalEntity: characteristics.distanceToTheNearestMunicipalEntity,
						countrySubject: characteristics.countrySubject ? {id: characteristics.countrySubject.id} : null,
						distanceToTheCountrySubjectCenter: characteristics.distanceToTheCountrySubjectCenter
					},
					control: {
						executivePerson: control.executivePerson ? {id: control.executivePerson.id} : null,
						inspectedPerson: control.inspectedPerson ? {id: control.inspectedPerson.id} : null,
						inspectionDate: control.inspectionDate,
						inspectionKind: control.inspectionKind ? {id: control.inspectionKind.id} : null,
						inspectionReason: control.inspectionReason ? {id: control.inspectionReason.id} : null,
						inspectionReasonDescription: control.inspectionReasonDescription,
						inspectionResultAvailabilityOfViolations: control.inspectionResultAvailabilityOfViolations ? {id: control.inspectionResultAvailabilityOfViolations.id} : null,
						inspectionResultDescription: control.inspectionResultDescription,
						inspectionSubject: control.inspectionSubject ? {id: control.inspectionSubject.id} : null,
						inspectionType: control.inspectionType ? {id: control.inspectionType.id} : null,
						penaltyAmount: control.penaltyAmount,
						timelineForViolations: control.timelineForViolations
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

	})
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
	})
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
	})
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
	})
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
	})
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
	})
	.factory("LandsLandAreaTypeService", function ($resource, $q) {
		var res = $resource('rest/lands/area_types/:id.json');
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