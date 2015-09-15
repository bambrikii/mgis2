angular.module("mgis.persons.person.legal", ["ui.router", "ui.bootstrap", //
	"mgis.persons.person.legal.service",
	"mgis.commons",
	"mgis.nc.services"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("persons/legal-persons", {
				url: "/persons/legal-persons",
				templateUrl: "app2/persons/legal-person/legal-person-list.htm"
			})
	})
	.factory("LegalPersonModule", function ($rootScope, LegalPersonService, MGISCommonsModalForm, NcOKVEDService, NcOKOGUService, NcOKOPFService, NcOKATOService, NcOKFSService) {

		function editItem0(modalScope, updateFunction) {
			NcOKFSService.get("", 0, 0).then(function (okfss) {
				modalScope.availableOKFSs = okfss.list;

				modalScope.refreshAvailableOKOGUs = function (name) {
					NcOKOGUService.get("", 0, 15, name).then(function (okogus) {
						modalScope.availableOKOGUs = okogus.list;
					});
				}

				modalScope.refreshAvailableActualAddressTerritoryOkatoCodes = function (name) {
					NcOKATOService.get("", 0, 15, null, name).then(function (okatos) {
						modalScope.availableActualAddressTerritoryOkatoCodes = okatos.list;
					});
				}

				modalScope.refreshAvailableLegalAddressTerritoryOkatoCodes = function (name) {
					NcOKATOService.get("", 0, 15, null, name).then(function (okatos) {
						modalScope.availableLegalAddressTerritoryOkatoCodes = okatos.list;
					});
				}

				modalScope.refreshAvailableOKOPFs = function (name) {
					NcOKOPFService.get("", 0, 15, name).then(function (okopfs) {
						modalScope.availableOKOPFs = okopfs.list;
					});
				}


				modalScope.refreshAvailableActivityTypes = function (name) {
					NcOKVEDService.get("", 0, 15, name).then(function (okveds) {
						modalScope.availableActivityTypes = okveds.list;
					});
				}


				MGISCommonsModalForm.edit("app2/persons/legal-person/legal-person-form.htm", modalScope, function (scope, $modalInstance) {
					LegalPersonService.save(scope.item).then(function (data) {
						$modalInstance.close();
						if (updateFunction) {
							updateFunction();
						}
					});
				}, {windowClass: "mgis-person-modal-form"});
			});
		}

		function addItem(name, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0, name: name}
			editItem0(modalScope, updateFunction);
		}

		function editItem(id, updateFunction) {
			LegalPersonService.get(id).then(function (item) {
				var modalScope = $rootScope.$new();
				modalScope.item = item;
				editItem0(modalScope, updateFunction);
			});
		}

		function removeItem(id, updateFunction) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				LegalPersonService.remove(id).then(function () {
					$modalInstance.close();
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
	.controller("LegalPersonSelectorController", function ($scope, $rootScope, LegalPersonModule, LegalPersonService) {
		$scope.list = new Array();
		$scope.first = 0;
		$scope.max = 15;
		$scope.name = $scope.person ? $scope.person.name : "";
		$scope.find = function () {
			updateGrid();
		}
		function updateGrid() {
			LegalPersonService.get("", $scope.first, $scope.max, $scope.name).then(function (data) {
				$scope.list = data.list;
			});
		}

		$scope.edit = function (id) {
			LegalPersonModule.edit(id, updateGrid);
		}
		$scope.add = function (name) {
			LegalPersonModule.add($scope.name, updateGrid);
		}
		$scope.remove = function (id) {
			LegalPersonModule.remove(id, updateGrid);
		}
		$scope.select = function (id) {
			LegalPersonService.get(id).then(function (item) {
				if ($scope.selectClicked) {
					$scope.selectClicked({id: id, name: item.name});
				}
			});
		}
		$scope.find();
	})
	.directive("legalPersonSelector", function () {
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/persons/legal-person/legal-person-selector.htm"
		}
	})
	.controller("LegalPersonsController", function ($scope, LegalPersonModule, LegalPersonService) {
		$scope.currentPage = 1;
		$scope.itemsPerPage = 15;
		function updateGrid() {
			LegalPersonService.get("", ($scope.currentPage - 1) * $scope.itemsPerPage, $scope.itemsPerPage).then(function (data) {
				$scope.legalPersonsPager = data;
			})
		}

		$scope.pageChanged = function () {
			updateGrid();
		}

		$scope.addItem = function () {
			LegalPersonModule.add("", updateGrid);
		}

		$scope.editItem = function (id) {
			LegalPersonModule.edit(id, updateGrid);
		}

		$scope.removeItem = function (id) {
			LegalPersonModule.remove(id, updateGrid);
		}

		updateGrid();

	})
;
