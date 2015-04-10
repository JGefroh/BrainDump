(function() {
    function Service($http) {
        var self = this;
        var endpoints = {
            base: '../rest/security',
            getRoles: function() {
                return this.base + '/roles';
            }
        };

        self.getRoles = function() {
            return $http.get(endpoints.getRoles()).then(function(response) {
                return response.data;
            });
        };
    }
    angular
        .module('BrainDump.SecurityModule')
        .service('RoleService', ['$http', Service]);
})();