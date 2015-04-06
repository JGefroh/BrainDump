(function() {
    function Controller($scope, $state, TopicService) {
        function initialize() {
            $scope.vm = {};
            $scope.loadTopic($state.params.topicId);
        }

        $scope.loadTopic = function(topicId) {
            TopicService.getTopic(topicId).then(function(topic) {
                $scope.vm.topic = topic;
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicExamineController', ['$scope', '$state', 'TopicService', Controller]);
})();