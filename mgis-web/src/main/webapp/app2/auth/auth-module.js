angular.module("mgis.auth.login", ["ui.bootstrap",
	"mgis.auth.service"
])
	.controller("MGISLoginController", function ($scope, MGISAuthenticationService, $sce) {
		$scope.login = function (username, password) {
			$scope.loginExceptionMessage = undefined;
			MGISAuthenticationService.login(username, password).then(function () {
				console.log("ok: " + username + ", " + password + ", " + JSON.stringify(arguments));

			}).catch(function (msg) {
				console.log("error: " + msg);
				var arr = /<body>(.*)<\/body>/i.exec(msg)
				var errorMessage = arr.length > 0 ? arr[1] : msg;
				errorMessage = errorMessage.replace(/&lt;/, "<");
				errorMessage = errorMessage.replace(/&gt;/g, ">");
				errorMessage = errorMessage.replace(/^"/, "");
				errorMessage = errorMessage.replace(/"$/, "");
				$scope.loginExceptionMessage = $sce.trustAsHtml(errorMessage);
			})
		}
	})
;
