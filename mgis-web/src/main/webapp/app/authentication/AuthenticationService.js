define([ "jquery", "notification/NotificationAggregator" ], function($, NotificationAggregator) {
	var AuthenticationService = function() {
		this.getPrivileges = function(successHandler) {
			successHandler([ "ROLE_ADMIN", "ROLE_USER" ]);
			return;
			$.ajax({
				url : "auth/privileges",
				success : function(data) {
					successHandler(data);
				},
				error : function(data, status, error) {
					NotificationModule
				}
			});
		}
	}
	return new AuthenticationService();
});