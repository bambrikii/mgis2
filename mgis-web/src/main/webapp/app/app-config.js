document.addEventListener("DOMContentLoaded", function(event) {
	require.config({
		baseUrl : 'app',
		paths : {
			jquery : "../bower_components/jquery/jquery",
			"jquery-ui" : "../bower_components/jquery-ui/jquery-ui.min",
			underscore : "../bower_components/underscore/underscore",
			backbone : "../bower_components/backbone/backbone",
			bootstrap : "../bower_components/bootstrap/dist/js/bootstrap",
			text : "../bower_components/requirejs-text/text",
			marionette : '../bower_components/marionette/lib/backbone.marionette',
			// backbone_modal :
			// '../bower_components/backbone-modal/backbone.modal',
			wreqr : "../bower_components/backbone.wreqr/lib/backbone.wreqr",
			backgrid : "../bower_components/backgrid/lib/backgrid",
			slicknav: "../bower_components/slicknav/dist/jquery.slicknav"
		},
		shim : {
			underscore : {
				exports : '_'
			},
			backbone : {
				deps : [ 'jquery', 'underscore' ]
			},
			marionette : {
				deps : [ 'backbone' ]
			},
			"jquery-ui" : {
				deps : [ 'jquery' ]
			},
			bootstrap : {
				deps : [ 'jquery', 'jquery-ui' ],
				exports : '$.fn.alert'
			},
			wreqr : {
				deps : [ "backbone" ]
			},
			backgrid : {
				depends : [ "backbone", "jquery" ],
				exports : "Backgrid"
			},
			slicknav: {
				depends: ["jquery", "jquery-ui"],
				exports: "Plugin"
			}
		},
		enforceDefine : true
	});
	require([ 'app' ]);
});