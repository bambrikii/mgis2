angular.module("mgis.isogd.search.service", ["ngResource",
	"mgis.error.service"
])
	.constant("ISOGDDocumentSearchConstants", {
		DATE_FORMAT: "YYYY/MM/DD"
	})
	.factory("ISOGDDocumentSearchService", function ($resource, $q, MGISErrorService, ISOGDDocumentSearchConstants) {
		var res = $resource('rest/isogd/search/list.json');
		return {
			get: function (id, first, max, section, docName, docDate, docNumber) {
				var deferred = $q.defer();
				res.get({
					id: id,
					sectionId: section && section.id ? section.id : null,
					docName: docName,
					docDate: docDate ? moment(docDate).format(ISOGDDocumentSearchConstants.DATE_FORMAT) : null,
					docNumber: docNumber,
					first: first,
					max: max
				}, function (result) {
					deferred.resolve(result);
				}, function (error) {
					MGISErrorService.handleError(error);
				});
				return deferred.promise;
			}
		}
	})
	.factory("ISOGDDocumentSearchConverterService", function (ISOGDDocumentSearchConstants) {
		return {
			toString: function (obj) {
				var arr = new Array();
				if (obj.section && obj.section.id) {
					arr.push("section:" + obj.section.id);
				}
				if (obj.docName) {
					arr.push("name:\"" + obj.docName + "\"");
				}
				if (obj.docNumber) {
					arr.push("number:" + obj.docNumber);
				}
				if (obj.docDate) {
					arr.push("date:" + moment(obj.docDate).format(ISOGDDocumentSearchConstants.DATE_FORMAT));
				}
				if (arr.length > 0) {
					return arr.join(" ");
				}
				return obj.docName;
			},
			toObject: function (str) {
				var obj = new Object();
				var matchFound = false;
				var pattern = /((name:((['"][^'"]+['"])|([^\s]+)))|(date:\d{4}\/\d{1,2}\/\d{1,2})|(number:[\w\d\.\-_]+)|(section:[\d]+))+/gi;
				var match = pattern.exec(str);
				while (match != null) {
					var matchItem = match[1];
					if (matchItem) {
						if (matchItem.indexOf("name:") == 0) {
							obj.docName = matchItem.substring(5).replace(/^'(.*)'$/gi, "$1").replace(/^"(.*)"$/gi, "$1");
							matchFound = true;
						} else if (matchItem.indexOf("date:") == 0) {
							var docDate = matchItem.substring(5);
							obj.docDate = docDate ? moment(docDate).toDate() : "";
							matchFound = true;
						} else if (matchItem.indexOf("number:") == 0) {
							obj.docNumber = matchItem.substring(7);
							matchFound = true;
						} else if (matchItem.indexOf("section:") == 0) {
							obj.section = {id: matchItem.substring(8)};
							matchFound = true;
						}
					}
					match = pattern.exec(str);
				}
				if (!matchFound) {
					obj.docDate = null;
					obj.docName = str;
				}
				return obj;
			}
		}
	})
;
