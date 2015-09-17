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

		$scope.createHandler = function (scope, onPrepare) {
			onPrepare({id: 0});
		};
		$scope.readHandler = function (id, scope, onPrepare) {
			MGISSettingsGisServerService.get(id).then(function (item) {
				onPrepare(item);
			});
		};
		$scope.updateHandler = function (item, onComplete) {
			MGISSettingsGisServerService.save(item).then(function () {
				onComplete();
			});
		}
		$scope.deleteHandler = function (id, onComplete) {
			console.log("deleteHandler");
			MGISSettingsGisServerService.remove(id).then(function () {
				console.log(".remove");
				onComplete();
			});
		};
		$scope.listHandler = function (filter, first, max, onComplete) {
			MGISSettingsGisServerService.get("", first, max, filter.code).then(function (data) {
				onComplete(data);
			});
		};
	})
;
