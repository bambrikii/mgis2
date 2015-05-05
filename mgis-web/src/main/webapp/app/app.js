define([ "jquery", "underscore", "backbone", 'marionette', 'Application',
		'ApplicationRouter' ], function($, _, Backbone, Marionette,
		application, ApplicationRouter) {
	application.start({});
	application.addRegions({
		mainRegion : ".main-container"
	});
	application.addInitializer(function(options) {
		console.log("addInitializer");

		var arcaController = {
			main : function() {
				console.log("main route");
				application.buildMain();
			}
		}
		var router = new ApplicationRouter({
			controller : arcaController,
			onRoute : function(name, path, args) {
				console.log("onRoute..." + name + ", " + path);
			}
		});
		router.on('route', function(actions) {
			console.log("route: " + actions);
		});
		if (!Backbone.History.started) {
			Backbone.history.start({
			// pushState : true
			});
		}
	});

	return application;
});