(function() {
    function Service($http) {
        var self = this;
        var endpoints = {
            base: '../rest/organizations',
            getOrganizations: function() {
                return this.base;
            },
            saveOrganization: function() {
                return this.base;
            }
        };


        self.getOrganizations = function() {
            return $http.get(endpoints.getOrganizations()).then(function(response) {
                return response.data;
            });
        };

        self.saveOrganization = function(organization) {
            return $http.put(endpoints.saveOrganization(), organization).then(function(response) {
                return response.data;
            });
        }
    }
    angular
        .module('BrainDump.OrganizationModule')
        .service('OrganizationService', ['$http', Service]);
})();