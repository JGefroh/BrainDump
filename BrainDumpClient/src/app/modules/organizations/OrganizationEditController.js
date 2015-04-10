(function() {
    function Controller($scope, $modalInstance, OrganizationService) {
        function initialize(){
            $scope.operations = {
                saveOrganization: {}
            };
        }

        $scope.save = function(organization) {
            $scope.operations.saveOrganization.status = 'LOADING';
            console.info(organization);
            OrganizationService.saveOrganization(organization).then(function(savedOrganization) {
                $modalInstance.close(savedOrganization);
            })
            .finally(function() {
                $scope.operations.saveOrganization.status = null;
            })
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };


        initialize();
    }
    angular
        .module('BrainDump.OrganizationModule')
        .controller('OrganizationEditController', ['$scope', '$modalInstance', 'OrganizationService', Controller]);
})();