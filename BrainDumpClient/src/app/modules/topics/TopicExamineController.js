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

        $scope.addSolution = function() {
            var modal = $modal.open(
                {
                    templateUrl: 'TopicSolutionEditPopup.html',
                    controller: 'TopicSolutionEditController',
                    size: 'lg',
                    resolve: {
                        isCreating: function() {
                            return true;
                        },
                        topicId: function() {
                            return $scope.topic.id;
                        },
                        solution: function() {
                            return {};
                        }
                    }
                }
            );
            modal.result.then(function(savedSolution) {
                $scope.topic.solutions.push(savedSolution);
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicExamineController', ['$scope', '$state', '$modal', 'TopicService', Controller]);
})();