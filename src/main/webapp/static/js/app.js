var apiEndpoint = "http://localhost:8080/";
//var apiEndpoint = "http://84.52.56.74:8080/voistkonnad/";

var app = angular.module('voiskondadeApp', ['voiskondadeApp.services','ngRoute', 'ngSanitize', 'ui.bootstrap', 'toaster', 'ui.router']);

app.config(['$routeProvider','$stateProvider','$urlRouterProvider', function ($routeProvider, $stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('activeTeams', {
            url: "/",
            views: {
                "topView": { controller: 'TopMenuController',templateUrl: './views/top_clean_menu.html' },
                "contentView": { controller: 'MainController',templateUrl: './views/activeTeams.html' },
                "leftMenuView": {controller: 'LeftMenuController', templateUrl: 'views/left_menu.html' }
            }
        })
        .state('allTeams', {
            url: "/",
            views: {
                "topView": { controller: 'TopMenuController',templateUrl: './views/top_clean_menu.html' },
                "contentView": { controller: 'AllTeamsController',templateUrl: './views/allTeams.html' },
                "leftMenuView": {controller: 'LeftMenuController', templateUrl: 'views/left_menu.html' }
            }
        });
}]);

app.factory('AuthInterceptor', ['$q' ,function ($q) {
    return {
        responseError: function (response) {
            if(response.status === 401){
                return $q.reject(response);
            } else {
                return $q.reject(response);
            }
        }
    };
}]);

app.config(function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';

    $httpProvider.interceptors.push([
        '$injector',
        function ($injector) {
            return $injector.get('AuthInterceptor');
        }
    ]);

});


app.controller('LeftMenuController', ['$scope', '$location', '$http', '$rootScope',
    function ($scope, $location, $http, $rootScope) {
    }]);


app.controller('TopMenuController', ['$scope', '$location', '$http', '$rootScope',
    function ($scope, $location, $http, $rootScope) {
        $rootScope.bodyClass = 'content_bg';
        $rootScope.left_menu_active = '';
        $scope.compName = "Small Ambitious company";
    }]);

app.controller('MainController', ['$scope', 'Teams', 'sysMessage', '$rootScope',
    function ($scope, Teams, sysMessage, $rootScope) {
        $rootScope.left_menu_active = 'activeTeams';
        Teams.get_active(function(response){
            $scope.teams = response;
        });

    }]);
app.controller('AllTeamsController', ['$scope', 'Teams', 'sysMessage', '$rootScope',
    function ($scope, Teams, sysMessage, $rootScope) {
        $rootScope.left_menu_active = 'allTeams';
        Teams.get_all(function(response){
            $scope.teams = response;
        });
        Teams.get_all_states(function(response){
            $scope.teamStates = response;
        });

        $scope.getStatesForTeam = function(stateName){
            if(stateName == "mitteaktiivne"){
                return $.grep($scope.teamStates, function(state){
                    return state.stateId != 3;
                })
            } else if(stateName == "laiali_saadetud"){
                return $.grep($scope.teamStates, function(state){
                    return state.stateId == 3;
                })
            } else {
                return $scope.teamStates;
            }
        };
        $scope.changeTeamState = function(team){
            Teams.change_state({teamId:team.teamId, data:team.state}, function(response){

            });
        }
    }]);
