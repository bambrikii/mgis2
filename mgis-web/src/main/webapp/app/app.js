define([ "jquery", "underscore", "backbone", 'marionette', 'Application', 'ApplicationRouter', "authentication/AuthenticationService" ], function($,
		_, Backbone, Marionette, application, ApplicationRouter, authenticationService) {
	application.start({});
	application.addRegions({
		mainRegion : ".main-container"
	});
	application.addInitializer(function(options) {
		console.log("addInitializer");

		var controller = {
			main : function() {
				console.log("main route");
				authenticationService.getPrivileges(function(privileges) {
					application.buildMain(privileges);
				});
			}
		}
		var router = new ApplicationRouter({
			controller : controller,
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