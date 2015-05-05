define([ "jquery", "underscore", "backbone", "marionette",
		"text!main/MainView.htm" ], function($, _, Backbone, Marionette,
		mainViewTemplate) {
	var MainView = Marionette.LayoutView.extend({
		template : _.template(mainViewTemplate),
		regions : {
			menuRegion : ".main-view-menu",
			contentRegion : ".main-view-content"
		}
	});
	return MainView;
});