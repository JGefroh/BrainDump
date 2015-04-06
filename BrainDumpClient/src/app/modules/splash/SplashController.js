(function() {
    function Controller($scope, $state, UserService) {
        function initialize() {
            $scope.vm = {
                input: {}
            };

            UserService.heartbeat().then(function() {
                if (UserService.isLoggedIn()) {
                    $scope.vm.input.uuid = UserService.user.uuid;
                }
                else {
                    UserService.generateId().then(function(uuid) {
                        $scope.vm.input.uuid = uuid;
                    });
                }
            });
        }

        $scope.authenticate = function(uuid) {
            UserService.authenticate(uuid).then(function() {
                $state.go('organizations.selection');
            });
        };
        initialize();
    }
    angular
        .module('BrainDump.SplashModule', [])
        .controller('SplashController', ['$scope', '$state', 'UserService', Controller]);
})();