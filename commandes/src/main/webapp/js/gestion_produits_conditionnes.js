var chezYenApp = angular.module("chezYenApp", []);

chezYenApp.controller("produitsConditionnesCtrl", function($scope, $http){
	$scope.produitsConditionnes = [];
	$scope.listeProduits  = [];
	$scope.myProduit = [];	//par défaut, le premier produit sera affiché.
	$scope.listeConditionnements = [];
	$scope.myConditionnement = [];	//par défaut, le premier conditionnement sera affiché.
	$scope.produitConditionneModif;
	
	$http.get('../gestProduitsConditionnes/repertoire').then (function(response) {
		$scope.produitsConditionnes = response.data.produitsConditionnes;
		$scope.listeProduits = response.data.listeProduits;
		$scope.myProduit = $scope.listeProduits[0];
		$scope.listeConditionnements = response.data.listeConditionnements;
		$scope.myConditionnement = $scope.listeConditionnements[0];
	});
	
	
	//gestion des cases à cocher
	$scope.types = [];
	
	$scope.availableTypes = {
			'enCarte': 'en carte ?'
	}
});