var app = angular.module("amazeon",[]);

app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix(''); // by default '!'
  $locationProvider.html5Mode(true);
}]);

app.controller('mainCtrl', function($scope, $http) {
	
		$scope.loggedIn = {value:false};
		/*console.log($scope.username, $scope.password);
		//$httpProvider.defaults.useXDomain = true;
		delete $http.defaults.headers.common['X-Requested-With'];
		$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
	    $http.get('http://localhost:8080/person/'+$scope.username).
	        then(function(response) {
	            console.log(response);
        },function myError(response) {
        	console.log(response);
    	});*/
});

app.directive("signIn", function() {
    return {
        restrict : "EA",
        templateUrl : "/modules/signin/signin.html",
        controller : "signInCtrl",
        scope : true
    }
});

app.directive("homePage", function() {
    return {
        restrict : "EA",
        templateUrl : "/modules/home/home.html",
        controller : "homeCtrl",
        scope : true
    }
});




