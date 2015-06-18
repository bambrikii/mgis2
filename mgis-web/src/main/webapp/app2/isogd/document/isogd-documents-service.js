angular.module("mgis.isogd.documents.service", [ "ui.router" ]) //
.factory("ISOGDDocumentsService", function($resource, $q) {
	var factory = {};

	factory.list = function(volumeId, first, max) {
		var deferred = $q.defer();
		$resource('rest/isogd/documents/list.json', {
			volumeId : volumeId
		}, {
			get : {
				method : 'GET'
			}
		}).get({
			first : first,
			max : max
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	factory.get = function(documentId) {
		var deferred = $q.defer();
		$resource('rest/isogd/documents/:documentId.json', {
			documentId : documentId
		}).get({}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	factory.save = function(volumeId, document) {
		var deferred = $q.defer();
		$resource('rest/isogd/documents/:documentId.json', {
			documentId : document.id
		}, {
			save : {
				method : 'POST'
			}
		}).save({
			id : volumeId,
			name : document.name,
			volume : {
				id : volumeId
			}
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	factory.remove = function(documentId) {
		var deferred = $q.defer();
		$resource('rest/isogd/documents/:documentId.json', {
			documentId : documentId
		}, {
			remove : {
				method : 'DELETE'
			}
		}).remove({
			id : documentId
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	return factory;
});
