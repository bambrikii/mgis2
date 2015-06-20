angular.module("mgis.admin", ["ui.router"]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider //
            .state("admin", {
                url: "/admin",
                templateUrl: "app2/admin/admin.htm",
                controller: function ($scope) {
                    console.log("admin");
                }
            }) //
            .state("admin.users", {
                url: "/users",
                templateUrl: "app2/admin/users-list.htm",
                controller: function ($scope) {
                    console.log("users");
                }
            }) //
            .state("admin.groups", {
                url: "/groups",
                templateUrl: "app2/admin/groups-list.htm",
                controller: function ($scope) {
                    console.log("groups");
                }
            }) //
            .state("admin.roles", {
                url: "/roles",
                templateUrl: "app2/admin/roles-list.htm",
                controller: function ($scope) {
                    console.log("roles");
                }
            }) //
        ;
    }) //
;
