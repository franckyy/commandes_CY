
// cette ligne crée un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var chezYenApp = angular.module("chezYenApp", []);

// Création d'un controlleur "StoreCtrl" dans notre module
// le scope est automatiquement injecté par angular
chezYenApp.controller("conditionnementCtrl", function($scope, $http) {
   $scope.conditionnements = [];
   $scope.conditionnementModif;
   
   $http.get('../gestConditionnements/repertoire').then(function (response) {
       $scope.conditionnements = response.data.conditionnements;
   });
  
   $scope.nouveau_conditionnement = function(designation, quantite){

	   console.log("nouveauConditionnement désignation : " + designation);
	   $http.post('../gestConditionnements/nouveau', {
		   	"conditionnementDesignation": designation,
		   	"conditionnementQuantite": quantite
		   }).success(function(response) {
			   $('#nouveauConditionnementDesignation').attr('value', '');
			   $("#panelNouveauConditionnement").slideToggle(600);
			   //the following line must be tried 
			   //$scope.$apply();
			   $scope.conditionnements.push(response.conditionnement);
		   }).error(function(response){
			   $scope.erreurs.push(response.erreurs);
		   });
   };
   
   $scope.valider_suppression = function(idConditionnement){
	   console.log("valider suppression conditionnement id : " + idConditionnement);

	   if(idConditionnement != null){
		   $http.post('../gestConditionnements/valider_suppression', {
			   "conditionnementID": idConditionnement
		   }).then(function successCallback(response) {	  
			   	console.log("succes suppression conditionnement");
			    $scope.conditionnements = response.data.conditionnements;
		   }, function errorCallback(response) {
			  	console.log("problème suppression conditionnement");
		   });
	   } else {

		   console.log("impossible suppression -> conditionnement null");
	   }
	   fermeturePopUp();
   };
   
   $scope.supprimer_conditionnement = function(idConditionnement) {
	   console.log("PopUpSuppression_conditionnement id : " + idConditionnement);
	   $http.post('../gestConditionnements/supprimer', {
		   "conditionnementID":idConditionnement
	   }).then(function successCallbak(response){
		   	$scope.conditionnementSuppr = response.data.conditionnement;
		   	console.log("succes suppression conditionnement - désignation : " + $scope.conditionnementSuppr.designation);
		   	
		   	openPopUp('popUpSuppr');
						
			return false;
		   	
	   }, function errorCallback(response){
		   console.log("problème modification conditionnement");
	   });	
   }
   
   $scope.modifier_conditionnement = function(idConditionnement) {
	   console.log("modifier_conditionnement id : " + idConditionnement);
	   $http.post('../gestConditionnements/modifier', {
		   "conditionnementID":idConditionnement
	   }).then(function successCallback(response){
		   	$scope.conditionnementModif = response.data.conditionnement;
		   	console.log("succes modification conditionnement - désignation : " + $scope.conditionnementModif.designation);
		   	
		   	openPopUp('popUpModif');
			
			return false;
		   	
	   }, function errorCallback(response){
		   console.log("problème modification conditionnement");
	   });
   }
   
   $scope.valider_modification = function(id, designation, quantite) {
	   console.log("gestion_conditionnements - Valider modification - id : " + id);
	 //vérification id différent de null ou nom différent de vide ou prénom différent de vide
	   if(id != null && designation != "" && quantite != ""){ 
		   $http.post('../gestConditionnements/valider_modification', {
			   	"conditionnementID": id,
			   	"conditionnementDesignation": designation,
			   	"conditionnementQuantite": quantite
			   }).success(function(response) {
				   $scope.conditionnements = response.conditionnements;
			   }).error(function(response){
				   $scope.erreurs.push(response.erreurs);
			   });
	   } else {
		   console.log("impossible modification -> conditionnement null");
	   }
	   fermeturePopUp();
   }
   
 //Fermeture de la pop-up et du fond
 $('a.close, #annulerModif, #annulerSuppr').on('click', function() { //Au clic sur le bouton ou sur le calque...
    	fermeturePopUp();
    	return false;
 });
  
// $scope.addNewTask2 = function(libelle, category) {
//    j'envoie une requette POST pour demander au serveur de creer la tache en BDD
//   $http.post('../rest/savetache2', {"tacheLibelle": libelle, "tacheCategory" : category} 
//    ).success(function(response) {
//         cette fonction est rappellée quand le serveur renvoie sa réponse
//         le serveur nous renvoie la tache insérée sous format JSON
//         angular désérialise le json dans response.data
//         nous ajoutons la tache insérée dans le tableau des taches du scope
//       $scope.taches.push(response.tache);
//    }); 
// };

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

//$scope.incompleteCount = function() {
//var count = 0;
//angular.forEach($scope.produits, function(t) {
//  if (!t.completed) {
//      count++;
//  }  
//});
//return count;
//};

//$scope.warningLevel = function() {
	//if ($scope.incompleteCount() < 3 ) {
	//   return "label-success";  
	//}
	//else {
	//   return "label-warning";
	//}
//};

// filter prend en parametre le nom du filtre, et la fonction
// "factory" fabrique de la fonction filtre
//chezYenApp.filter("completedTask", function() {
//    // 2 parametres , la collection sur laquelle on travaille
//    // un parametrage du filtre (ici un boolean pour determiner le filtrage)
//   return function (items, showComplete) {
//       var resultItems = [];
//       angular.forEach(items, function(item) {
//          if (item.completed == false || showComplete == true) {
//              resultItems.push(item);
//          }
//       });
//       return resultItems;
//   };
//});