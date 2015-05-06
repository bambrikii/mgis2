define([ "jquery", "underscore", "backbone", "marionette",
		"notification/NotificationView.htm" ], function($, _, Backbone,
		Marionette, notificationViewTemplate) {
	var NotificationView = Marionette.ItemView.extend({
		template : _.template(notificationViewTemplate)
	})
	return NotificationView;
});