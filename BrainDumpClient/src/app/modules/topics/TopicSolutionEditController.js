(function() {
    function Controller($scope, $modalInstance, TopicService, solution) {
        function initialize(){
            $scope.operations = {
                    saveSolution: {}
                };
            $scope.form = {};
            $scope.form.input = angular.copy(solution);
        }

        $scope.save = function(solution) {
            $scope.operations.saveSolution.status = 'LOADING';
            TopicService.saveSolution(solution).then(function(savedSolution) {
                $modalInstance.close(savedSolution);
            })
            .finally(function() {
                $scope.operations.saveSolution.status = null;
            })
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };


        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicSolutionEditController', ['$scope', '$modalInstance', 'TopicService',
                                                    'solution', Controller]);
})();