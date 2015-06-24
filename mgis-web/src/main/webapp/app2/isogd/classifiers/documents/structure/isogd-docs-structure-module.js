angular.module("mgis.isogd.classifiers.documents.structure", ["ui.router", "ui.bootstrap", //
        "mgis.commons", //
        "mgis.isogd.classifiers.documents.structure.services"
    ] //
) //
    .controller("ISOGDClassifiersDocumentsStructureController", function ($rootScope, $scope, ISOGClassifiersDocumentsClassesService, ISOGClassifiersDocumentsObjectsService, ISOGClassifiersDocumentsSubObjectsService, $modal, MGISCommonsModalForm) {

        $scope.docClasses = [];

        function updateTree() {
            ISOGClassifiersDocumentsClassesService.get("", 0, 0).then(function (classes) {
                $scope.docClasses = classes.list;
            });
        }

        function openEditClassForm(modalScope) {
            MGISCommonsModalForm.edit("app2/isogd/classifiers/documents/structure/isogd-docs-class.htm", modalScope, function ($scope, $modalInstance) {
                ISOGClassifiersDocumentsClassesService.save(modalScope.documentClass).then(function () {
                    $modalInstance.close();
                    updateTree();
                });
            });
        }

        function openEditObjectForm(modalScope) {
            MGISCommonsModalForm.edit("app2/isogd/classifiers/documents/structure/isogd-docs-object.htm", modalScope, function ($scope, $modalInstance) {
                ISOGClassifiersDocumentsObjectsService.save(modalScope.documentObject).then(function () {
                    $modalInstance.close();
                    updateTree();
                });
            });
        }

        function openEditSubObjectModal(modalScope) {
            MGISCommonsModalForm.edit("app2/isogd/classifiers/documents/structure/isogd-docs-subobject.htm", modalScope, function ($scope, $modalInstance) {
                ISOGClassifiersDocumentsSubObjectsService.save($scope.documentSubObject).then(function () {
                    updateTree();
                    $modalInstance.close();
                });
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
            openEditClassForm(modalScope);
        }

        $scope.editClass = function (id) {
            ISOGClassifiersDocumentsClassesService.get(id).then(function (data) {
                var modalScope = $rootScope.$new();
                modalScope.documentClass = data;
                openEditClassForm(modalScope);
            });
        }

        $scope.removeClass = function (id) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                $modalInstance.close();
                ISOGClassifiersDocumentsClassesService.remove(id).then(function (data) {
                    updateTree();
                });
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
            openEditObjectForm(modalScope);
        }
        $scope.editObject = function (objectId) {
            ISOGClassifiersDocumentsObjectsService.get(objectId).then(function (data) {
                var modalScope = $rootScope.$new();
                modalScope.documentObject = data;
                openEditObjectForm(modalScope);
            });
        }
        $scope.removeObject = function (objectId) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                ISOGClassifiersDocumentsObjectsService.remove(objectId).then(function (data) {
                    updateTree();
                    $modalInstance.close();
                });
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
            openEditSubObjectModal(modalScope);
        }
        $scope.editSubObject = function (subObjectId) {
            ISOGClassifiersDocumentsSubObjectsService.get(subObjectId).then(function (data) {
                var modalScope = $rootScope.$new();
                modalScope.documentSubObject = data;
                openEditSubObjectModal(modalScope);
            });
        }
        $scope.removeSubObject = function (subObjectId) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                ISOGClassifiersDocumentsSubObjectsService.remove(subObjectId).then(function (data) {
                    updateTree();
                    $modalInstance.close();
                });
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
