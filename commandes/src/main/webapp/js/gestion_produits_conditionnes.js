var chezYenApp = angular.module("chezYenApp", []);

chezYenApp.controller("produitsConditionnesCtrl", function($scope, $http){
	$scope.produitsConditionnes = [];
	$scope.produitConditionneModif;
	
	$http.get('../gestProduitsConditionnes/repertoire').then (function(response) {
		$scope.produitsConditionnes = response.data.produitsConditionnes;
	});
});