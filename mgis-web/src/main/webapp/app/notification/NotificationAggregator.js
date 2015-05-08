define([ "backbone", "wreqr" ], function(Backbone, Wreqr) {
	var notificationEventAggregator = new Backbone.Wreqr.EventAggregator();
	notificationEventAggregator.error = function(message) {
		this.trigger("error", {
			title : "Error",
			message : message
		});
	}
	notificationEventAggregator.information = function(message) {
		this.trigger("information", {
			title : "Information",
			message : message
		});
	}
	notificationEventAggregator.information = function(message) {
		this.trigger("warning", {
			title : "Warning",
			message : message
		});
	}
	return notificationEventAggregator;
});