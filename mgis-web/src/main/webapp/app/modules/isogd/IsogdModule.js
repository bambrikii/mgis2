define([ "jquery", "underscore", "backbone", "marionette" ], function($, _, Backbone, Marionette) {
	var IsogdModule = function() {
		this.createView = function() {
			return new Marionette.LayoutView({
				template : _.template("ISOGD")
			})
		}
	}
	return IsogdModule;
});