angular.module("mgis.commons.crud", [
	"mgis.commons",
	"mgis.error"

])
	.directive("mgisCommonsCrudPanel", function () {
		return {
			restrict: "E",
			scope: {
				searchFormTemplateUrl: "=",
				listItemTemplateUrl: "=",
				editFormTemplateUrl: "=",
				createHandler: "&",
				readHandler: "&",
				updateHandler: "&",
				deleteHandler: "&",
				listHandler: "&"
			},
			templateUrl: "app2/commons/crud/common-crud-panel.htm",
			controller: function ($scope, $rootScope, MGISCommonsModalForm) {
				$scope.currentPage = 1;
				$scope.itemsPerPage = 15;
				$scope.filter = {}

				function editItem(modalScope) {
					MGISCommonsModalForm.edit($scope.editFormTemplateUrl, modalScope, function (scope, $modalInstance) {
						$scope.updateHandler({
							item: scope.item,
							onComplete: function () {
								$modalInstance.close();
								$scope.list();
							}
						})
					}, {windowClass: "mgis-crud-modal-form"});
				}

				$scope.addItem = function () {
					var modalScope = $rootScope.$new();
					$scope.createHandler({
						scope: modalScope,
						onPrepare: function (item) {
							modalScope.item = item;
							editItem(modalScope);
						}
					});
				}

				$scope.editItem = function (id) {
					var modalScope = $rootScope.$new();
					$scope.readHandler({
						id: id,
						scope: modalScope,
						onPrepare: function (item) {
							modalScope.item = item;
							editItem(modalScope);
						}
					});
				}

				$scope.removeItem = function (id) {
					MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
						$scope.deleteHandler({
							id: id,
							onComplete: function () {
								$modalInstance.close();
								$scope.list();
							}
						});
					});
				}

				$scope.list = function () {
					console.log($scope.filter);
					$scope.listHandler({
						filter: $scope.filter,
						first: ($scope.currentPage - 1) * $scope.itemsPerPage,
						max: $scope.itemsPerPage,
						onComplete: function (pager) {
							$scope.crudPager = pager;
						}
					});
				}

				$scope.pageChanged = function () {
					$scope.list();
				}

				$scope.list();

			}
		}
	})
;
