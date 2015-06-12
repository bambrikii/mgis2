angular.module("mgis.isogd.books", [ "ui.router" ]) //
.factory("ISOGDBooksService", function($http) {
	var factory = {};
	var books = [ //
	{
		"id" : 1,
		"name" : "Book1"
	}, //
	{
		"id" : 2,
		"name" : "Book2"
	}, //
	{
		"id" : 3,
		"name" : "Book3"
	} //
	];
	factory.list = function(volumeId) {
		return books;
	}
	factory.save = function() {

	}
	factory.remove = function() {

	}
	return factory;
});
