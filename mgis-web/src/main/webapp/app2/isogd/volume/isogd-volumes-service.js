angular.module("mgis.isogd.volumes.service", [ "ui.router" ]) //
.factory("ISOGDVolumesService", function($http) {
	var factory = {};
	var volumes = [ //
	{
		"id" : 1,
		"name" : "Volume1"
	}, //
	{
		"id" : 2,
		"name" : "Volume2"
	}, //
	{
		"id" : 3,
		"name" : "Volume3"
	} //
	];
	factory.list = function(sectionId) {
		return volumes;
	}
	factory.save = function() {

	}
	factory.remove = function() {

	}
	return factory;
});
