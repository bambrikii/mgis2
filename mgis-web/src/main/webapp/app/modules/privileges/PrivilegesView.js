define([ "jquery", "underscore", "backbone", "marionette", "text!modules/privileges/PrivilegesView.htm" ], function($, _, Backbone, Marionette,
		privilegesViewTemplate) {
	var PrivilegesView = Marionette.LayoutView.extend({
		template : _.template(privilegesViewTemplate),
		regions : {
			usersListRegion : {
				selector : ".mgis2-privileges-users-list"
			},
			userDetailsRegion : {
				selector : ".mgis2-privileges-users-details"
			}
		}
	});
	return PrivilegesView;
});