(function() {
    function Service($http, UserService) {
        var self = this;
        var endpoints = {
            base: '../rest/users',
            getSelf: function() {
                return this.base;
            },
            saveUser: function() {
                return this.base;
            }
        };

        this.getSelf = function() {
            return $http.get(endpoints.getSelf()).then(function(response) {
                return response.data;
            });
        };

        this.saveUser = function(user) {
            return $http.put(endpoints.saveUser(), user).then(function(response) {
                return response.data;
            });
        };
    }
    angular
        .module('BrainDump.SecurityModule')
        .service('UserAdminService', ['$http', 'UserService', Service]);
})();