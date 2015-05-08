define([ "jquery", "backbone", "marionette", "notification/NotificationAggregator", "text!notification/InformationView.htm",
		"text!notification/WarningView.htm", "text!notification/ErrorView.htm" ], function($, Backbone, Marionette, notificationAggregator,
		informationViewTemplate, warningViewTemplate, errorViewTemplate) {
	var NotificationModule = function(region) {
		this.region = region;

		var me = this;
		this.createInformationView = function(message) {
			// TODO:
		}
		this.createWarningView = function(message) {
			// TODO:
		}
		this.createErrorView = function(options) {
			var ErrorView = Marionette.ItemView.extend({
				template : _.template(errorViewTemplate)
			});
			var errorModel = new Backbone.Model(options);
			console.log("errorModel: " + JSON.stringify(errorModel));
			var errorView = new ErrorView({
				model : errorModel
			});
			this.region.show(errorView);
			$(errorView.$el.find("div.modal")).modal({});
		}

		var me = this;
		notificationAggregator.on("information", function(message) {
			me.createInformationView(message);
		});
		notificationAggregator.on("warning", function(message) {
			me.createWarningView(message);
		});
		notificationAggregator.on("error", function(message) {
			me.createErrorView(message);
		});

	}
	return NotificationModule;
});