/**
 * Created by asd on 21/06/15.
 */
angular.module("mgis.admin.users", ["ui.bootstrap", "ui.multiselect", //
    "mgis.commons", //
    "mgis.admin.users.service", "mgis.admin.privileges.service" //
]) //
    .config(function ($stateProvider, $urlRouterProvider) {
    }) //
    .controller("AdminUsersController", function ($scope, AdminUsersService, AdminPrivilegesService, $modal, MGISCommonsModalForm, $rootScope) {
        $scope.first = 0;
        $scope.max = 15;
        function updateGrid() {
            AdminUsersService.get("", $scope.first, $scope.max).then(function (data) {
                $scope.users = data.list;
//            $scope.first = data.first;
//            $scope.max = data.max;
            });
        }

        function openModal(modalScope) {
            AdminPrivilegesService.get("").then(function (availablePrivileges) {
                modalScope.availablePrivileges = availablePrivileges.list;
                MGISCommonsModalForm.edit("app2/admin/users/admin-user-form.htm", modalScope, function ($scope, $modalInstance) {
                    AdminUsersService.save($scope.editedUser).then(function () {
                        $modalInstance.close();
                        updateGrid();
                    });
                });
            });
        }

        updateGrid();

        $scope.add = function () {
            var modalScope = $rootScope.$new();
            modalScope.editedUser = {
                id: 0,
                username: "",
                password: "",
                active: true,
                roles: []
            }
            openModal(modalScope);
        }
        $scope.edit = function (userId) {
            AdminUsersService.get(userId).then(function (user) {
                var modalScope = $rootScope.$new();
                modalScope.editedUser = user;
                openModal(modalScope);
            });
        }
        $scope.remove = function (userId) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                AdminUsersService.remove(userId).then(function () {
                    $modalInstance.close();
                    updateGrid();
                });
            });
        }
    }) //
;
