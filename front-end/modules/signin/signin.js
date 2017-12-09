app.controller('signInCtrl', function($scope, $http) {
	console.log("ayush",$scope.loggedIn.value);
	var si = this;
	si.signIn= function(){
		console.log('ayush')
		
		console.log($scope.username, $scope.password);
		//$httpProvider.defaults.useXDomain = true;
	    $http.get('http://localhost:8080/person_name/'+$scope.username).
	        then(function(response) {
	            if(response.data.securePassword === $scope.password){
	            	$scope.loggedIn.value = true;
	            	for(var key in response.data){
	            		$scope.personInfo[key] = response.data[key];
	            	}
	            	
	            }else{
	            	console.log('Invalid Credentials');
	            }
        },function myError(response) {
        	console.log(response);
    	});
	}
	$scope.username = 'ayush';
	$scope.password = '';
});