(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('DevCleaningTool', DevCleaningTool);

    DevCleaningTool.$inject = ['$resource'];

    function DevCleaningTool ($resource) {
        var resourceUrl =  'api/dev-cleaning-tools/:id';

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
