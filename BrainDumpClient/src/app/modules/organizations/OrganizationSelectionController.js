(function() {
    function Controller($scope, $modal, OrganizationService, UserService) {
        function initialize() {
            $scope.operations = {
                loadOrganizations: {}
            };
            UserService.heartbeat().then(function() {
                $scope.loadOrganizations();
            });
        }

        $scope.createOrganization = function() {
            var modal = $modal.open(
                {
                    templateUrl: 'OrganizationEditPopup.html',
                    controller: 'OrganizationEditController',
                    size: 'lg',
                    resolve: {
                    }
                }
            );
            modal.result.then(function(organization) {
                $scope.organizations.push(organization);
            });
        };

        $scope.changeMembership = function(organization) {
            var modal = $modal.open(
                {
                    templateUrl: 'OrganizationMembershipEditPopup.html',
                    controller: 'OrganizationMembershipEditController',
                    size: 'lg',
                    resolve: {
                        organization: function() {
                            return organization;
                        }
                    }
                }
            );
            modal.result.then(function(organization) {
                $scope.organizations.push(organization);
            });
        };

        $scope.loadOrganizations = function() {
            $scope.operations.loadOrganizations.status = 'LOADING';
            OrganizationService.getOrganizations().then(function(organizations) {
                $scope.organizations = organizations;
            })
            .finally(function() {
                $scope.operations.loadOrganizations.status = null;
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.OrganizationModule')
        .controller('OrganizationSelectionController', ['$scope', '$modal', 'OrganizationService', 'UserService', Controller]);
})();