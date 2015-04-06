/**
 * Defines and configures all modules.
 */
angular
    .module('BrainDump',
    [
        'ui.router',
        'ui.bootstrap',
        'com.jgefroh.WidgetModule',
        'BrainDump.DevelopmentModule',
        'BrainDump.RoutesModule',
        'BrainDump.SplashModule',
        'BrainDump.NavigationModule',
        'BrainDump.OrganizationModule',
        'BrainDump.TopicModule',
        'BrainDump.SecurityModule'
    ]);
angular
    .module('BrainDump')
    .constant('applicationName', 'BrainDump')
    .constant('versionNumber', 'v0.0.0');