describe('MGISCommons', function () {
    var scope;
    var controller;
    beforeEach(module("mgis.commons"));
    beforeEach(inject(function ($rootScope, $controller, $location) {
        scope = $rootScope.$new();
        controller = $controller("MGISDateTimeController", {
            $scope: scope
        });
    }));

    describe('MGISDateTimeController', function () {
        it("open", function () {
            //scope.open();
            //expect(scope).to.have.property(opened);
        });
    });
});
