angular.module("mgis.address", ["ui.bootstrap", "ui.select",
	"mgis.commons",
	"mgis.address.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("addresses", {
				url: "/addresses",
				templateUrl: "app2/address/address-list.htm"
			})
	})
	.factory("AddressModule", function ($rootScope, MGISCommonsModalForm, AddressService, AddressElementSearchService) {

		function editItem0(modalScope, updateFunction) {
			var min = 0;
			var max = 15;

			function refreshAvailableProperty(property, filter) {
				AddressElementSearchService.get("", min, max, filter).then(function (data) {
					modalScope["available" + property + "s"] = data.list;
				});
			}

			modalScope.refreshAvailableOKATOs = function (name) {
				refreshAvailableProperty("OKATO", {type: "okato", okato: name});
			}
			modalScope.refreshAvailableKLADRs = function (name) {
				refreshAvailableProperty("KLADR", {type: "kladr", kladr: name});
			}
			modalScope.refreshAvailableOKTMOs = function (name) {
				refreshAvailableProperty("OKTMO", {type: "oktmo", oktmo: name});
			}
			modalScope.refreshAvailableSubjects = function (name) {
				refreshAvailableProperty("Subject", {type: "subject", subject: name});
			}
			modalScope.refreshAvailableRegions = function (name) {
				refreshAvailableProperty("Region", {type: "region", region: name});
			}
			modalScope.refreshAvailableCities = function (name) {
				refreshAvailableProperty("Citie", {type: "city", city: name});
			}
			modalScope.refreshAvailableUrbanDistricts = function (name) {
				refreshAvailableProperty("UrbanDistrict", {type: "district", district: name});
			}
			modalScope.refreshAvailableSovietVillages = function (name) {
				refreshAvailableProperty("SovietVillage", {type: "soviet-village", sovietVillage: name});
			}
			modalScope.refreshAvailableLocalities = function (name) {
				refreshAvailableProperty("Localitie", {type: "locality", locality: name});
			}
			modalScope.refreshAvailableStreets = function (name) {
				refreshAvailableProperty("Street", {type: "street", street: name});
			}
			modalScope.refreshAvailableHomes = function (name) {
				refreshAvailableProperty("Home", {type: "street", home: name});
			}
			modalScope.refreshAvailableHousings = function (name) {
				refreshAvailableProperty("Housing", {type: "housing", housing: name});
			}
			modalScope.refreshAvailableBuildings = function (name) {
				refreshAvailableProperty("Building", {type: "building", building: name});
			}
			modalScope.refreshAvailableApartments = function (name) {
				refreshAvailableProperty("Apartment", {type: "apartment", apartment: name});
			}

			MGISCommonsModalForm.edit("app2/address/address-form.htm", modalScope, function (scope, $modalInstance) {
				AddressService.save(scope.address).then(function () {
					$modalInstance.close();
					if (updateFunction) {
						updateFunction();
					}
				});
			}, {windowClass: "mgis-address-modal-form"});
		}

		function addItem(updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.address = {id: 0};
			editItem0(modalScope, updateFunction);
		}

		function editItem(id, updateFunction) {
			AddressService.get(id).then(function (data) {
				var modalScope = $rootScope.$new();
				modalScope.address = data;
				editItem0(modalScope, updateFunction);
			});
		}

		function removeItem(id, updateFunction) {
			MGISCommonsModalForm.confirmRemoval(function () {
				AddressService.remove(id).then(function () {
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
	.controller("AddressController", function ($scope, AddressModule, AddressService) {

		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;

		function updateGrid() {
			AddressService.list("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage).then(function (data) {

			});
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			AddressModule.add(updateGrid);
		}

		$scope.editItem = function (id, updateFunction) {
			AddressModule.edit(id, updateFunction);
		}

		$scope.removeItem = function (id, updateFunction) {
			AddressModule.remove(id, updateFunction);
		}
	})
;
