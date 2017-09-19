(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('DevSupportingTool', DevSupportingTool);

    DevSupportingTool.$inject = ['$resource'];

    function DevSupportingTool ($resource) {
        var resourceUrl =  'api/dev-supporting-tools/:id';

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
