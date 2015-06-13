angular.module("mgis.isogd.books", ["ui.router", "ui.bootstrap"]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider//
            .state("isogd.books", {
                url: "/sections/:sectionId/volumes/:volumeId/books/",
                templateUrl: "app2/isogd/book/isogd-books-list.htm",
                controller: function ($scope, $state, $stateParams, ISOGDBooksService, $modal) {
                    console.log("books...");
                    $scope.stateParams = $stateParams;

                    $scope.books = function (volumeId) {
                        return ISOGDBooksService.list(volumeId);
                    }
                    // Book
                    $scope.addBook = function (sectionId) {
                        console.log("add Book");
                    }
                    $scope.editBook = function (bookId) {
                        console.log("edit Book");
                    }
                    $scope.removeBook = function (bookId) {
                        var modalInstance = $modal.open({
                            templateUrl: 'app2/isogd/isogd-confirm-deletion.htm',
                            controller: function ($scope, $modalInstance) {
                                $scope.ok = function () {
                                    $modalInstance.close("");
                                    console.log("remove Book");
                                    // TODO:
                                }
                                $scope.cancel = function () {
                                    $modalInstance.dismiss('cancel');
                                }
                            }
                        });
                    }
                }
            })

    })
    .controller("ISOGDBooksCtrl", function ($scope) {

    }) //
;

