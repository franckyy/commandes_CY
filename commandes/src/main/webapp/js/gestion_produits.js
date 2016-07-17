
// cette ligne crée un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var chezYenApp = angular.module("chezYenApp", []);

// Création d'un controlleur "StoreCtrl" dans notre module
// le scope est automatiquement injecté par angular
chezYenApp.controller("produitCtrl", function($scope, $http) {

   $scope.produits = [];
   $scope.produitModif;
   
   $http.get('../gestProduits/repertoire').then(function (response) {
       $scope.produits = response.data.produits;
   });
   
   $scope.nouveau_produit = function(designation, prix, stock){
	   console.log("nouveau produit désignation : " + designation);
	   
	   http.post('../gestProduits/nouveau', {
		   "produitDesignation": designation,
		   "produitPrix": prix,
		   "produitStock": stock
	   }).success(function(response){
		   $("#panelNouveauProduit").slideToggle(600);
		   //the following line must be tried 
		   //$scope.$apply();
		   $scope.Produits.push(response.produit);
	   });
   };
});