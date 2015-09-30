angular.module("mgis.isogd.documents.service", ["ui.router"
	, "mgis.error.service"]) //
	.factory("ISOGDDocumentsService", function ($resource, $q, MGISErrorService) {
		var res = $resource('rest/isogd/documents/:id.json');
		return {
			get: function (id, first, max, volumeId) {
				var deferred = $q.defer();
				res.get({
					id: id,
					volumeId: volumeId,
					first: first,
					max: max
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (document) {
				var deferred = $q.defer();
				res.save({id: document.id}, {
					id: document.id,
					name: document.name,
					docDate: document.docDate,
					docNumber: document.docNumber,
					volume: {
						id: document.volume.id
					},
					author: document.author && document.author.id ? {id: document.author.id} : null,
					documentSubObject: {
						id: document.documentSubObject.id
					},
					commonPart: document.commonPart,
					specialPart: document.specialPart
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (documentId) {
				var deferred = $q.defer();
				res.remove({
					id: documentId
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					// TODO: handle error
				});
				return deferred.promise;
			},
			listDocumentSubObjectsByVolumeId: function (volumeId, first, max) {
				var res = $resource("rest/isogd/documents/listDocumentSubObjectsByVolumeId/:volumeId.json");
				var deferred = $q.defer();
				res.get({
					volumeId: volumeId,
					first: first,
					max: max
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			loadDocumentClassByVolumeId: function (volumeId) {
				var res = $resource("rest/isogd/documents/readDocumentClassByVolumeId/:volumeId.json");
				var deferred = $q.defer();
				res.get({
					volumeId: volumeId
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	});
