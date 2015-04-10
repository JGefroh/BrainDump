(function() {
    function Controller($scope, $modalInstance, TopicService
                        , isCreating, model) {
        function initialize(){
            $scope.operations = {
                saveTopic: {}
            };
            $scope.form = {};
            $scope.form.isCreating = isCreating;
            $scope.form.topic = angular.copy(model);
        }

        $scope.save = function(topic) {
            $scope.operations.saveTopic.status = 'LOADING';
            TopicService.saveTopic(topic).then(function(savedTopic) {
                $modalInstance.close(savedTopic);
            })
            .finally(function() {
                $scope.operations.saveTopic.status = null;
            })
        };

        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };


        initialize();
    }
    angular
        .module('BrainDump.TopicModule')
        .controller('TopicEditController', ['$scope', '$modalInstance', 'TopicService',
                                            'isCreating', 'model', Controller]);
})();