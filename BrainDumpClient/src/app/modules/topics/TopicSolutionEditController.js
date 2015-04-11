(function() {
    function Controller($scope, $modalInstance, TopicService, isCreating, topicId, solution) {
        function initialize(){
            $scope.operations = {
                    saveSolution: {}
            };
            $scope.form = {};
            $scope.form.isCreating = isCreating;
            $scope.form.input = angular.copy(solution);
        }

        $scope.save = function(solution) {
            $scope.operations.saveSolution.status = 'LOADING';
            if (isCreating) {
                createSolution(solution);
            }
            else {
                updateSolution(solution);
            }
        };

        function createSolution(solution) {
            TopicService.addSolution(topicId, solution).then(function(savedSolution) {
                $modalInstance.close(savedSolution);
            })
            .finally(function() {
                $scope.operations.saveSolution.status = null;
            });
        }

        function updateSolution(solution) {
            TopicService.saveSolution(solution).then(function(savedSolution) {
                $modalInstance.close(savedSolution);
            })
            .finally(function() {
                $scope.operations.saveSolution.status = null;
            })
        }

        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };


        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicSolutionEditController', ['$scope', '$modalInstance', 'TopicService',
                                                    'isCreating', 'topicId', 'solution', Controller]);
})();