(function() {
    function Controller($scope, $state, UserService) {
        function initialize() {
            $scope.operations = {
                authenticate: {}
            };
            $scope.input = {};
            UserService.heartbeat().then(function() {
                if (UserService.isLoggedIn()) {
                    $scope.input.uuid = UserService.user.uuid;
                }
                else {
                    generateUUID();
                }
            })
            .catch(function(error) {
                    generateUUID();
            });
        }

        function generateUUID() {
            UserService.generateId().then(function(uuid) {
                $scope.input.uuid = uuid;
            });
        }

        $scope.authenticate = function(uuid) {
            $scope.operations.authenticate.status = 'LOADING';
            UserService.authenticate(uuid).then(function() {
                $state.go('organizations.selection');
            })
            .finally(function() {
                $scope.operations.authenticate.status = null;
            });
        };
        initialize();
    }
    angular
        .module('BrainDump.SplashModule', [])
        .controller('SplashController', ['$scope', '$state', 'UserService', Controller]);
})();