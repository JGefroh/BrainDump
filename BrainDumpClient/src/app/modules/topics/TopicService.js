(function() {
    function Service($http) {
        var self = this;
        var endpoints = {
            getTopicsForOrganization: function(organizationId) {
                return '../rest/topics/{organizationId}'.replace('{organizationId}', organizationId);
            },
            getTopic: function(topicId) {
                return 'test_data/topic.json';
            }
        };


        self.getTopicsForOrganization = function(organizationId) {
            return $http.get(endpoints.getTopicsForOrganization(organizationId)).then(function(response) {
                return response.data;
            });
        };

        self.getTopic = function(topicId) {
            return $http.get(endpoints.getTopic(topicId)).then(function (response) {
                return response.data;
            });
        }
    }
    angular
        .module('BrainDump.TopicModule')
        .service('TopicService', ['$http', Service]);
})();