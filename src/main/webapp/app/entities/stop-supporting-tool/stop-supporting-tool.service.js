(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('StopSupportingTool', StopSupportingTool);

    StopSupportingTool.$inject = ['$resource'];

    function StopSupportingTool ($resource) {
        var resourceUrl =  'api/stop-supporting-tools/:id';

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
