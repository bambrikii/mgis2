define([ "backbone", "wreqr" ], function(Backbone, Wreqr) {
	var mainModuleAggregator = new Backbone.Wreqr.EventAggregator();
	return mainModuleAggregator;
});