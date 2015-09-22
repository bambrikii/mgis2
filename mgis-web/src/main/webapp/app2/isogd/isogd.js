angular.module("mgis.isogd", ["ui.router", "ui.bootstrap", "angularUtils.directives.uiBreadcrumbs", //
	"mgis.commons", //
	"mgis.isogd.sections", "mgis.isogd.sections.service", //
	"mgis.isogd.volumes", "mgis.isogd.volumes.service", //
	"mgis.isogd.books", "mgis.isogd.books.service",//
	"mgis.isogd.documents", "mgis.isogd.documents.service", //
	"mgis.isogd.classifiers" //
]) //
	.config(function ($stateProvider, $urlRouterProvider) {

		$urlRouterProvider.when("/isogd/sections", "/isogd/sections/").when("/isogd", "/isogd/sections/");

		$stateProvider
			.state("isogd", {
				url: "/isogd",
				views: {
					"": {
						templateUrl: "app2/isogd/isogd.htm",
						controller: "ISOGDCtrl"
					}
				}
			});
	}) //
	.controller("ISOGDCtrl", function ($scope, $modal, ISOGDSectionsService, ISOGDBooksService, ISOGDVolumesService, ISOGDDocumentsService) {
	}) //
	.directive("breadcrumb", function (ISOGDSectionsService, ISOGDBooksService, ISOGDVolumesService) {
		return {
			restrict: "E",
			templateUrl: 'app2/isogd/breadcrumb.htm',
			link: function (scope, elem, args) {
				var sectionId = args.sectionid;
				var bookId = args.bookid;
				var volumeId = args.volumeid;
				var links = [];

				links.push({
					text: 'ISOGD.Sections',
					sref: 'isogd.sections()',
					active: true
				});

				if (sectionId) {
					ISOGDSectionsService.get(sectionId).then(function (section) {
						links.push({
							text: section.name,
							sref: 'isogd.books({sectionId:' + sectionId + '})',
							active: true
						});
						if (bookId) {
							ISOGDBooksService.get(bookId).then(function (book) {
								links.push({
									text: book.name,
									sref: 'isogd.volumes({sectionId:' + sectionId + ',bookId:' + bookId + '})'
								});
								if (volumeId) {
									ISOGDVolumesService.get(volumeId).then(function (volume) {
										links.push({
											text: volume.name,
											sref: 'isogd.documents({sectionId:' + sectionId + ',bookId:' + bookId + ',volumeId:' + volumeId + '})'
										});
									})
								}
							});
						}
					});
				}
				scope.links = links;
			}
		}
	});
