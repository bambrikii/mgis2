angular.module("mgis.isogd.volumes.service", [ "ui.router", "ui.bootstrap", "ngResource" ]) //
.factory("ISOGDVolumesService", function($resource, $q) {

	var factory = {};

	factory.list = function(bookId, first, max) {
		console.log("list: " + bookId);
		var deferred = $q.defer();
		$resource('rest/isogd/volumes/list.json', {
			bookId : bookId
		}, {
			get : {
				method : 'GET'
			}
		}).get({
			first : first,
			max : max
		}, function(data) {
			deferred.resolve(data);
		})
		return deferred.promise;
	}

	factory.get = function(volumeId) {
		var deferred = $q.defer();
		$resource('rest/isogd/volumes/:volumeId.json', {
			volumeId : volumeId
		}).get({}, function(data) {
			deferred.resolve(data);
		})
		return deferred.promise;
	}

	factory.save = function(bookId, volume) {
		var deferred = $q.defer();
		console.log(bookId);
		$resource('rest/isogd/volumes/:volumeId.json', {
			volumeId : volume.id
		}, {
			save : {
				method : 'POST'
			}
		}).save({
			id : volume.id,
			name : volume.name,
			book : {
				id : bookId
			}
		}, function(data) {
			deferred.resolve(data);
		})
		return deferred.promise;
	}

	factory.remove = function(volumeId) {
		var deferred = $q.defer();
		console.log("volumeId: " + volumeId);
		$resource('rest/isogd/volumes/:volumeId.json', {
			volumeId : volumeId
		}, {
			remove : {
				method : 'DELETE'
			}
		}).remove({
			id : volumeId
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	return factory;
});
