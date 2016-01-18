var services = angular.module('voiskondadeApp.services', ['ngResource']);

services.factory('Teams', ['$resource',
    function ($resource) {
        return $resource('',{},{
                get_active: {method: 'GET', url: apiEndpoint + 'teams/active/', isArray:true},
                get_all: {method: 'GET', url: apiEndpoint + 'teams/all/', isArray:true},
                get_all_states: {method: 'GET', url: apiEndpoint + 'teams/states/', isArray:true},
                create: {method: 'POST', url: apiEndpoint + 'teams/create/', params: { team : '@team'}},
                update: {method: 'POST', url: apiEndpoint + 'teams/:teamId', params: { data : '@data', teamId : '@teamId'}},
                change_state: {method: 'POST', url: apiEndpoint + 'teams/:teamId/state', params: { teamStateCode : '@data', teamId : '@teamId'}},
                get_sports: {method: 'POST', url: apiEndpoint + 'teams/sports', isArray:true},
                get_countries: {method: 'GET', url: apiEndpoint + 'teams/countries', isArray:true}
            });}]);

services.factory('UserService', ['$resource',
    function ($resource) {
        return $resource('',{},{
                check: {method: 'GET', url: apiEndpoint + 'user/check/'},
                login: {method: 'GET', url: apiEndpoint + 'user/login/', params:{login:'@login', password:'@password'}},
                logout: {method: 'GET', url: apiEndpoint + 'user/logout/'}
            });}]);

services.factory("sysMessage", ['toaster','$filter', function (toaster, $filter) {
    return {
        ok: function (message) {
            toaster.pop('success', 'Success', message);
        },
        error: function (message) {
            toaster.pop('error', 'Error', message);
        }
    }
}]);

services.factory("sysLocation", ['$location', function ($location) {
    return {
        goHome: function () {
            $location.path('/');
        },
        goList: function () {
            $location.path('/list/');
        },
        goLink: function (link) {
            $location.path(link);
        }
    }
}]);

services.factory('browser', ['$window', function($window) {

     return {

        detectBrowser: function() {

            var userAgent = $window.navigator.userAgent;

            var browsers = {
                chrome: /chrome/i, 
                safari: /safari/i, 
                firefox: /firefox/i, 
                ie: /internet explorer/i, 
                opera: /opera/i
            };

            for(var key in browsers) {
                if (browsers[key].test(userAgent)) {
                    return key;
                }
            }

           return 'unknown';
       }
    }
}]);