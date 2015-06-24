/**
 * Created by Alexander Arakelyan on 24.06.15.
 */
angular.module("mgis.isogd.classifiers.documents.representation", ["ui.router", //
    "mgis.commons",
    "mgis.isogd.classifiers.documents.representation.service" //
]) //
    .controller("ISOGDClassifiersDocumentsRepresentationController", function ($rootScope, $scope, ISOGDClassfiersDocumentsRepresentationFormService, ISOGDClassfiersDocumentsRepresentationFormatService, $modal, MGISCommonsModalForm) {
        $scope.representationForms = [];
        function updateTree() {
            ISOGDClassfiersDocumentsRepresentationFormService.get("", 0, 0).then(function (data) {
                $scope.representationForms = data.list;
            });
        }

        function openEditFormModal(modalScope) {
            MGISCommonsModalForm.edit("app2/isogd/classifiers/documents/representation/isogd-docs-representation-form.htm", modalScope, function ($scope, $modalInstance) {
                    ISOGDClassfiersDocumentsRepresentationFormService.save($scope.representationForm).then(function () {
                        updateTree();
                        $modalInstance.close();
                    });
                }
            );
        }

        function openEditFormatModal(modalScope) {
            MGISCommonsModalForm.edit("app2/isogd/classifiers/documents/representation/isogd-docs-representation-format.htm", modalScope, function ($scope, $modalInstance) {
                    ISOGDClassfiersDocumentsRepresentationFormatService.save($scope.representationFormat).then(function () {
                        updateTree();
                        $modalInstance.close();
                    });
                }
            );
        }

        // Representation Form
        $scope.addRepresentationForm = function () {
            var modalScope = $rootScope.$new();
            modalScope.representationForm = {
                id: 0,
                code: "",
                name: ""
            }
            openEditFormModal(modalScope);
        }

        $scope.editRepresentationForm = function (id) {
            ISOGDClassfiersDocumentsRepresentationFormService.get(id).then(function (item) {
                var modalScope = $rootScope.$new();
                modalScope.representationForm = item;
                openEditFormModal(modalScope);
            })
        }

        $scope.removeRepresentationForm = function (id) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                ISOGDClassfiersDocumentsRepresentationFormService.remove(id).then(function () {
                    updateTree();
                    $modalInstance.close();
                });
            });
        }

        // Representation Format
        $scope.addRepresentationFormat = function (representationFormId) {
            var modalScope = $rootScope.$new();
            modalScope.representationFormat = {
                id: 0,
                code: "",
                name: "",
                representationForm: {
                    id: representationFormId
                }
            }
            openEditFormatModal(modalScope);
        }

        $scope.editRepresentationFormat = function (id) {
            ISOGDClassfiersDocumentsRepresentationFormatService.get(id).then(function (item) {
                var modalScope = $rootScope.$new();
                modalScope.representationFormat = item;
                openEditFormatModal(modalScope);
            });
        }

        $scope.removeRepresentationFormat = function (id) {
            MGISCommonsModalForm.confirmRemoval(function ($modalInstance) {
                ISOGDClassfiersDocumentsRepresentationFormatService.remove(id).then(function () {
                    updateTree();
                    $modalInstance.close();
                });
            });
        }


        updateTree();
    }
) //
;
