(function() {
    function Controller($scope, $state, $modal, UserService, TopicService, OrganizationService) {
        function initialize() {
            $scope.organizationId = null;
            $scope.operations = {
                loadTopics: {}
            };
            UserService.heartbeat().then(function() {
                if ($state.params.organizationId) {
                    $scope.organizationId = $state.params.organizationId;
                    OrganizationService.setActiveOrganization({id: $scope.organizationId});
                    $scope.loadTopics($scope.organizationId);
                }
                else {
                    $state.go('organizations.selection');
                }
            });
        }

        $scope.createTopic = function() {
            var modal = $modal.open(
                {
                    templateUrl: 'TopicEditPopup.html',
                    controller: 'TopicEditController',
                    size: 'lg',
                    resolve: {
                        isCreating: function() {
                            return true;
                        },
                        model: function() {
                            return {
                                organizationId: $scope.organizationId
                            };
                        }
                    }
                }
            );
            modal.result.then(function(topic) {
                $state.go('topics.examine', {topicId: topic.id});
            });
        };

        $scope.loadTopics = function(organizationId) {
            $scope.operations.loadTopics.status = 'LOADING';
            TopicService.getTopicsForOrganization(organizationId).then(function(topics) {
                $scope.topics = topics;
            })
            .finally(function() {
                $scope.operations.loadTopics.status = null;
            });
        };

        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicSelectionController', ['$scope', '$state', '$modal',
                                                 'UserService', 'TopicService', 'OrganizationService', Controller]);
})();