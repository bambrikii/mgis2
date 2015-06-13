angular.module("mgis.isogd.documents.service", [ "ui.router" ]) //
.factory("ISOGDDocumentsService", function($http) {
	var factory = {};
	var documents = [ //
	{
		"id" : 1,
		"name" : "Document1"
	}, //
	{
		"id" : 2,
		"name" : "Document2"
	}, //
	{
		"id" : 3,
		"name" : "Document3"
	} //
	];
	factory.list = function(bookId) {
		return documents;
	}
	factory.save = function() {

	}
	factory.remove = function() {

	}
	return factory;
});
