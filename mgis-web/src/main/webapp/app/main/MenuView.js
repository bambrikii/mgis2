define([ "jquery", "jquery-ui", "backbone", "marionette", "text!main/MenuView.htm", "bootstrap", "main/MainModuleAggregator" ], function($, jqueryUI,
		Backbone, Marionette, menuViewTemplate, Bootstrap, mainModuleAggregator) {
	var MenuView = Marionette.ItemView.extend({
		template : _.template(menuViewTemplate),
		initialize : function(options) {
			this.privileges = options.privileges;
		},
		events : {
			"click a#mgis2-isogd-menu-link" : "menuIsogdClicked",
			"click a#mgis2-privileges-menu-link" : "menuPrivilegesClicked",
			"click a#mgis2-invalid-menu-link" : "menuInvalidClicked",
		},
		// TODO: aggregate all click handlers into a single one
		menuIsogdClicked : function(target, options) {
			mainModuleAggregator.trigger("openModule", target, {
				moduleName : "isogd"
			});
		},
		menuPrivilegesClicked : function(target, options) {
			mainModuleAggregator.trigger("openModule", target, {
				moduleName : "privileges"
			});
		},
		menuInvalidClicked : function(target, options) {
			mainModuleAggregator.trigger("openModule", target, {
				moduleName : "invalid"
			});
		},
		onShow : function() {
		}
	});
	return MenuView;
});