define([ "main/MainView", "main/MainModuleAggregator", "modules/isogd/IsogdModule", "modules/privileges/PrivilegesModule",
		"notification/NotificationAggregator", "notification/NotificationModule" ], function(MainView, mainModuleAggregator, IsogdModule,
		PrivilegesModule, notificationAggregator, NotificationModule) {
	var MainModule = function(notificationModule) {
		this.createView = function(privileges) {
			this.mainView = new MainView({
				privileges : privileges
			});
			var notificationModule = new NotificationModule(this.mainView.notificationRegion);
			var me = this;
			mainModuleAggregator.on("openModule", function(target, options) {
				var module = null;
				var moduleName = options.moduleName;
				switch (moduleName) {
				case "isogd":
					module = new IsogdModule();
					break;
				case "privileges":
					module = new PrivilegesModule();
					console.log("privileges");
					break;
				default:
					// throw new Error("no module found for " + moduleName);
					notificationAggregator.error("no module found for " + moduleName);
					return;
				}
				me.mainView.contentRegion.show(module.createView());
			});
			return this.mainView;
		}
	}
	return MainModule;
});