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
	
	$scope.nouveauProduitConditionne = function(myProduit,myConditionnement,conditionnement_prix, types){
		console.log("myProduit : " + myProduit.idProduit + ", myConditi : " + myConditionnement.idConditionnement + ", prix condi : " + conditionnement_prix + ", in carte ? " + types);
		
		$http.post('../gestProduitsConditionnes/nouveau', {
			"produitID":myProduit.idProduit,
			"conditionnementID":myConditionnement.idConditionnement,
			"produitConditionnePrix":conditionnement_prix
		}).success(function(response) {
			$('#panelNouveauProduitConditionne').slideToggle(600);
			console.log("prodCond a pousser : " + response.produitConditionne);
			$scope.produitsConditionnes.push(response.produitConditionne);
		});
	}
	
	//gestion des cases à cocher
	$scope.types = [];
	
	$scope.availableTypes = {
			'enCarte': 'en carte ?'
	}
});