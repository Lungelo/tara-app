(function() {
    'use strict';
    angular
        .module('taraApp')
        .factory('GetWay', GetWay);

    GetWay.$inject = ['$resource'];

    function GetWay ($resource) {
        var resourceUrl =  'api/get-ways/:id';

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
