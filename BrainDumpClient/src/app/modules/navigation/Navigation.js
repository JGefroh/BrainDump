(function() {
    function Directive() {
        function DirectiveController($scope, applicationName, versionNumber, UserService) {
            function initialize() {
                $scope.applicationName = applicationName;
                $scope.versionNumber = versionNumber;
            }

            $scope.user = UserService.user;
            initialize();
        }
        return {
            restrict: 'A',
            scope: {
            },
            templateUrl: 'Navigation.html',
            controller: ['$scope', 'applicationName', 'versionNumber', 'UserService', DirectiveController]
        }
    }
    angular
        .module('BrainDump.NavigationModule', [])
        .directive('navigation', [Directive]);
})();