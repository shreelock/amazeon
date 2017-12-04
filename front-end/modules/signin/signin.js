app.controller('signInCtrl', function($scope, $http) {
	var si = this;
	si.signIn= function(){
		console.log($scope.username, $scope.password);
		//$httpProvider.defaults.useXDomain = true;
		delete $http.defaults.headers.common['X-Requested-With'];
		$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
	    $http.get('http://localhost:8080/person/'+$scope.username).
	        then(function(response) {
	            console.log(response);
        },function myError(response) {
        	console.log(response);
    	});
	}
	$scope.username = 'ayush';
	$scope.password = '';
});