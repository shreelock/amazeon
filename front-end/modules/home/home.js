app.controller('homeCtrl', function($scope, $http) {
	var home = this;
	home.itemData = [];
	home.personInfo = $scope.personInfo;
	home.shoppingCart = [];
    home.addressOptions = [];
    home.paymentGateWayOptions = ["Visa", "Master Card", "American Express", "e-Wallet"];
    home.deliveryOptions = ["Free delivery", "Express delivery", "Prime delivery"];
    home.selectedDeliveryOption = '';
    home.selectedAddress = '';
    home.selectedGateway = ''
    
    function getAddressByPersonID(){
        home.addressOptions = [];
        $http.get('http://localhost:8080/getaddr/'+home.personInfo.personId).
            then(function(response) {
                console.log(response.data);
                response.data.forEach(function(item){
                    home.addressOptions.push({'label': item.address, 'value': item.addrType});
                });
        },function myError(response) {
            console.log(response);
        });
    };

    getAddressByPersonID();

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




    home.itemInfo = {};

    function getAllItem(){
        home.itemData=[];
        $http.get('http://localhost:8080/listItems/').
            then(function(response) {
                console.log(response.data);
                response.data.forEach(function(item){
                    home.itemData.push(item);
                });
        },function myError(response) {
            console.log(response);
        });

    }

    getAllItem();


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

    function formPostData(){
        data = {};
        data.customer_id = home.personInfo.personId;
        data.amount = 1500;
        data.gateway = home.selectedGateway;
        data.orderDate = "1994-02-19";
        data.shipper_name = home.selectedDeliveryOption;
        data.address_type = home.selectedAddress;
        orderCart = [];
        home.shoppingCart.forEach(function(item){
            orderCart.push({customerId:home.personInfo.personId, itemId:item.itemId, sellerId:item.sellerId, quantity:item.quantity});
        });

        data.cartItems = orderCart;
        return data;
    }

    home.placeOrder = function(){
        data = formPostData();
        var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            };
        console.log(data);
        $http.post('http://localhost:8080/placeOrder1', data, config).then(function (response) {
                refreshShoppingCart();
                getAllItem();
                console.log(response);
            },function (error) {
                console.log(error);
            });

    }

	/*home.itemData = [{"itemId":1,"sellerId":4,"itemName":"ITMNAME1","sellerName":"Adidas","quantity":12},
				{"itemId":1,"sellerId":5,"itemName":"ITMNAME1","sellerName":"Nike","quantity":45},
				{"itemId":2,"sellerId":4,"itemName":"ITMNAME2","sellerName":"Adidas","quantity":11},
				{"itemId":2,"sellerId":5,"itemName":"ITMNAME2","sellerName":"Nike","quantity":34}];*/

});