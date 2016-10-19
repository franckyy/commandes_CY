var chezYenApp = angular.module("chezYenApp", []);

chezYenApp.controller("produitsConditionnesCtrl", function($scope, $http){
	$scope.produitsConditionnes = [];
	$scope.listeProduits  = [];
	$scope.myProduit = [];	//par défaut, le premier produit sera affiché.
	$scope.listeConditionnements = [];
	$scope.myConditionnement = [];	//par défaut, le premier conditionnement sera affiché.
	$scope.produitConditionneModif;
	$scope.listeTable = [];
	
	$http.get('../gestProduitsConditionnes/repertoire').then (function(response) {
		$scope.produitsConditionnes = response.data.produitsConditionnes;
		$scope.listeProduits = response.data.listeProduits;
		$scope.myProduit = $scope.listeProduits[0];
		$scope.listeConditionnements = response.data.listeConditionnements;
		$scope.myConditionnement = $scope.listeConditionnements[0];
		$scope.listeTable = response.data.listeTable;
	});
	
	$scope.nouveauProduitConditionne = function(myProduit,myConditionnement,conditionnement_prix, types){
		console.log("myProduit : " + myProduit.idProduit + ", myConditi : " + myConditionnement.idConditionnement + ", prix condi : " + conditionnement_prix + ", in carte ? " + types);
		if(myProduit != null && myConditionnement != null && conditionnement_prix != null) {
			$http.post('../gestProduitsConditionnes/nouveauProduitConditionne', {
				"conditionnementID":myConditionnement,
				"produitID":myProduit,
				"conditionnementPrix":conditionnement_prix,
				"enCarte":types
			}).success(function(response){
				console.log("my reponse");
				$scope.produitConditionneModif = response.data.pc;
				$scope.listeTable.push($scope.produitConditionneModif);
			})
		} else {
			console.log("ajout de produit conditionné impossible !");
		}
	}

	//gestion des orderBy
	$scope.reverseSort = false;
	$scope.sortColumn = "pc.produitDesignation";
	console.log("produitsConditionnes : " + $scope.produitsConditionnes);
	$scope.sortData = function(column) {
			$scope.reverseSort = ($scope.sortColumn == column)? !$scope.reverseSort : false;
			$scope.sortColumn = column;
	}
	
	$scope.getSortClass = function(column) {
		if($scope.sortColumn == column) {
			return $scope.reverseSort ? 'arrow-down' : 'arrow-up';
		}
		return '';
	}
	
	//gestion des cases à cocher
	$scope.types = [];
	
	$scope.availableTypes = {
			'enCarte': 'en carte ?'
	}
});