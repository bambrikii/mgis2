define([ "backbone", "wreqr" ], function(Backbone, Wreqr) {
	var notificationEventAggregator = new Backbone.Wreqr.EventAggregator();
	return notificationEventAggregator;
});