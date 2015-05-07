define([ "jquery", "jquery-ui", "backbone", "marionette", "text!main/MenuView.htm", "bootstrap", "main/MainModuleAggregator" ], function($, jqueryUI,
		Backbone, Marionette, menuViewTemplate, Bootstrap, mainModuleAggregator) {
	var MenuView = Marionette.ItemView.extend({
		template : _.template(menuViewTemplate),
		initialize : function(options) {
			this.privileges = options.privileges;
		},
		events : {
			"click a#mgis2-isogd-menu-link" : "menuIsogdClicked",
			"click a#mgis2-privileges-menu-link" : "menuPrivilegesClicked"
		},
		menuIsogdClicked : function(target, options) {
			console.log("menuIsogdClicked");
			mainModuleAggregator.trigger("openModule", target, {
				moduleName : "isogd"
			});
		},
		menuPrivilegesClicked : function(target, options) {
			console.log("menuPrivilegesClicked");
			mainModuleAggregator.trigger("openModule", target, {
				moduleName : "privileges"
			});
		},
		onShow : function() {
		}
	});
	return MenuView;
});