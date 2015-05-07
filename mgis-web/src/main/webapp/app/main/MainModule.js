define([ "main/MainView", "main/MainModuleAggregator", "modules/isogd/IsogdModule", "modules/privileges/PrivilegesModule" ], function(MainView,
		mainModuleAggregator, IsogdModule, PrivilegesModule) {
	var MainModule = function() {
		this.createView = function(privileges) {
			this.mainView = new MainView({
				privileges : privileges
			});
			return this.mainView;
		}
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
				throw new Error("no module found for " + moduleName);
				break;
			}
			me.mainView.contentRegion.show(module.createView());
		});
	}
	return MainModule;
});