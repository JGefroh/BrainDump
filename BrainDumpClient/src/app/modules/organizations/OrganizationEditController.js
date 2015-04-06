(function() {
    function Controller($scope, $modalInstance, OrganizationService) {
        function initialize(){
            $scope.vm = {
                operations: {
                    saveOrganization: {}
                }
            }
        }

        $scope.save = function(organization) {
            $scope.vm.operations.saveOrganization.status = 'LOADING';
            OrganizationService.saveOrganization(organization).then(function(savedOrganization) {
                $modalInstance.close(savedOrganization);
            })
            .finally(function() {
                $scope.vm.operations.saveOrganization.status = null;
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