(function() {
    function Service($http) {
        var self = this;
        self.activeOrganization = {};

        var endpoints = {
            base: '../rest/organizations',
            getOrganizations: function() {
                return this.base;
            },
            saveOrganization: function() {
                return this.base;
            },
            getMembershipsForOrganization: function(organizationId) {
                return this.base + '/{organizationId}/memberships'
                        .replace('{organizationId}', organizationId)
            },
            addMemberAs: function(userId, organizationId, role) {
                return this.base + '/{organizationId}/memberships?userId={userId}&role={role}'
                        .replace('{organizationId}', organizationId)
                        .replace('{userId}', userId)
                        .replace('{role}', role);
            },
            removeMembership: function(userToRemoveId, organizationId) {
                return this.base + '/{organizationId}/memberships?userId={userToRemoveId}'
                        .replace('{organizationId}', organizationId)
                        .replace('{userId}', userToRemoveId);
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
        };

        self.getMembershipsForOrganization = function(organizationId) {
            return $http.get(endpoints.getMembershipsForOrganization(organizationId)).then(function(response) {
                return response.data;
            });
        };

        self.addMemberAs = function(userId, organizationId, role) {
            return $http.put(endpoints.addMemberAs(userId, organizationId, role)).then(function(response) {
                return response.data;
            });
        };

        self.removeMembership = function(userToRemoveId, organizationId) {
            return $http.put(endpoints.removeMembership(userToRemoveId, organizationId)).then(function(response) {
                return response.data;
            });
        };


        // Utilities

        self.setActiveOrganization = function(organization) {
            self.activeOrganization = organization;
        }
    }
    angular
        .module('BrainDump.OrganizationModule')
        .service('OrganizationService', ['$http', Service]);
})();