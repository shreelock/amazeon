app.controller('homeCtrl', function($scope, $http) {
	var home = this;
	home.itemData = [];
	home.personInfo = $scope.personInfo;
	home.shoppingCart = [];


    function refreshShoppingCart(){
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


    };

    refreshShoppingCart();

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
        		$http.get('http://localhost:8080/itemReview/'+item.itemId).
        			then(function(response){
        				home.itemInfo['review'] = response.data;
        				console.log(data);
        			},function(error){
        				console.log(error);
        		});
	    },function myError(response) {
	    	console.log(response);
		});
    }

    home.addToCart = function (item) {
    	var alreadyPresent =  false;
    	home.shoppingCart.forEach(function(cartItem){
    		console.log(item);
    		if(cartItem.itemId == item.itemId && cartItem.sellerId == item.sellerId){

    			alreadyPresent = true;
    			if(item.quantity>cartItem.quantity){
	    			var itemToSend = Object.assign({},cartItem);
	    			itemToSend.quantity++;
	    			delete itemToSend.itemName;
	    			console.log(itemToSend);
	    			putShoppingCart(itemToSend);    				
    			}
    		}
    	});
    	if(!alreadyPresent){
    		putShoppingCart({itemId:item.itemId, sellerId:item.sellerId, customerId:home.personInfo.personId, quantity:1})
    	}
    };

    function putShoppingCart(item){
    	var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
    	$http.post('http://localhost:8080/addToCart', item, config).then(function (response) {
    			refreshShoppingCart();
                console.log(response);
            },function (error) {
                console.log(error);
            });
    }

    home.removeFromCart = function(item){
		var itemToSend = Object.assign({},item);
		delete itemToSend.itemName;
		if(itemToSend.quantity>1){
			itemToSend.quantity--;
			putShoppingCart(itemToSend);
		}else{
			home.deleteFromCart(itemToSend);
		}
    }

    home.deleteFromCart = function(item){
    	console.log('delete api to be implemented', item);
    }

	/*home.itemData = [{"itemId":1,"sellerId":4,"itemName":"ITMNAME1","sellerName":"Adidas","quantity":12},
				{"itemId":1,"sellerId":5,"itemName":"ITMNAME1","sellerName":"Nike","quantity":45},
				{"itemId":2,"sellerId":4,"itemName":"ITMNAME2","sellerName":"Adidas","quantity":11},
				{"itemId":2,"sellerId":5,"itemName":"ITMNAME2","sellerName":"Nike","quantity":34}];*/

});