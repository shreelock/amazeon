app.controller('homeCtrl', function($scope, $http) {
	var home = this;
	home.itemData = [];
	home.personInfo = $scope.personInfo;
	home.shoppingCart = [];

	$http.get('http://localhost:8080/getCart/' + home.personInfo.personId).
        then(function(response) {
        	console.log(response.data);
        	response.data.forEach(function(item){
        		home.shoppingCart.push(item);
        	});
    },function myError(response) {
    	console.log(response);
	});


	$http.get('http://localhost:8080/listItems/').
        then(function(response) {
        	console.log(response.data);
        	response.data.forEach(function(item){
	        	home.itemData.push(item);
        	});
    },function myError(response) {
    	console.log(response);
	});

    home.itemInfo = {};

    home.getItemInfo = function(item){
		$http.get('http://localhost:8080/itemInfo/'+item.itemId).
	        then(function(response) {
	        	console.log(response.data);
	        	for(var key in response.data){
	        		home.itemInfo[key] = response.data[key];
	        	}
	    },function myError(response) {
	    	console.log(response);
		});
    }

	/*home.itemData = [{"itemId":1,"sellerId":4,"itemName":"ITMNAME1","sellerName":"Adidas","quantity":12},
				{"itemId":1,"sellerId":5,"itemName":"ITMNAME1","sellerName":"Nike","quantity":45},
				{"itemId":2,"sellerId":4,"itemName":"ITMNAME2","sellerName":"Adidas","quantity":11},
				{"itemId":2,"sellerId":5,"itemName":"ITMNAME2","sellerName":"Nike","quantity":34}];*/

});