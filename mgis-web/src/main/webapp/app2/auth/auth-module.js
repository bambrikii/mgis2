angular.module("mgis.auth.login", ["ui.bootstrap",
	"mgis.auth.service"
])
	.controller("MGISLoginController", function ($scope, MGISAuthenticationService) {
		$scope.login = function (username, password) {
			MGISAuthenticationService.login(username, password).then(function () {
				console.log("ok: " + username + ", " + password + ", " + JSON.stringify(arguments));

			}).catch(function (msg) {
				console.log("error: " + msg);
			})
		}
	})
;
