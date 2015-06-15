angular.module("mgis.isogd.sections.service", [ "ui.router", 'ngResource' ]) //
.factory("ISOGDSectionsService", function($resource, $q) {
	var factory = {};
	var sections = [];
	factory.list = function(first, max) {
		var deferred = $q.defer();
		sections = $resource('rest/isogd/sections/list.json').get({
			first : first,
			max : max
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}
	factory.get = function(sectionId) {
		var deferred = $q.defer();
		$resource('rest/isogd/sections/:sectionId.json', {
			sectionId : sectionId
		}).get({

		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}
	factory.save = function(section) {
		var deferred = $q.defer();
		$resource('rest/isogd/sections/:sectionId.json', {
			sectionId : section.id
		}, {
			save : {
				method : 'POST'
			}
		}).save({
			id : section.id,
			name : section.name
		}, function(data) {
			deferred.resolve(data);
		});
		return deferred.promise;
	}
	factory.remove = function() {

	}
	return factory;
});
