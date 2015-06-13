angular.module("mgis.isogd.documents", ["ui.router", "ui.bootstrap"]) //
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider //
            .state("isogd.documents", {
                url: "/sections/:sectionId/volumes/:volumeId/books/:bookId/documents/",
                templateUrl: "app2/isogd/document/isogd-documents-list.htm",
                controller: function ($scope, $state, $stateParams, ISOGDDocumentsService, $modal) {
                    console.log("documents...");
                    $scope.stateParams = $stateParams;

                    $scope.documents = function (bookId) {
                        return ISOGDDocumentsService.list(bookId);
                    }

                    // Document
                    $scope.addDocument = function () {
                        console.log("add Document");
                    }
                    $scope.editDocument = function () {
                        console.log("edit Document");
                    }
                    $scope.removeDocument = function () {
                        var modalInstance = $modal.open({
                            templateUrl: 'app2/isogd/isogd-confirm-deletion.htm',
                            controller: function ($scope, $modalInstance) {
                                $scope.ok = function () {
                                    $modalInstance.close("");
                                    console.log("remove Document");
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
    .controller("ISOGDDocumentsCtrl", function ($scope) {

    }) //
;

