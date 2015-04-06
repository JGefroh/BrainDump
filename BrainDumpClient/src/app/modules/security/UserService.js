(function() {
    function UserService($q, $http, $stateParams) {
        var self = this;
        var endpoints = {
            base: '../rest/security',
            authenticate: function() {
                return this.base;
            },
            generateId: function() {
                return this.base + '/uuid'
            },
            heartbeat: function() {
                return this.base + '/heartbeat'
            }
        };
        self.user = null;

        this.heartbeat = function() {
            return $http.get(endpoints.heartbeat()).then(function(response) {
                self.user = response.data;
                return response.data;
            });
        };

        this.authenticate = function(uuid) {
            return $http.put(endpoints.authenticate(), uuid).then(function(response) {
                self.user = response.data;
                return response.data;
            });
        };

        this.generateId = function(uuid) {
            return $http.get(endpoints.generateId()).then(function(response) {
                return response.data;
            });
        };


        /**
         * Business Logic
         */
        this.isLoggedIn = function() {
            return self.user !== null;
        };
    }
    angular
        .module('BrainDump.SecurityModule', [])
        .service('UserService', ['$q', '$http', '$stateParams', UserService]);
})();