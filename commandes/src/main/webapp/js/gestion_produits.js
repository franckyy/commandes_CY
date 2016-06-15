
// cette ligne crée un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var chezYenApp = angular.module("chezYenApp", []);

//Création d'un controlleur "StoreCtrl" dans notre module
//le scope est automatiquement injecté par angular
chezYenApp.controller("produitCtrl", function($scope, $http) {
	
});