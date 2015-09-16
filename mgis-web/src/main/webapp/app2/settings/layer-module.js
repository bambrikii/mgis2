angular.module("mgis.settings.layer", [
	"mgis.commons",
	"mgis.settings.layer.service"
])
	.factory("MGISSettingsLayerModule", function ($rootScope, MGISCommonsModalForm, MGISSettingsLayerService) {
		function edit0() {
		}

		function add() {

		}

		function edit() {
			MGISSettingsLayerService.get(id).then(function () {

			});
		}

		function remove() {
			MGISCommonsModalForm.confirmRemoval(function () {

			});
		}

		return {
			add: add,
			edit: edit,
			remove: remove
		}
	})
	.controller("MGISSettingsLayerController", function ($scope, MGISSettingsLayerModule) {
		$scope.list = new Array();
		$scope.first = 0;
		$scope.max = 15;
		function updateGrid() {
			MGISSettingsLayerModule.get("", $scope.first, $scope.max).then(function (data) {
				$scope.list = data.list;
			});
		}

		$scope.add = function (name) {
			MGISSettingsLayerModule.add(name, updateGrid);
		}
		$scope.edit = function (id) {
			MGISSettingsLayerModule.edit(id, updateGrid);
		}
		$scope.remove = function (id) {
			MGISSettingsLayerModule.remove(id, updateGrid);
		}
		$scope.select = function (id) {
			MGISSettingsLayerModule.get(id).then(function (item) {
				if ($scope.selectClicked) {
					$scope.selectClicked({id: id, name: item.name});
				}
			});
		}

		updateGrid();

	})
;
