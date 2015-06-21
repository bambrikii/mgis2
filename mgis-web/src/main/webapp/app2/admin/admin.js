angular.module("mgis.admin", ["ui.router", //
    "mgis.admin.users", "mgis.admin.privileges"
]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider //
            .state("admin", {
                url: "/admin",
                templateUrl: "app2/admin/admin.htm"
            }) //
            .state("admin.users", {
                url: "/users",
                templateUrl: "app2/admin/users/admin-users-list.htm"
            }) //
            .state("admin.groups", {
                url: "/groups",
                templateUrl: "app2/admin/groups/groups-list.htm"
            }) //
            .state("admin.privileges", {
                url: "/privileges",
                templateUrl: "app2/admin/privileges/privileges-list.htm"
            }) //
        ;
    }) //
;
