define([ "jquery", "underscore", "backbone", "marionette", "main/MainView" ],
		function($, _, Backbone, Marionette, MainView) {
			var Application = Marionette.Application.extend({
				initialize : function() {

				},
				channelName : 'appChannel'
			});

			var app = new Application({
				container : ".main-container",
				buildMain : function() {
					if (!app.mainView) {
						var mainView = new MainView();
						mainView.render();
						app.mainView = mainView;
						app.mainRegion.show(mainView);
						return mainView;
					}
					return arcaApp.mainView;
				}
			});
			return app;
		});