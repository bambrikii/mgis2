angular.module("mgis.isogd.books.service", [ "ui.router", "ngResource" ]) //
.factory("ISOGDBooksService", function($resource, $q) {
	var factory = {};

	factory.list = function(sectionId, first, max) {
		var deferred = $q.defer();
		$resource('rest/isogd/books/list.json', {
			sectionId : sectionId
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

	factory.get = function(bookId) {
		var deferred = $q.defer();
		$resource('rest/isogd/books/:bookId.json', {
			bookId : bookId
		}).get({}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	factory.save = function(sectionId, book) {
		var deferred = $q.defer();
		$resource('rest/isogd/books/:bookId.json', {
			bookId : book.id
		}, {
			save : {
				method : 'POST'
			}
		}).save({
			id : book.id,
			name : book.name,
			section : {
				id : sectionId
			}
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}

	factory.remove = function(bookId) {
		var deferred = $q.defer();
		$resource('rest/isogd/books/:bookId.json', {
			bookId : bookId
		}, {
			remove : {
				method : 'DELETE'
			}
		}).remove({
			id : bookId
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}
	return factory;
});
