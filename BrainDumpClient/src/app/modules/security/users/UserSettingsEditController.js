(function() {
    function Controller($scope, UserService, UserAdminService) {
        function initialize(){
            $scope.form = {};
            $scope.operations = {
                saveSettings: {},
                loadUser: {
                    status: 'LOADING'
                }
            };

            UserService.heartbeat().then(function() {
                $scope.loadSelf();
            });
        }

        $scope.loadSelf = function() {
            $scope.operations.loadUser.status = 'LOADING';
            UserAdminService.getSelf().then(function(user) {
                $scope.form.user = user;
            })
            .finally(function() {
                $scope.operations.loadUser.status = null;
            });
        };

        $scope.save = function(user) {
            $scope.operations.saveSettings.status = 'LOADING';
            UserAdminService.saveUser(user).then(function(savedUser) {
                angular.copy(savedUser, $scope.form.user);
            })
            .finally(function() {
                $scope.operations.saveSettings.status = null;
            });
        };


        initialize();
    }
    angular
        .module('BrainDump.SecurityModule')
        .controller('UserSettingsEditController', ['$scope', 'UserService', 'UserAdminService', Controller]);
})();