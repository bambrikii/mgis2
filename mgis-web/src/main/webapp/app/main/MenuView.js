define([ "jquery", "jquery-ui", "backbone", "marionette",
		"text!main/MenuView.htm", "bootstrap" ], function($, jqueryUI,
		Backbone, Marionette, menuViewTemplate, Bootstrap) {
	var MenuView = Marionette.ItemView.extend({
		template : _.template(menuViewTemplate),
		onShow : function() {
		}
	});
	return MenuView;
});