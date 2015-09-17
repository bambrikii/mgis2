angular.module("mgis.settings.gis.server", ["ui.router",
	"mgis.commons.crud",
	"mgis.settings.gis.server.service"
])
	.config(function ($stateProvider) {
		$stateProvider
			.state("settings.gis.servers", {
				url: "/servers",
				templateUrl: "app2/settings/gis/gis-server-panel.htm"
			});
	})
	.factory("MGISSettingsGisServerModule", function ($rootScope, MGISCommonsModalForm, MGISSettingsGisServerService) {
		function edit0() {
		}

		function add() {

		}

		function edit() {
			MGISSettingsGisServerService.get(id).then(function () {

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
	.controller("MGISSettingsGisServerController", function ($scope, MGISSettingsGisServerService) {

		$scope.createHandler = function (scope, prepareHandler) {
			prepareHandler({id: 0});
		};
		$scope.readHandler = function (id, scope, completeHandler) {
			MGISSettingsGisServerService.get(id).then(function (item) {
				completeHandler(item);
			});
		};
		$scope.updateHandler = function (item, completeHandler) {
			MGISSettingsGisServerService.save(item).then(function () {
				completeHandler();
			});
		}
		$scope.deleteHandler = function (id, completeHandler) {
			MGISSettingsGisServerService.remove(id).then(function () {
				completeHandler();
			});
		};
		$scope.listHandler = function (filter, first, max, completeHandler) {
			MGISSettingsGisServerService.get("", first, max).then(function (data) {
				$scope.list = data.list;
				completeHandler();
			});
		};
	})
;
