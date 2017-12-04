var app = angular.module("amazeon",[]);

app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix(''); // by default '!'
  $locationProvider.html5Mode(true);
}]);

app.directive("signIn", function() {
    return {
        restrict : "EA",
        templateUrl : "/modules/signin/signin.html",
        controller : "signInCtrl"
    }
});

app.directive("homePage", function() {
    return {
        restrict : "EA",
        templateUrl : "/modules/home/home.html",
        controller : "homeCtrl"
    }
});




