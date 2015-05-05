define([ "jquery", "underscore", "backbone", "marionette" ], function($, _,
		Backbone, Marionette) {
	var ApplicationRouter = Marionette.AppRouter.extend({
		appRoutes : {
			"*notFound" : "main"
		}
	});
	console.log("router");
	return ApplicationRouter;
});