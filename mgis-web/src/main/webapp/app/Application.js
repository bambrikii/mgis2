define([ "jquery", "underscore", "backbone", "marionette", "main/MainModule", "notification/NotificationAggregator" ], function($, _, Backbone,
		Marionette, MainModule, notificationAggregator) {

	var Application = Marionette.Application.extend({
		initialize : function() {
		},
		channelName : 'appChannel'
	});

	var app = new Application({
		container : ".main-container",
		buildMain : function(privileges) {
			if (!app.mainInitialized) {
				this.mainModule = new MainModule({
					region : app.mainRegion
				});
				app.mainRegion.show(this.mainModule.createView(privileges));
				app.mainInitialized = true;
			}
		},
		openModule : function() {
			this.mainModule.openModule(moduleName);
		}
	});

	return app;
});