(function() {
    function Routes($stateProvider) {
        $stateProvider
            .state('user', {
                url: '/user',
                templateUrl: 'users/UserView.html'
            })
            .state('user.settings', {
                url: '/settings',
                templateUrl: 'users/UserSettingsEditView.html',
                controller: 'UserSettingsEditController'
            })
    }
    angular
        .module('BrainDump.SecurityModule', [])
        .config(['$stateProvider', Routes]);
})();