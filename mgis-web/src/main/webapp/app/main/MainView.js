define([ "jquery", "underscore", "backbone", "marionette",
		"text!main/MainView.htm", "main/MenuView" ], function($, _, Backbone,
		Marionette, mainViewTemplate, MenuView) {
	var MainView = Marionette.LayoutView.extend({
		template : _.template(mainViewTemplate),
		regions : {
			menuRegion : ".main-view-menu",
			contentRegion : ".main-view-content"
		},
		onShow : function() {
			var menu = new MenuView();
			this.menuRegion.show(menu);
		}
	});
	return MainView;
});