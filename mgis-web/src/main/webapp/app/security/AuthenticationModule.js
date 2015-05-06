define([ "jquery", "notification/NotificationController" ], function($,
		NotificationController) {
	var AuthenticationModule = function() {
		this.getPrivileges = function(successHandler) {
			$.get({
				success : function(data) {
					successHandler(data);
				},
				error : function(data, status, error) {
					NotificationModule
				}
			});
		}
	}
	return new AuthenticationModule();
});