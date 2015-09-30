angular.module("mgis.isogd.documents.search", ["ui.router"])
	.config(function ($stateProvider) {
		$stateProvider
			.state("isogd.search", {
				url: "/search",
				templateUrl: "app2/isogd/document-search/document-list.htm"
			});
	})
	.controller("ISOGDDocumentSearchController", function () {

	})
;
