angular.module("mgis.isogd.classifiers.documents.structure", ["ui.router", "ui.bootstrap", //
        "mgis.isogd.classifiers.documents.structure.services"
    ] //
) //
    .controller("ISOGDClassifiersDocumentsStructureController", function ($rootScope, $scope, ISOGClassifiersDocumentsClassesService, ISOGClassifiersDocumentsObjectsService, ISOGClassifiersDocumentsSubObjectsService, $modal) {
        var CLASS_EDIT_TEMPLATE_URL = "app2/isogd/classifiers/documents/structure/isogd-docs-class.htm";
        var OBJECT_EDIT_TEMPLATE_URL = "app2/isogd/classifiers/documents/structure/isogd-docs-object.htm";
        var SUB_OBJECT_EDIT_TEMPLATE_URL = "app2/isogd/classifiers/documents/structure/isogd-docs-subobject.htm";

        $scope.docClasses = [];
        function updateTree() {
            ISOGClassifiersDocumentsClassesService.get("", 0, 0).then(function (classes) {
                $scope.docClasses = classes.list;
            });
        }

        updateTree();

        // Class
        $scope.addClass = function () {
            var modalScope = $rootScope.$new();
            modalScope.documentClass = {
                id: 0,
                code: ""
            }
            var modalInstance = $modal.open({
                scope: modalScope,
                templateUrl: CLASS_EDIT_TEMPLATE_URL,
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        ISOGClassifiersDocumentsClassesService.save($scope.documentClass).then(function () {
                            $modalInstance.close();
                            updateTree();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss("cancel");
                    }
                }
            });
        }
        $scope.editClass = function (id) {
            ISOGClassifiersDocumentsClassesService.get(id).then(function (data) {
                var modalScope = $rootScope.$new();
                modalScope.documentClass = data;
                var modalInstance = $modal.open({
                    scope: modalScope,
                    templateUrl: CLASS_EDIT_TEMPLATE_URL,
                    controller: function ($scope, $modalInstance) {
                        $scope.ok = function () {
                            ISOGClassifiersDocumentsClassesService.save(modalScope.documentClass).then(function () {
                                $modalInstance.close();
                                updateTree();
                            });
                        }
                        $scope.cancel = function () {
                            $modalInstance.dismiss("cancel");
                        }
                    }
                });
            });
        }
        $scope.removeClass = function (id) {
            var modalInstance = $modal.open({
                templateUrl: "app2/common/confirm-deletion.htm",
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        $modalInstance.close();
                        ISOGClassifiersDocumentsClassesService.remove(id).then(function (data) {
                            updateTree();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    }
                }
            });
        }

        // Object
        $scope.addObject = function (classId) {
            var modalScope = $rootScope.$new();
            modalScope.documentObject = {
                id: 0,
                code: "",
                documentClass: {
                    id: classId
                }
            }
            var modalInstance = $modal.open({
                scope: modalScope,
                templateUrl: OBJECT_EDIT_TEMPLATE_URL,
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        ISOGClassifiersDocumentsObjectsService.save(modalScope.documentObject).then(function () {
                            $modalInstance.close();
                            updateTree();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss("cancel");
                    }
                }
            });
        }
        $scope.editObject = function (objectId) {
            ISOGClassifiersDocumentsObjectsService.get(objectId).then(function (data) {
                var modalScope = $rootScope.$new();
                modalScope.documentObject = data;
                var modalInstance = $modal.open({
                    scope: modalScope,
                    templateUrl: OBJECT_EDIT_TEMPLATE_URL,
                    controller: function ($scope, $modalInstance) {
                        $scope.ok = function () {
                            ISOGClassifiersDocumentsObjectsService.save(modalScope.documentObject).then(function () {
                                $modalInstance.close();
                                updateTree();
                            });
                        }
                        $scope.cancel = function () {
                            $modalInstance.dismiss("cancel");
                        }
                    }
                });
            });
        }
        $scope.removeObject = function (objectId) {
            var modalInstance = $modal.open({
                templateUrl: 'app2/common/confirm-deletion.htm',
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        $modalInstance.close();
                        ISOGClassifiersDocumentsObjectsService.remove(objectId).then(function (data) {
                            updateTree();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    }
                }
            });
        }

        // SubObject
        $scope.addSubObject = function (objectId) {
            var modalScope = $rootScope.$new();
            modalScope.documentSubObject = {
                id: 0,
                code: "",
                documentObject: {
                    id: objectId
                }
            }
            var modalInstance = $modal.open({
                scope: modalScope,
                templateUrl: SUB_OBJECT_EDIT_TEMPLATE_URL,
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        ISOGClassifiersDocumentsSubObjectsService.save($scope.documentSubObject).then(function () {
                            updateTree();
                            $modalInstance.close();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss("cancel");
                    }
                }
            });
        }
        $scope.editSubObject = function (subObjectId) {
            ISOGClassifiersDocumentsSubObjectsService.get(subObjectId).then(function (data) {
                var modalScope = $rootScope.$new();
                modalScope.documentSubObject = data;
                var modalInstance = $modal.open({
                    scope: modalScope,
                    templateUrl: SUB_OBJECT_EDIT_TEMPLATE_URL,
                    controller: function ($scope, $modalInstance) {
                        $scope.ok = function () {
                            ISOGClassifiersDocumentsSubObjectsService.save($scope.documentSubObject).then(function () {
                                updateTree();
                                $modalInstance.close();
                            });
                        }
                        $scope.cancel = function () {
                            $modalInstance.dismiss("cancel");
                        }
                    }
                })
            })
        }
        $scope.removeSubObject = function (subObjectId) {
            var modalInstance = $modal.open({
                templateUrl: 'app2/common/confirm-deletion.htm',
                controller: function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        $modalInstance.close();
                        ISOGClassifiersDocumentsSubObjectsService.remove(subObjectId).then(function (data) {
                            updateTree();
                        });
                    }
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    }
                }
            });
        }

    })
//
    .controller("ISOGDDocumentsClassifiersDocumentsSubObjectsController", function ($scope, ISOGClassifiersDocumentsSubObjectsService) {
        ISOGClassifiersDocumentsSubObjectsService.get("", 0, 0, $scope.docObject.id).then(function (data) {
            $scope.documentSubObjects = data.list;
        });
    })
;
