//var apiEndpoint = "http://localhost:8080/";
var apiEndpoint = "http://84.52.56.74:8080/voistkonnad/";

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
        .state('newTeam', {
            url: "/",
            views: {
                "topView": { controller: 'TopMenuController',templateUrl: './views/top_clean_menu.html' },
                "contentView": { controller: 'NewTeamController',templateUrl: './views/newTeam.html' },
                "leftMenuView": {controller: 'LeftMenuController', templateUrl: 'views/left_menu.html' }
            }
        })
        .state('editTeam', {
            url: "/",
            views: {
                "topView": { controller: 'TopMenuController',templateUrl: './views/top_clean_menu.html' },
                "contentView": { controller: 'EditTeamController',templateUrl: './views/editTeam.html' },
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
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8';
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


app.controller('TopMenuController', ['$scope', '$rootScope', 'UserService','sysMessage',
    function ($scope, $rootScope, UserService, sysMessage) {
        $rootScope.bodyClass = 'content_bg';
        $rootScope.authorised = false;
        $scope.compName = "Võistkondade register";


        UserService.check(function(response){
            $rootScope.authorised = true;
            $scope.worker = response;
        },function(){
            console.log("unauthorised");
        });

        $scope.login = function(username, password){
            UserService.login({login: username, password: password}, function(response){
                $scope.worker = response;
                $rootScope.authorised = true;
            }, function(){
                sysMessage.error("Vale kasutajanimi või parool");
            });
        };
        $scope.logout = function(){
            $rootScope.authorised = false;
            UserService.logout();
        }
    }]);

app.controller('MainController', ['$scope', 'Teams', 'sysMessage', '$rootScope',
    function ($scope, Teams, sysMessage, $rootScope) {
        $rootScope.left_menu_active = 'activeTeams';
        Teams.get_active(function(response){
            $scope.teams = response;
        });

    }]);

app.controller('AllTeamsController', ['$scope', 'Teams', 'sysMessage', '$rootScope','$state',
    function ($scope, Teams, sysMessage, $rootScope, $state) {
        $rootScope.left_menu_active = 'allTeams';
        $scope.teamStates = [];

        var loadTeams = function (){
            Teams.get_all(function(response){
                $scope.teams = response;
            });
        };

        loadTeams();

        Teams.get_all_states(function(response){
            $scope.teamStates = response;
        });

        $scope.getStatesForTeam = function(stateName){
            if(stateName == "mitteaktiivne"){
                return $.grep($scope.teamStates, function(state){
                    return state.stateCode != 3;
                })
            } else if(stateName == "laiali_saadetud"){
                return $.grep($scope.teamStates, function(state){
                    return state.stateCode == 3;
                })
            } else {
                return $scope.teamStates;
            }
        };
        $scope.changeTeamState = function(team){
            var teamState = $.grep($scope.teamStates, function(state){return state.stateName == team.state})[0];
            Teams.change_state({teamId:team.teamId, data:teamState.stateCode},
                function(response){
                    if(response){
                        var index = $scope.teams.indexOf(team);
                        $scope.teams.splice(index,1);
                        $scope.teams.splice(index,0,response);
                    }
                }, function (){
                    loadTeams();
                });
        };

        $scope.editTeam = function(team){
            $rootScope.editableTeam = team.teamId;
            $state.go('editTeam');
        }
    }]);

app.controller('NewTeamController', ['$scope', 'Teams', 'sysMessage', '$rootScope','$state',
    function ($scope, Teams, sysMessage, $rootScope, $state) {
        $rootScope.left_menu_active = 'newTeam';
        $scope.countries = [];
        $scope.spotrs = [];
        $scope.team = {
            description:" "
        };

        Teams.get_sports(function(response){
            $scope.sports = response;
        });
        Teams.get_countries(function(response){
            $scope.countries = response;
        });

        $scope.createTeam = function(){
            Teams.create($scope.team, function(response){
                sysMessage.ok("Salvestatud");
                $rootScope.editableTeam = response.teamId;
                $state.go('editTeam')
            }, function(){
                sysMessage.error("Viga salvestamisel");
            });
        };

    }]);
app.controller('EditTeamController', ['$scope', 'Teams', 'sysMessage', '$rootScope',
    function ($scope, Teams, sysMessage, $rootScope) {
        $rootScope.left_menu_active = 'editTeam';
        $scope.countries = [];
        $scope.spotrs = [];
        $scope.team = {
            teamId : 0,
            description:" "
        };

        Teams.get_by_id({teamId:$rootScope.editableTeam}, function(response){
            $scope.team = response;
            loadOtherData();
        });

        var loadOtherData = function(){
            Teams.get_sports(function(response){
                $scope.sports = response;
                if ($scope.team.teamId != 0){
                    $scope.team.sportCode = $.grep($scope.sports, function(sport){
                        return sport.sportName == $scope.team.sport;
                    })[0].sportCode;
                }
            });
            Teams.get_countries(function(response){
                $scope.countries = response;
                if ($scope.team.teamId != 0){
                    $scope.team.countryCode = $.grep($scope.countries, function(country){
                        return country.countryName == $scope.team.country;
                    })[0].countryCode;
                }
            });
        };


        $scope.editTeam = function(){
            Teams.update($scope.team, function(response){
                $scope.team = response;
                loadOtherData();
            },function(){
                sysMessage.error("Viga salvestamisel");
            });
        };

    }]);
