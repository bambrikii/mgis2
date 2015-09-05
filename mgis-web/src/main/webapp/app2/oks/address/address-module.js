angular.module("mgis.oks.address", ["ui.bootstrap", "ui.select",
	"mgis.commons",
	"mgis.oks.address.service"
])
	.factory("OKSAddressModule", function ($rootScope, MGISCommonsModalForm, OKSAddressService) {

		function editItem0(modalScope, updateFunction) {
			MGISCommonsModalForm.edit("app2/oks/address/address-form.htm", modalScope, function (scope, $modalInstance) {
				OKSAddressService.save(scope.address).then(function () {
					$modalInstance.close();
					if (updateFunction) {
						updateFunction();
					}
				});
			});
		}

		function addItem(updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.address = {id: 0};
			editItem0(modalScope, updateFunction);
		}

		function editItem(id, updateFunction) {
			OKSAddressService.get(id).then(function (data) {
				var modalScope = $rootScope.$new();
				modalScope.address = data;
				editItem0(modalScope, updateFunction);
			});
		}

		function removeItem(id, updateFunction) {
			MGISCommonsModalForm.confirmRemoval(function () {
				OKSAddressService.remove(id).then(function () {
					if (updateFunction) {
						updateFunction();
					}
				});
			});
		}

		return {
			add: addItem,
			edit: editItem,
			remove: removeItem
		}
	})
;
