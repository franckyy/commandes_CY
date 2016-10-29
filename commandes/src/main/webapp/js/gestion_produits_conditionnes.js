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
	
	$scope.nouveauProduitConditionne = function(myProduit,myConditionnement,conditionnement_prix){
		console.log("myProduit : " + myProduit.idProduit + ", myConditi : " + myConditionnement.idConditionnement + ", prix condi : " + conditionnement_prix + ", in carte ? " + $scope.types);
		
		$http.post('../gestProduitsConditionnes/nouveau', {
			"produitID":myProduit.idProduit,
			"conditionnementID":myConditionnement.idConditionnement,
			"produitConditionnePrix":conditionnement_prix
		}).success(function(response) {
			$('#panelNouveauProduitConditionne').slideToggle(600);
			console.log("prodCond a pousser : " + response.produitConditionne);
			if(response.produitConditionne != null) {
				$scope.produitsConditionnes.push(response.produitConditionne);
				console.log("produit conditionné sauvegardé.")
			} else {
				console.log("pas de produit conditionné sauvegardé car il existe peut-être déjà.")
			}
		});
	}
	
	$scope.modifier_produit_conditionne = function(idProduitConditionne) {
		console.log("modifier prodCond id : " + idProduitConditionne);
		$http.post('../gestProduitsConditionnes/modification', {
			"produitConditionneID":idProduitConditionne
		}).then(function successCallback(response){
			console.log('popup modif');
			openPopUp('popUpModif');
		})
	}
	
	//gestion des cases à cocher
	$scope.types = [];
	
	$scope.availableTypes = {
			'enCarte': 'en carte ?'
	}
});


//Ouverture PopUp
function openPopUp(popID) {
	console.log('ouverture popup ' + popID);
	var largeur_fenetre = $(window).width();
	if(largeur_fenetre < 1500) {
		var popWidth = largeur_fenetre - largeur_fenetre * 0.1; // la largeur
	} else {
		var popWidth = largeur_fenetre - largeur_fenetre * 0.3; //la largeur
	}
	
	//faire apparaitre la popup
	$('#' + popID).fadeIn().css({
		'width': Number(popWidth)
	});
	
	//Récupération du margin, qui permettra de centrer la fenetre - on ajuste de 80px en conformité avec le CSS
	var popMargTop = ($('#' + popID).height() + 80) / 2;
	var popMargLeft = ($('#' + popID).width() + 80) / 2;
	
	//on affecte le margin
	$('#' + popID).css({
		'margin-top': -popMargTop,
		'margin-left': -popMargLeft
	});
	
	//Effet fade-In du fond opaque
	$('body').append('<div id="fade"></div>'); // ajout du fond opaque noir
	//Apparition du fond - .css({'filter' : 'alpha(opacity=80)'}) pour corriger les bogues de IE
	$('fade').css({
		'filter': 'alpha(opacity=80)'
	}).fadeIn();
}

function fermeturePopUp() {
	console.log("fermeture de la popUp");
	
	$('fade, .popup_Block').fadeOut(function() {
		$('#fade, a.close').remove(); //...ils disparaissent ensemble
	});
}