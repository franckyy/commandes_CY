
// cette ligne crée un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var chezYenApp = angular.module("chezYenApp", []);

// Création d'un controlleur "StoreCtrl" dans notre module
// le scope est automatiquement injecté par angular
chezYenApp.controller("clientCtrl", function($scope, $http) {
   $scope.clients = [];
   $scope.clientModif;
   
   $http.get('../gestClients/repertoire').then(function (response) {
       $scope.clients = response.data.clients;
   });
  
   $scope.nouveau_client = function(nom, prenom, email, tel, numVoie, typeVoie, nomVoie, codePostal, ville){

	   console.log("nouveauClient nom : " + nom);
	   $http.post('../gestClients/nouveau', {
		   	"clientNom": nom,
		   	"clientPrenom": prenom,
		   	"clientEmail": email,
		   	"clientTelephone": tel,
		   	"clientNumeroVoie": numVoie,
		   	"clientTypeVoie": typeVoie,
		   	"clientNomVoie": nomVoie,
		   	"clientCodePostal": codePostal,
		   	"clientVille": ville
		   }).success(function(response) {
			   $('#nouveauClientNom').attr('value', '');
			   $("#panelNouveauClient").slideToggle(600);
			   //the following line must be tried 
			   //$scope.$apply();
			   $scope.clients.push(response.client);
		   }).error(function(response){
			   $scope.erreurs.push(response.erreurs);
		   });
   };
   
   $scope.valider_suppression = function(idClient){
	   console.log("valider suppression client id : " + idClient);

	   if(idClient != null){
		   $http.post('../gestClients/valider_suppression', {
			   "clientID": idClient
		   }).then(function successCallback(response) {	  
			   	console.log("succes suppression client");
			    $scope.clients = response.data.clients;
		   }, function errorCallback(response) {
			  	console.log("problème suppression client");
		   });
	   } else {

		   console.log("impossible suppression -> client null");
	   }
	   fermeturePopUp();
   };
   
   $scope.supprimer_client = function(idClient) {
	   console.log("PopUpSuppression_client id : " + idClient);
	   $http.post('../gestClients/supprimer', {
		   "clientID":idClient
	   }).then(function successCallbak(response){
		   	$scope.clientSuppr = response.data.client;
		   	console.log("succes suppression client - nom : " + $scope.clientSuppr.nom);
		   	
		   	openPopUp('popUpSuppr');
						
			return false;
		   	
	   }, function errorCallback(response){
		   console.log("problème modification client");
	   });	
   }
   
   $scope.modifier_client = function(idClient) {
	   console.log("modifier_client id : " + idClient);
	   $http.post('../gestClients/modifier', {
		   "clientID":idClient
	   }).then(function successCallback(response){
		   	$scope.clientModif = response.data.client;
		   	console.log("succes modification client - nom : " + $scope.clientModif.nom);
		   	
		   	openPopUp('popUpModif');
			
			return false;
		   	
	   }, function errorCallback(response){
		   console.log("problème modification client");
	   });
   }
   
   $scope.valider_modification = function(id, nom, prenom, email, telephone, numVoie, typeVoie, nomVoie, codePostal, ville) {
	   console.log("gestion_clients - Valider modification - id : " + id);
	 //vérification id différent de null ou nom différent de vide ou prénom différent de vide
	   if(id != null && nom != "" && prenom != ""){ 
		   $http.post('../gestClients/valider_modification', {
			   	"clientID": id,
			   	"clientNom": nom,
			   	"clientPrenom": prenom,
			   	"clientEmail": email,
			   	"clientTelephone": telephone,
			   	"clientNumeroVoie": numVoie,
			   	"clientTypeVoie": typeVoie,
			   	"clientNomVoie": nomVoie,
			   	"clientCodePostal": codePostal,
			   	"clientVille": ville
			   }).success(function(response) {
				   $scope.clients = response.clients;
			   }).error(function(response){
				   $scope.erreurs.push(response.erreurs);
			   });
	   } else {
		   console.log("impossible modification -> client null");
	   }
	   fermeturePopUp();
   }
   
   $scope.sortColumn = "nom";
   $scope.reverseSort = false;  

   $scope.sortData = function (column) {  
	   $scope.reverseSort = ($scope.sortColumn==column) ? !$scope.reverseSort :false;  
	   $scope.sortColumn = column;   
   }  

   $scope.getSortClass = function (column)  {  
	   if ($scope.sortColumn == column) {  
		   return $scope.reverseSort ? 'arrow-down' : 'arrow-up';
	   }  
	   
	   return "";
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