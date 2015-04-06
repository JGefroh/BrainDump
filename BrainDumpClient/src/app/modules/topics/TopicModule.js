(function() {
    function Routes($stateProvider) {
        $stateProvider
            .state('topics', {
                url: '/topics',
                templateUrl: 'TopicView.html',
                abstract: true
            })
            .state('topics.selection', {
                url: '/selection/:organizationId',
                templateUrl: 'TopicSelectionView.html',
                controller: 'TopicSelectionController'
            })
            .state('topics.examine', {
                url: '/examine/:topicId',
                templateUrl: 'TopicExamineView.html',
                controller: 'TopicExamineController'
            })
    }
    angular
        .module('BrainDump.TopicModule', [])
        .config(['$stateProvider', Routes]);
})();