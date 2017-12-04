app.controller('homeCtrl', function($scope, $http) {
	var home = this;
	home.data = [{"itemId":1,"sellerId":4,"itemName":"ITMNAME1","sellerName":"Adidas","quantity":12},
				{"itemId":1,"sellerId":5,"itemName":"ITMNAME1","sellerName":"Nike","quantity":45},
				{"itemId":2,"sellerId":4,"itemName":"ITMNAME2","sellerName":"Adidas","quantity":11},
				{"itemId":2,"sellerId":5,"itemName":"ITMNAME2","sellerName":"Nike","quantity":34}];
	
});