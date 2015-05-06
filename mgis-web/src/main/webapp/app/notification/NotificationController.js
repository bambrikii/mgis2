define([ "jquery", "notification/NotificationView" ], function($,
		NotificationView) {
	var NotificationModule = function() {
		this.warn = function(message) {
			var view = new NotificationView();
			// TODO:
		};
		this.error = function(message) {
			var view = new NotificationView();
			// TODO:
		}
	}
	return new NotificationModule();
});