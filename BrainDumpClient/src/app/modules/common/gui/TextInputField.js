(function() {
    /**
     * @ngdoc directive
     * @name textInputField
     * @param label
     * @param model
     * @param isLoading
     */
    function Directive() {
        function Controller($scope) {
            $scope.isValid = function(validationState) {
                return validationState === 'VALID';
            };
            $scope.isInvalid = function(validationState) {
                return validationState === 'INVALID';
            };
        }
        return {
            restrict: 'A',
            scope: {
                label: '@',
                model: '=',
                isLoading: '=',
                validationState: '=',
                isShowingMessage: '=',
                isDisabled: '='
            },
            transclude: true,
            templateUrl: 'TextInputField.html',
            controller: ['$scope', Controller]
        };
    }
    angular
        .module('com.jgefroh.WidgetModule')
        .directive('textInputField', Directive);
})();