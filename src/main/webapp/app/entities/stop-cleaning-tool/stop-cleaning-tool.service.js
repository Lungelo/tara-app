(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('StopCleaningTool', StopCleaningTool);

    StopCleaningTool.$inject = ['$resource'];

    function StopCleaningTool ($resource) {
        var resourceUrl =  'api/stop-cleaning-tools/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
