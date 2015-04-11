(function() {
    function Directive() {
        function Controller($scope, $modal, TopicService) {
            function initialize() {
                $scope.operations = {
                    deleteSolution: {},
                    kudoSolution: {},
                    unkudoSolution: {},
                    isLoading: function() {
                        return this.kudoSolution.status === 'LOADING' || this.unkudoSolution.status === 'LOADING'
                                || this.deleteSolution === 'LOADING';
                    }
                }
            }

            $scope.editSolution = function(solution) {
                var modal = $modal.open(
                    {
                        templateUrl: 'TopicSolutionEditPopup.html',
                        controller: 'TopicSolutionEditController',
                        size: 'lg',
                        resolve: {
                            isCreating: function() {
                                return false;
                            },
                            topicId: function() {
                                return null; //[JG] Not needed to edit.
                            },
                            solution: function() {
                                return solution;
                            }
                        }
                    }
                );
                modal.result.then(function(savedSolution) {
                    angular.copy(savedSolution, solution);
                });
            };

            $scope.deleteSolution = function(solution) {
                $scope.operations.deleteSolution.status = 'LOADING';
                TopicService.deleteSolution(solution.id).finally(function() {
                    $scope.operations.deleteSolution.status = null;
                });
            };

            $scope.addKudo = function(solution) {
                $scope.operations.kudoSolution.status = 'LOADING';
                TopicService.kudoSolution(solution.id).then(function(kudodSolution) {
                    angular.copy(kudodSolution, solution);
                })
                .finally(function() {
                    $scope.operations.kudoSolution.status = null;
                });
            };

            $scope.removeKudo = function(solution) {
                $scope.operations.unkudoSolution.status = 'LOADING';
                TopicService.unkudoSolution(solution.id).then(function(unkudodSolution) {
                    angular.copy(unkudodSolution, solution);
                })
                .finally(function() {
                    $scope.operations.unkudoSolution.status = null;
                });
            };

            initialize();
        }
        return {
            restrict: 'A',
            scope: {
                solution: '='
            },
            templateUrl: 'TopicSolution.html',
            controller: ['$scope', '$modal', 'TopicService', Controller]
        };
    }
    angular
        .module('BrainDump.TopicModule')
        .directive('topicSolution', Directive);
})();