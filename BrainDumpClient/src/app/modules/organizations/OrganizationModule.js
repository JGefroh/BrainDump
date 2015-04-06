(function() {
    function Routes($stateProvider) {
        $stateProvider
            .state('organizations', {
                url: '/organizations',
                templateUrl: 'OrganizationView.html'
            })
            .state('organizations.selection', {
                url: '/selection',
                templateUrl: 'OrganizationSelectionView.html',
                controller: 'OrganizationSelectionController'
            })
    }
    angular
        .module('BrainDump.OrganizationModule', [])
        .config(['$stateProvider', Routes]);
})();