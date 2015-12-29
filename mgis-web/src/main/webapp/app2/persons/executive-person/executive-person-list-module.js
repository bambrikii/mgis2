/**
 * Created by donchenko-y on 12/24/15.
 */
angular.module("mgis.persons.executive.person.list", ["ui.router", "ui.bootstrap", //
	"mgis.persons.executive.person.list.service",
	"mgis.commons"
])
	.factory("ExecutivePersonModule", function (ExecutivePersonService, MGISCommonsModalForm, $rootScope) {

		function editItem0(modalScope, updateFunction) {

			MGISCommonsModalForm.edit("app2/persons/executive-person/executive-person-form.htm", modalScope, function (scope, $modalInstance) {
				ExecutivePersonService.save(scope.item).then(function (data) {
					$modalInstance.close();
					if (updateFunction) {
						updateFunction();
					}
				});
			}, {windowClass: "mgis-person-modal-form"});
		}

		function addItem(name, updateFunction) {
			var modalScope = $rootScope.$new();
			modalScope.item = {id: 0, name: name}
			editItem0(modalScope, updateFunction);
		}

		function editItem(id, updateFunction) {
			var modalScope = $rootScope.$new();
			ExecutivePersonService.get(id).then(function (item) {
				modalScope.item = item;
				editItem0(modalScope, updateFunction);
			});
		}

		function removeItem(id, updateFunction) {
			MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
				ExecutivePersonService.remove(id).then(function () {
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
	.directive("executivePersonListSelector", function () {
		return {
			restrict: "E",
			scope: {
				person: "=",
				selectClicked: "&"
			},
			templateUrl: "app2/persons/executive-person/executive-person-list-selector.htm",
			controller: function ($scope, $rootScope, ExecutivePersonService, MGISCommonsModalForm, ExecutivePersonModule, CommonsPagerManager) {
				$scope.list = new Array();
				$scope.first = 0;
				$scope.max = 0;
				$scope.name = $scope.person ? $scope.person.name : "";
				$scope.currentPage = 1;
				$scope.itemsPerPage = CommonsPagerManager.pageSize();
				$scope.find = function () {
					updateGrid();
				}
				function updateGrid() {
					ExecutivePersonService.get("", CommonsPagerManager.offset($scope.currentPage), $scope.itemsPerPage, $scope.name).then(function (data) {
						$scope.list = data.list;
						$scope.executivePersonPager = data;
					});
				}

				$scope.edit = function (id) {
					ExecutivePersonModule.edit(id, updateGrid);
				}
				$scope.add = function (name) {
					ExecutivePersonModule.add(name, updateGrid);
				}
				$scope.remove = function (id) {
					ExecutivePersonModule.remove(id, updateGrid);
				}
				$scope.pageChanged = function () {
					updateGrid();
				}
				$scope.select = function (id) {
					ExecutivePersonService.get(id).then(function (item) {
						if ($scope.selectClicked) {
							$scope.selectClicked({
								person: item
							});
						}
					});
				}
				$scope.find();

			}
		}
	})
;
