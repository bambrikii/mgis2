module.exports = function(grunt) {

	// tell grunt to load jshint task plugin.
	grunt.loadNpmTasks('grunt-contrib-jshint');

	// configure tasks
	grunt.initConfig({
		jshint : {
			files : [ 'GruntFile.js', 'src/main/resources/javascript/**/*.js',
					'src/test/javascript/**/*.js' ],
			options : {
				ignores : [ 'src/main/resources/javascript/lib/**/*.js' ]
			}
		}
	// more plugin configs go here.
	});

	grunt.registerTask('default', [ 'jshint' ]);

};
