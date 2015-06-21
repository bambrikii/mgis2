/**
 * Created by asd on 21/06/15.
 */
angular.module("mgis.admin.users", [  "ui.bootstrap", "ui.multiselect", //
    "mgis.admin.users.service", "mgis.admin.privileges.service" //
]) //
    .config(function ($stateProvider, $urlRouterProvider) {
    }) //
    .controller("AdminUsersController", function ($scope, AdminUsersService, AdminPrivilegesService, $modal) {
        $scope.first = 0;
        $scope.max = 15;
        function updateGrid() {
            AdminUsersService.get("", $scope.first, $scope.max).then(function (data) {
                $scope.users = data.list;
//            $scope.first = data.first;
//            $scope.max = data.max;
            });
        }

        function openModal() {
            AdminPrivilegesService.get("").then(function (availablePrivileges) {
                $scope.availablePrivileges = availablePrivileges.list;
                $modal.open({
                    animate: true,
                    scope: $scope,
                    templateUrl: "app2/admin/users/admin-user-form.htm",
                    controller: function ($scope, $modalInstance) {
                        $scope.ok = function () {
                            AdminUsersService.save($scope.editedUser).then(function () {
                                $modalInstance.close();
                                updateGrid();
                            })
                        }
                        $scope.cancel = function () {
                            $modalInstance.dismiss("cancel");
                        }
                    }
                });
            });
        }

        updateGrid();

        $scope.add = function () {
            $scope.editedUser = {
                id: 0,
                username: "",
                password: "",
                active: true,
                roles: []
            }
            openModal();
        }
        $scope.edit = function (userId) {
            AdminUsersService.get(userId).then(function (user) {
                $scope.editedUser = user;
                openModal();
            });
        }
        $scope.remove = function (userId) {
            var modalInstance = $modal.open({
                templateUrl: 'app2/common/confirm-deletion.htm',
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        AdminUsersService.remove(userId).then(function () {
                            $modalInstance.close();
                            updateGrid();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    }
                }
            });
        }
    }) //
;
