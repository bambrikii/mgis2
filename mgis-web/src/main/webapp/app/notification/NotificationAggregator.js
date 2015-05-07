define([ "backbone", "wreqr", "Application" ], function(Backbone, Wreqr,
		application) {
	var notificationEventAggregator = new Backbone.Wreqr.EventAggregator();
	return notificationEventAggregator;
});