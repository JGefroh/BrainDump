(function() {
    function Routes($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('splash', {
                url: '/splash',
                templateUrl: 'modules/splash/Splash.html',
                controller: 'SplashController'
            });
    }
    angular
        .module('BrainDump.RoutesModule', [])
        .config(['$stateProvider', '$urlRouterProvider', Routes]);
})();