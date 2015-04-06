(function() {
    function Controller($scope, $state, TopicService) {
        function initialize() {
            $scope.vm = {};
            $scope.loadTopics($state.params.organizationId);
        }

        $scope.loadTopics = function(organizationId) {
            TopicService.getTopicsForOrganization(organizationId).then(function(topics) {
                $scope.vm.topics = topics;
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicSelectionController', ['$scope', '$state', 'TopicService', Controller]);
})();