
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
	   
	   $http.post('../gestProduits/nouveau', {
		   "produitDesignation": designation,
		   "produitPrix": prix,
		   "produitStock": stock
	   }).success(function(response){
		   $("#panelNouveauProduit").slideToggle(600);
		   //the following line must be tried 
		   //$scope.$apply();
		   $scope.produits.push(response.produit);
	   });
   };
   
   $scope.modifier_produit = function(idProduit) {
	   console.log("modifier_produit id : " + idProduit);
	   $http.post('../gestProduits/modifier',{
		   "produitID": idProduit
	   }).then(function successCallback(response){
		   $scope.produitModif = response.data.produit;
	   
		   	console.log("succes modification produit - désignation : " + $scope.produitModif.designation);
	   		
		   	openPopUp('popUpModif');
			
			return false;
	   });
   }

   $scope.valider_modification = function(id, designation, prix, stock) {
	   console.log("gestion_produit.js - valider_modification - idProduit : " + id);
	   
	   if(id != null && designation != "" && prix != "" && stock >= 0){
		   $http.post('../gestProduits/valider_modification', {
			   "produitID": id,
			   "produitDesignation": designation,
			   "produitPrix": prix,
			   "produitStock": stock
		   }).success(function(response){
			  $scope.produits = response.produits; 
		   });
	   } else {
		   console.log("produit non valide : id -> " + id + ", désignation -> " + designation + ", prix -> " + prix + ", stock -> " + stock);
	   }

	   fermeturePopUp();
   }
   
	//Fermeture de la pop-up et du fond
	$('a.close, #annulerModif, #annulerSuppr').on('click', function() { //Au clic sur le bouton ou sur le calque...
	   	fermeturePopUp();
	   	return false;
	});
});


//Ouverture popup
function openPopUp(popID){
	console.log('ouverture popUp ' + popID);
	 var largeur_fenetre = $(window).width();
	   	if(largeur_fenetre < 1500){
			var popWidth = largeur_fenetre - largeur_fenetre * 0.1; //la largeur
	   	} else {
			var popWidth = largeur_fenetre - largeur_fenetre * 0.3; //la largeur
	   	}

		//Faire apparaitre la pop-up
		$('#' + popID).fadeIn().css({
			'width': Number(popWidth)
		});

		//Récupération du margin, qui permettra de centrer la fenêtre - on ajuste de 80px en conformité avec le CSS
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;

		//On affecte le margin
		$('#' + popID).css({
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
		
		//Effet fade-in du fond opaque
		$('body').append('<div id="fade"></div>'); //Ajout du fond opaque noir
		//Apparition du fond - .css({'filter' : 'alpha(opacity=80)'}) pour corriger les bogues de IE
		$('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
		
}

function fermeturePopUp(){
	   console.log("fermeture pop-up");
	   $('#fade , .popup_block').fadeOut(function() {
	   		$('#fade, a.close').remove();  //...ils disparaissent ensemble
	   	});
}