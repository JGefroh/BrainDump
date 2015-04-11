(function() {
    function Routes($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('splash', {
                url: '/splash',
                templateUrl: 'modules/splash/Splash.html',
                controller: 'SplashController'
            });
        $urlRouterProvider.otherwise('/splash');
    }
    angular
        .module('BrainDump.RoutesModule', [])
        .config(['$stateProvider', '$urlRouterProvider', Routes]);
})();