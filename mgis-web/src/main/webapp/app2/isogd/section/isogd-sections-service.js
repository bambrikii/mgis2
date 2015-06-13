angular.module("mgis.isogd.sections.service", [ "ui.router" ]) //
.factory("ISOGDSectionsService", function($http) {
	var factory = {};
	var sections = [ //
	{
		"id" : 1,
		"name" : "Section1"
	}, //
	{
		"id" : 2,
		"name" : "Section2"
	}, //
	{
		"id" : 3,
		"name" : "Section3"
	} //
	];
	factory.list = function() {
		return sections;
	}
	factory.save = function() {

	}
	factory.remove = function() {

	}
	return factory;
});
