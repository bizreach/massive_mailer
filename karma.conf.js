module.exports = function(config) {
    config.set({
        basePath: './src/main/',
        colors: true,
        browsers: ['HeadlessChrome'],
        customLaunchers: {
          HeadlessChrome: {
            base: 'ChromeHeadless',
            flags: [
                '--no-sandbox'
            ],
            debug: true
          }
        },
        frameworks: ['jasmine'],
        files: [
            { pattern: 'webapp/resources/lib/bootstrap/js/jquery.js', watched: false },
            { pattern: 'webapp/resources/lib/bootstrap/js/bootstrap.js', watched: false },

            'webapp/resources/js/**/*.js',

            '../test/javascript/**/*Test.js',
        ],
        singleRun: true,
    });
};

