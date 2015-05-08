define([ "jquery", "notification/NotificationAggregator" ], function($, notificationAggregator) {
	var AuthenticationService = function() {
		this.getPrivileges = function(successHandler) {
			$.ajax({
				method : "GET",
				url : "rest/auth/privileges.json",
				success : function(data) {
					successHandler(data);
				},
				error : function(data, status, error) {
					notificationAggregator.error(error);
				}
			});
		}
	}
	return new AuthenticationService();
});