define([ "jquery", "underscore", "backbone", "marionette" ], function($, _, Backbone, Marionette) {
	var ApplicationRouter = Marionette.AppRouter.extend({
		appRoutes : {
			// "isogd" : "isogd",
			// "privileges" : "privileges",
			"*notFound" : "main"
		}
	});
	console.log("router");
	return ApplicationRouter;
});