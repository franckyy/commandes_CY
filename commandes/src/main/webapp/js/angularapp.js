
// cette ligne creer un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var storeApp = angular.module("storeApp", ['productsModule', 'storeDirectives', 'ngRoute']);


storeApp.config(function($routeProvider) {
   $routeProvider.when("/products", {
      templateUrl: "views/products.html" 
   });
   $routeProvider.when("/admin", {
       templateUrl: "views/admin.html"
   });
   $routeProvider.otherwise( {
      templateUrl: "views/products.html" 
   });
    
});
// creation d'un controlleur "toDoCtrl" dans notre module
// le scope est automatiquement injecté par angular
storeApp.controller("StoreCtrl", function($scope, $http) {
   $scope.produits = [];
   $scope.currentView = 'views/productsList.html';
   $scope.testvalue = "bienvenue";
   $http.get('rest/produit').then(function (response) {
       $scope.produits = response.data.produits;
   });
   
   $scope.navigateTo = function(view) {
       $scope.currentView = view;
   };
 
   
});