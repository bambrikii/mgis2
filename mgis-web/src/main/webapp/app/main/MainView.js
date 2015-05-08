define([ "jquery", "underscore", "backbone", "marionette", "text!main/MainView.htm", "main/MenuView" ], function($, _, Backbone, Marionette,
		mainViewTemplate, MenuView) {
	var MainView = Marionette.LayoutView.extend({
		template : _.template(mainViewTemplate),
		initialize : function(options) {
			this.privileges = options.privileges;
		},
		regions : {
			menuRegion : ".main-view-menu",
			contentRegion : ".main-view-content",
			notificationRegion : ".main-view-notification"
		},
		onShow : function() {
			var menu = new MenuView({
				privileges : this.privileges
			});
			this.menuRegion.show(menu);
		}
	});
	return MainView;
});