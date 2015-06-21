/**
 * Created by asd on 21/06/15.
 */
angular.module("mgis.admin.privileges", [ //
    "mgis.admin.privileges.service" //
]) //
    .controller("AdminPrivilegesController", function ($scope, AdminPrivilegesService) {
        $scope.first = 0;
        $scope.max = 15;
        function updateGrid() {
            AdminPrivilegesService.get("", $scope.first, $scope.max).then(function (data) {
                $scope.privileges = data.list;
//            $scope.first = data.first;
//            $scope.max = data.max;
            });
        }

        updateGrid();
    }) //
;
