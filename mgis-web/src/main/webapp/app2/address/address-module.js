angular.module("mgis.address", ["ui.bootstrap", "ui.select",
	"mgis.commons",
	"mgis.address.service",
	"mgis.nc.services"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("addresses", {
				url: "/addresses",
				templateUrl: "app2/address/address-list.htm"
			})
	})
	.factory("AddressModule", function ($rootScope, MGISCommonsModalForm, AddressService, AddressElementSearchService,
										NcOKATOService, NcOKTMOService) {

		function editItem0(modalScope, updateFunction) {
			var min = 0;
			var max = 15;

			function refreshAvailableProperty(property, filter) {
				AddressElementSearchService.get("", min, max, filter).then(function (data) {
					modalScope["available" + property + "s"] = data.list;
				});
			}

			function retrieveSubject() {
				if (modalScope.address && modalScope.address.subject && modalScope.address.subject.id) {
					return modalScope.address && modalScope.address.subject && modalScope.address.subject.code;
				}
				return null;
			}

			function retrieveRegion() {
				if (modalScope.address && modalScope.address.region && modalScope.address.region.id) {
					return modalScope.address && modalScope.address.region && modalScope.address.region.code;
				}
				return null;
			}

			function retrieveLocality() {
				if (modalScope.address && modalScope.address.locality && modalScope.address.locality.id) {
					return modalScope.address && modalScope.address.locality && modalScope.address.locality.code;
				}
				return null;
			}

			function retrieveStreet() {
				if (modalScope.address && modalScope.address.street && modalScope.address.street.id) {
					return modalScope.address && modalScope.address.street && modalScope.address.street.code;
				}
				return null;
			}

			modalScope.refreshAvailableOKATOs = function (name) {
				NcOKATOService.get("", min, max, "", name).then(function (data) {
					modalScope.availableOKATOs = data.list;
				});
			}
			modalScope.refreshAvailableOKTMOs = function (name) {
				NcOKTMOService.get("", min, max, "", name).then(function (data) {
					modalScope.availableOKTMOs = data.list;
				});
			}
			modalScope.refreshAvailableSubjects = function (name) {
				refreshAvailableProperty("Subject", {type: "subject", subject: name});
			}
			modalScope.refreshAvailableRegions = function (name) {
				var subj = retrieveSubject();
				refreshAvailableProperty("Region", {type: "region", subject: subj, region: name});
			}
			modalScope.refreshAvailableLocalities = function (name) {
				var subj = retrieveSubject();
				var regio = retrieveRegion();
				refreshAvailableProperty("Localitie", {
					type: "locality",
					subject: subj,
					region: regio,
					locality: name
				});
			}
			modalScope.refreshAvailableStreets = function (name) {
				var subjec = retrieveSubject();
				var regio = retrieveRegion();
				var localit = retrieveLocality();
				refreshAvailableProperty("Street", {
					type: "street",
					subject: subjec,
					region: regio,
					locality: localit,
					street: name
				});
			}
			modalScope.refreshAvailableHomes = function (name) {
				return new Array();
				//var stree = retrieveStreet();
				//refreshAvailableProperty("Home", {
				//	type: "home",
				//	street: stree,
				//	home: name
				//});
				//return modalScope.availableHomes;
			}
			modalScope.refreshAvailableHousings = function (name) {
				return new Array();
				//var stree = retrieveStreet();
				//var hom = modalScope.address.home;
				//refreshAvailableProperty("Housing", {
				//	type: "housing",
				//	street: stree,
				//	home: hom,
				//	housing: name
				//});
				//return modalScope.availableHousings;
			}
			modalScope.refreshAvailableBuildings = function (name) {
				return new Array();
				//var stree = retrieveStreet();
				//var hom = modalScope.address.home;
				//var housin = modalScope.address.housing;
				//modalScope.availableBuildings = refreshAvailableProperty("Building", {
				//	type: "building",
				//	street: stree,
				//	home: hom,
				//	housing: housin,
				//	building: name
				//});
				//return modalScope.availableBuildings;
			}
			modalScope.refreshAvailableApartments = function (name) {
				return new Array();
				//var stree = retrieveStreet();
				//var hom = modalScope.address.home;
				//var housin = modalScope.address.housing;
				//var buildin = modalScope.address.building;
				//refreshAvailableProperty("Apartment", {
				//	type: "apartment",
				//	street: stree,
				//	home: hom,
				//	housing: housin,
				//	building: buildin,
				//	apartment: name
				//});
				//return modalScope.availableApartments;
			}

			function emptyRegion() {
				modalScope.address.region = {};
				emptyLocality();
			}

			function emptyLocality() {
				modalScope.address.locality = {};
				modalScope.address.okato = {};
				modalScope.address.oktmo = {};
				modalScope.address.postalCode = "";
				emptyStreet();
			}

			function emptyStreet() {
				modalScope.address.street = {}
				emptyHome();
			}

			function emptyHome() {
				modalScope.address.home = "";
				emptyHousing();
			}

			function emptyHousing() {
				modalScope.address.housing = "";
				emptyBuilding();
			}

			function emptyBuilding() {
				modalScope.address.building = "";
				emptyApartment();
			}

			function emptyApartment() {
				modalScope.address.apartment = "";
			}

			modalScope.subjectSelected = function () {
				modalScope.refreshAvailableRegions("");
				emptyRegion();
			}
			modalScope.regionSelected = function () {
				modalScope.refreshAvailableLocalities("");
				emptyLocality();
			}
			modalScope.localitySelected = function () {
				modalScope.refreshAvailableStreets("");
				modalScope.refreshAvailableOKATOs(modalScope.address.locality.name);
				modalScope.refreshAvailableOKTMOs(modalScope.address.locality.name);
				emptyStreet();
			}
			modalScope.streetSelected = function () {
				if (modalScope.address && modalScope.address.street && modalScope.address.street.index) {
					modalScope.address.postalCode = modalScope.address.street.index;
				}
				modalScope.refreshAvailableHomes("");
				emptyHome();
			}
			modalScope.homeSelected = function () {
				modalScope.refreshAvailableHousings("");
				emptyHousing();
			}
			modalScope.housingSelected = function () {
				modalScope.refreshAvailableBuildings("");
				emptyBuilding();
			}
			modalScope.buildingSelected = function () {
				modalScope.refreshAvailableApartments("");
				emptyApartment();
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
			MGISCommonsModalForm.confirmRemoval(function (scope, modalInstance) {
				AddressService.remove(id).then(function () {
					if (updateFunction) {
						updateFunction();
					}
					modalInstance.close();
				});
			});
		}

		return {
			add: addItem,
			edit: editItem,
			remove: removeItem
		}
	})
	.directive("addressAsString", function () {
		return {
			restrict: "E",
			scope: {
				address: "="
			},
			templateUrl: "app2/address/address-as-string.htm"
		}
	})
	.controller("AddressController", function ($scope, AddressModule, AddressService) {

		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;

		function updateGrid() {
			AddressService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage).then(function (data) {
				$scope.addressPager = data;
			});
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			AddressModule.add(updateGrid);
		}

		$scope.editItem = function (id) {
			AddressModule.edit(id, updateGrid);
		}

		$scope.deleteItem = function (id) {
			AddressModule.remove(id, updateGrid);
		}

		updateGrid();
	})
;
