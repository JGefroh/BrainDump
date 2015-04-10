(function() {
    function Controller($scope, $modalInstance, UserService, OrganizationService, RoleService, organization) {
        function initialize() {
            $scope.form = {};
            $scope.operations = {
                addMember: {},
                loadRoles: {},
                loadMemberships: {},
                isInitializing: function() {
                    return this.loadRoles.status === 'LOADING' || this.loadMemberships.status === 'LOADING';
                }
            };
            $scope.organization = angular.copy(organization);
            UserService.heartbeat().then(function() {
                loadRoles();
                loadMembershipsForOrganization(organization.id);
            });
        }

        function loadMembershipsForOrganization(organizationId) {
            $scope.operations.loadMemberships.status = 'LOADING';
            OrganizationService.getMembershipsForOrganization(organizationId).then(function(memberships) {
                $scope.organization.memberships = memberships;
            })
            .finally(function() {
                $scope.operations.loadMemberships.status = null;
            });
        }

        function loadRoles() {
            $scope.operations.loadRoles.status = 'LOADING';
            RoleService.getRoles().then(function(roles) {
                $scope.roles = roles;
                $scope.form.roleToAdd = roles[0];
            })
            .finally(function() {
                $scope.operations.loadRoles.status = null;
            });
        }

        $scope.addMemberAs = function(userId, role) {
            $scope.operations.addMember.status = 'LOADING';
            OrganizationService.addMemberAs(userId, organization.id, role).then(function(membership) {
                var exists = false;
                angular.forEach($scope.organization.memberships, function(existingMembership) {
                    if (existingMembership.id === membership.id) {
                        angular.copy(membership, existingMembership);
                        exists = true;
                    }
                });
                if (!exists) {
                    $scope.organization.memberships.push(membership);
                }
            })
            .finally(function() {
                $scope.operations.addMember.status = null;
            })
        };

        $scope.removeMembership = function(membership) {
            $scope.operations.removeMembership[membership.id] = {
                status: 'LOADING'
            };
            OrganizationService.removeMembership(membership.user.id, organization.id).finally(function() {
                delete $scope.operations.removeMembership[membership.id];
            })
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };


        initialize();
    }
    angular
        .module('BrainDump.OrganizationModule')
        .controller('OrganizationMembershipEditController', ['$scope', '$modalInstance', 'UserService', 'OrganizationService', 'RoleService', 'organization', Controller]);
})();