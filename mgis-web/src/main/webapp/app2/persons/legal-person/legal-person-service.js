angular.module("mgis.persons.person.legal.service", ["ngResource"])
	.factory("LegalPersonService", function ($resource, $q) {
		var res = $resource('rest/persons/legal-persons/:id.json');
		return {
			get: function (id, first, max, name) {
				var deferred = $q.defer();
				res.get({id: id, first: first, max: max, name: name}, {}, function (data) {
					deferred.resolve(data);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				var p = {}
				angular.copy(item, p);
				p.ownershipForm = item.ownershipForm && item.ownershipForm.id ? {id: item.ownershipForm.id} : null;
				if (item.founders) {
					p.founders = new Array();
					for (var i in item.founders) {
						p.founders.push({id: item.founders[i].id});
					}
				}
				if (item.representatives) {
					p.representatives = new Array();
					for (var i in item.representatives) {
						p.representatives.push(item.representatives[i]);
					}
				}
				p.activityType = item.activityType && item.activityType.id ? {id: item.activityType.id} : null;
				p.okogu = item.okogu && item.okogu.id ? {id: item.okogu.id} : null;
				p.organizationalForm = item.organizationalForm && item.organizationalForm.id ? {id: item.organizationalForm.id} : null;
				p.actualAddressTerritoryOkatoCode = item.actualAddressTerritoryOkatoCode && item.actualAddressTerritoryOkatoCode.id ? {id: item.actualAddressTerritoryOkatoCode.id} : null;
				p.actualAddress = item.actualAddress && item.actualAddress.id ? {id: item.actualAddress.id} : null;
				p.legalAddressTerritoryOkatoCode = item.legalAddressTerritoryOkatoCode && item.legalAddressTerritoryOkatoCode.id ? {id: item.legalAddressTerritoryOkatoCode.id} : null;
				p.legalAddress = item.legalAddress && item.legalAddress.id ? {id: item.legalAddress.id} : null;


				res.save({id: item.id,}, p, function (data) {
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
