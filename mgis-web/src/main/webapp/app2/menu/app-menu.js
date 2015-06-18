angular.module("mgis.menu.main", [ "ui.router" ]) //
.directive("mainMenu", function() {
	return {
		restrict : "E",
		replace : true,
		transclude : true,
		template : '<div class="navbar navbar-default"><div class="container-fluid"><menu-title><span translate>MGIS</span></menu-title><ul class="nav navbar-nav" ng-transclude></ul></div></div></div>'

	}
}) //
.directive("menuTitle", function() {
	return {
		restrict : "E",
		replace : true,
		transclude : true,
		template : '<div class="navbar-header"><a class="navbar-brand" href="#/" ng-transclude>Menu Title</a></div>'
	}
}) //
.directive("menuItem", function() {
	return {
		restrict : "E",
		replace : true,
		scope : {
			link : "@"
		},
		transclude : true,
		template : '<li><a href="{{link}}" ng-transclude>Menu Item</a></li>'
	}
}) //
.controller("MainMenuCtrl", function($scope) {

});