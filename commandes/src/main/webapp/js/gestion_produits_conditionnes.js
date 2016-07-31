var chezYenApp = angular.module("chezYenApp", []);

chezYenApp.controller("produitsConditionnesCtrl", function($scope, $http){
	$scope.produitsConditionnes = [];
	$scope.produitConditionneModif;
	$scope.produitsConditionnes = [];
	$scope.produits = [
	       	      {designation:'black', id:0},
	       	      {designation:'white', id:1},
	       	      {designation:'red', id:2},
	       	      {designation:'blue', id:3},
	       	      {designation:'yellow', id:4}
	       	    ];
	
	$http.get('../gestProduitsConditionnes/repertoire').then (function(response) {
		$scope.produitsConditionnes = response.data.produitsConditionnes;
	});
});