(function() {
    function Controller($scope, $modal, OrganizationService, UserService) {
        function initialize() {
            $scope.vm = {
                operations: {
                    loadOrganizations: {}
                }
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
                $scope.vm.organizations.push(organization);
            });
        };

        $scope.loadOrganizations = function() {
            $scope.vm.operations.loadOrganizations.status = 'LOADING';
            OrganizationService.getOrganizations().then(function(organizations) {
                $scope.vm.organizations = organizations;
            })
            .finally(function() {
                $scope.vm.operations.loadOrganizations.status = null;
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.OrganizationModule')
        .controller('OrganizationSelectionController', ['$scope', '$modal', 'OrganizationService', 'UserService', Controller]);
})();