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
				refreshAvailableProperty("Home", {type: "home", home: name});
				return modalScope.availableHomes;
			}
			modalScope.refreshAvailableHousings = function (name) {
				refreshAvailableProperty("Housing", {type: "housing", housing: name});
				return modalScope.availableHousings;
			}
			modalScope.refreshAvailableBuildings = function (name) {
				refreshAvailableProperty("Building", {type: "building", building: name});
				return modalScope.availableBuildings;
			}
			modalScope.refreshAvailableApartments = function (name) {
				refreshAvailableProperty("Apartment", {type: "apartment", apartment: name});
				return modalScope.availableApartments;
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
