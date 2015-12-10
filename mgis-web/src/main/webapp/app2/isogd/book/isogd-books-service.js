angular.module("mgis.isogd.books.service", ["ui.router", "ngResource",
	"mgis.error.service"
]) //
	.factory("ISOGDBooksService", function ($resource, $q, MGISErrorService) {

		var res = $resource("rest/isogd/books/:id.json");
		var swapRes = $resource('rest/isogd/books/swap-orders.json');
		return {
			get: function (id, first, max, sectionId) {
				var deferred = $q.defer();
				res.get({
					id: id,
					first: first,
					max: max,
					sectionId: sectionId
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			save: function (item) {
				var deferred = $q.defer();
				res.save({id: item.id}, {
					id: item.id,
					name: item.name,
					section: {
						id: item.section.id
					},
					documentObject: {
						id: item.documentObject.id
					}
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			remove: function (id) {
				var deferred = $q.defer();
				res.remove({
					id: id
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			listDocumentObjectsBySectionId: function (sectionId) {
				var deferred = $q.defer();
				var res = $resource("rest/isogd/books/listDocumentObjectsBySectionId/:sectionId.json");
				res.get({
					sectionId: sectionId
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			},
			swapOrders: function (sourceId, targetId) {
				var deferred = $q.defer();
				swapRes.save({}, {
					sourceId: sourceId,
					targetId: targetId
				}, function (data) {
					deferred.resolve(data);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}

	});
