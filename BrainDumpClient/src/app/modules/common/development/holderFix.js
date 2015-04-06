(function() {
    function Directive() {
        return {
            link: function (scope, element, attrs) {
                Holder.run({ images: element[0], nocss: true });
            }
        };
    }
    angular
        .module('BrainDump.DevelopmentModule', [])
        .directive('holderFix', Directive);
})();