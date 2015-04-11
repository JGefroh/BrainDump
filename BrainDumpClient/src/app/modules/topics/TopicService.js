(function() {
    function Service($http) {
        var self = this;
        var endpoints = {
            base: '../rest/topics',
            getTopicsForOrganization: function(organizationId) {
                return this.base + '/organizations/{organizationId}'.replace('{organizationId}', organizationId);
            },
            getTopic: function(topicId) {
                return this.base + '/{topicId}'.replace('{topicId}', topicId);
            },
            saveTopic: function() {
                return this.base;
            },
            deleteTopic: function(topicId) {
                return this.base + '/{topicId}'.replace('{topicId}', topicId);
            },
            addSolution: function(topicId) {
                return this.base + '/{topicId}/solutions'.replace('{topicId}', topicId);
            },
            saveSolution: function() {
                return this.base + '/solutions'
            },
            kudoSolution: function(solutionId) {
                return this.base + '/solutions/{solutionId}/kudo'.replace('{solutionId}', solutionId);
            },
            unkudoSolution: function(solutionId) {
                return this.base + '/solutions/{solutionId}/unkudo'.replace('{solutionId}', solutionId);
            },
            deleteSolution: function(solutionId) {
                return this.base + '/solutions/{solutionId}'.replace('{solutionId}', solutionId);
            }
        };


        // Topics

        self.getTopicsForOrganization = function(organizationId) {
            return $http.get(endpoints.getTopicsForOrganization(organizationId)).then(function(response) {
                return response.data;
            });
        };

        self.getTopic = function(topicId) {
            return $http.get(endpoints.getTopic(topicId)).then(function(response) {
                return response.data;
            });
        };

        self.saveTopic = function(topic) {
            return $http.put(endpoints.saveTopic(), topic).then(function(response) {
                return response.data;
            });
        };

        self.deleteTopic = function(topicId) {
            return $http.delete(endpoints.deleteTopic(topicId)).then(function(response) {
                return response.data;
            });
        };


        // Solutions

        self.addSolution = function(topicId, solution) {
            return $http.put(endpoints.addSolution(topicId), solution).then(function(response) {
                return response.data;
            });
        };

        self.saveSolution = function(solution) {
            return $http.put(endpoints.saveSolution(), solution).then(function(response) {
                return response.data;
            });
        };

        self.kudoSolution = function(solutionId) {
            return $http.put(endpoints.kudoSolution(solutionId)).then(function(response) {
                return response.data;
            });
        };

        self.unkudoSolution = function(solutionId) {
            return $http.put(endpoints.unkudoSolution(solutionId)).then(function(response) {
                return response.data;
            });
        };

        self.deleteSolution = function(solutionId) {
            return $http.delete(endpoints.deleteSolution(solutionId)).then(function(response) {
                return response.data;
            });
        };
    }
    angular
        .module('BrainDump.TopicModule')
        .service('TopicService', ['$http', Service]);
})();