angular.module("mgis.error.service", ["ui.bootstrap"])
	.factory("MGISErrorService", function ($modal, $rootScope) {
		var _currentId = 0;
		var _errors = new Array();

		return {
			errors: _errors,
			handleError: function (error) {
				var err = {}
				angular.copy(error, err);
				err.id = ++_currentId;
				err.date = new Date();
				_errors.push(err);
			},
			removeError: function (error) {
				for (var i in _errors) {
					var err = _errors[i];
					if (err.id == error.id) {
						_errors.splice(i, 1);
					}
				}
			}, removeAllErrors: function () {
				_errors.splice(0, _errors.length);
			}
		}
	});
