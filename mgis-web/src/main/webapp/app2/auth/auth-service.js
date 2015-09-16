angular.module("mgis.auth.service", [])
	.factory('MGISRedirectInterceptor', function ($q, $location, $window) {
		return {
			'response': function (response) {
				if (typeof response.data === 'string') {
					if (response.data.indexOf("ng-app=\"mgis\"") > -1) {
						$window.location.href = "mgis2";
						return $q.reject(response);
					} else if (response.data.indexOf("ng-app=\"mgis.auth.app\"") > -1) {
						$window.location.href = "login";
						return $q.reject(response);
					}
				}
				return response;
			}
		}

	})
	.
	config(function ($httpProvider) {
		$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
		$httpProvider.interceptors.push('MGISRedirectInterceptor');
	})
	.factory("MGISAuthenticationService", function ($http, $q) {

		return {
			login: function (username, password) {
				var deferred = $q.defer();
				$http.post("login", "username=" + username + "&password=" + password)
					.success(function (data, status, headers, config) {
						deferred.resolve({});
					})
					.error(function (msg, code) {
						deferred.reject(msg);
					});
				return deferred.promise;
			}
		}
	})
;
