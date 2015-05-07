define([ "jquery", "underscore", "backbone", "marionette", "modules/privileges/PrivilegesView" ],
		function($, _, Backbone, Marionette, PrivilegesView) {
			var PrivilegesModule = function(region) {
				this.createView = function() {
					this.privilegesView = new PrivilegesView();
					return this.privilegesView;
				}
				this.listUsers = function() {

				}
				this.editPrivileges = function() {

				}
			}
			return PrivilegesModule;
		});