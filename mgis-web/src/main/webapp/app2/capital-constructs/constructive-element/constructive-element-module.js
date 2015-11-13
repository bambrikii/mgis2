angular.module("mgis.capital-constructs.constructive-elements", ["ui.bootstrap",
	"mgis.commons",
	"mgis.capital-constructs.constructive-element-type"
])
	.factory("CapitalConstructsConstructiveElementCRUDService", function ($rootScope,
																		  MGISCommonsModalForm) {
		function editItem(element, completeHandler) {
			var modalScope = $rootScope.$new();
			modalScope.item = {};
			angular.copy(element, modalScope.item);
			MGISCommonsModalForm.edit("app2/capital-constructs/constructive-element/constructive-element-form.htm", modalScope, function (scope, modalInstance) {
				angular.copy(scope.item, element);
				if (completeHandler) {
					completeHandler(element);
				}
				modalInstance.close();
			});
		}

		return {
			add: function (capitalConstruct) {
				editItem({id: 0}, function (constructiveElement) {
					if (!capitalConstruct.constructiveElements) {
						capitalConstruct.constructiveElements = new Array();
					}
					capitalConstruct.constructiveElements.push(constructiveElement);
				});
			},
			edit: function (constructiveElement) {
				editItem(constructiveElement);
			},
			remove: function (capitalConstruct, constructiveElement) {
				MGISCommonsModalForm.confirmRemoval(function () {
					for (var i in capitalConstruct.constructiveElements) {
						var elem = capitalConstruct.constructiveElements;
						if (elem == constructiveElement) {
							capitalConstruct.constructiveElements.splice(i, 1);
						}
					}
				});
			}
		}
	})
;
