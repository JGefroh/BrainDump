(function() {
    function Controller($scope, $state, $modal, TopicService) {
        function initialize() {
            $scope.operations = {
                loadTopic: {},
                addSolution: {},
                deleteTopic: {}
            };
            $scope.form = {
                input: {}
            };
            $scope.loadTopic($state.params.topicId);
        }

        $scope.loadTopic = function(topicId) {
            $scope.operations.loadTopic.status = 'LOADING';
            TopicService.getTopic(topicId).then(function(topic) {
                $scope.topic = topic;
            })
            .finally(function() {
                $scope.operations.loadTopic.status = null;
            });
        };

        $scope.deleteTopic = function(topic) {
            $scope.operations.deleteTopic.status = 'LOADING';
            TopicService.deleteTopic(topic.id).then(function() {
                $state.go('topics.selection', {organizationId: topic.organizationId});
            })
            .finally(function() {
                $scope.operations.deleteTopic.status = null;
            });
        };

        $scope.editTopic = function(topic) {
            var modal = $modal.open(
                {
                    templateUrl: 'TopicEditPopup.html',
                    controller: 'TopicEditController',
                    size: 'lg',
                    resolve: {
                        isCreating: function() {
                            return false;
                        },
                        model: function() {
                            return topic;
                        }
                    }
                }
            );
            modal.result.then(function(savedTopic) {
                angular.copy(savedTopic, topic);
            });
        };

        $scope.addSolution = function(solutionText) {
            $scope.operations.addSolution.status = 'LOADING';
            TopicService.addSolution($state.params.topicId, solutionText).then(function(savedSolution) {
                $scope.topic.solutions.push(savedSolution);
                $scope.form.input.solution = null;
            })
            .finally(function() {
                $scope.operations.addSolution.status = null;
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicExamineController', ['$scope', '$state', '$modal', 'TopicService', Controller]);
})();