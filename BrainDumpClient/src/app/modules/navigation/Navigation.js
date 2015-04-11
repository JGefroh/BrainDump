(function() {
    function Directive() {
        function DirectiveController($scope, applicationName, versionNumber, UserService, OrganizationService) {
            function initialize() {
                $scope.applicationName = applicationName;
                $scope.versionNumber = versionNumber;
                $scope.organizationUtil = OrganizationService;
                $scope.user = UserService.user;
            }
            initialize();
        }
        return {
            restrict: 'A',
            scope: {
            },
            templateUrl: 'Navigation.html',
            controller: ['$scope', 'applicationName', 'versionNumber', 'UserService', 'OrganizationService', DirectiveController]
        }
    }
    angular
        .module('BrainDump.NavigationModule', [])
        .directive('navigation', [Directive]);
})();