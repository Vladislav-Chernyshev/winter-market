angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/winter/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallBack(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.winterMarketUser;
        $http.default.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.winterMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.winterMarketUser) {
        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.winterMarketUser;
                $http.default.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.default.headers.common.Authorization = '';
    }


    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/winter/api/v1/products')
            .then(function (response) {
                $scope.productsList = response.data;
                // console.log(response);
            });
    };

    $scope.showProductInfo = function (productId) {
        $http.get('http://localhost:8189/winter/api/v1/products/' + productId)
            .then(function (response) {
                alert(response.data.title);
            });
    }


    $scope.deleteProductById = function (productId) {
        $http.delete('http://localhost:8189/winter/api/v1/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8189/winter/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.loadProducts();
            });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/winter/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
                // console.log(response);
            });
    };

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/winter/api/v1/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:8189/winter/api/v1/cart/clear')
            .then(function (response) {
                $scope.cart = response.data;
                $scope.loadCart();
            });
    }

    $scope.plusProductToCart = function (productId) {
        $http.get('http://localhost:8189/winter/api/v1/cart/plus/' + productId)
            .then(function (response) {
                $scope.cart = response.data;
                $scope.loadCart();
            });
    }

    $scope.minusProductToCart = function (productId) {
        $http.get('http://localhost:8189/winter/api/v1/cart/minus/' + productId)
            .then(function (response) {
                $scope.cart = response.data;
                $scope.loadCart();
            });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete('http://localhost:8189/winter/api/v1/cart/' + productId)
            .then(function (response) {
                $scope.cart = response.data;
                $scope.loadCart();
            });
    }

    $scope.loadProducts();
    $scope.loadCart();
});