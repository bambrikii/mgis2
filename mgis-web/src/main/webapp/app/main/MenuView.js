define([ "jquery", "jquery-ui", "backbone", "marionette", "slicknav",
		"text!main/MenuView.htm" ], function($, jqueryUI, Backbone, Marionette,
		SlickNav, menuViewTemplate) {
	var MenuView = Marionette.ItemView.extend({
		template : _.template(menuViewTemplate),
		onShow : function() {
			this.$el.slicknav({
				label : "",
				duration : 100,
				easingOpen : "easeOutBounce"
			});
			delete this.$el.empty();
		}
	});
	return MenuView;
});