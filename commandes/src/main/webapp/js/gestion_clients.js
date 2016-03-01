
// cette ligne crée un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var chezYenApp = angular.module("chezYenApp", []);

// Création d'un controlleur "StoreCtrl" dans notre module
// le scope est automatiquement injecté par angular
chezYenApp.controller("clientCtrl", function($scope, $http) {
   $scope.clients = [];
   $http.get('../gestClients/repertoire').then(function (response) {
       $scope.clients = response.data.clients;
   });
  
   $scope.nouveau_client = function(nom, prenom, numVoie, typeVoie, nomVoie, codePostal, ville){
	   $http.post('../gestClients/nouveau', {
		   	"client_nom": nom,
		   	"client_prenom": prenom,
		   	"client_numVoie": numVoie,
		   	"client_typeVoie": typeVoie,
		   	"client_nomVoie": nomVoie,
		   	"client_codePostal": codePostal,
		   	"client_ville": ville
		   }).success(function(response) {
			   $scope.clients.push(response.client);
	   });
   };
//   $scope.incompleteCount = function() {
//     var count = 0;
//     angular.forEach($scope.produits, function(t) {
//       if (!t.completed) {
//           count++;
//       }  
//     });
//     return count;
//   };
   
//   $scope.warningLevel = function() {
//     if ($scope.incompleteCount() < 3 ) {
//        return "label-success";  
//     }
//     else {
//        return "label-warning";
//     }
//   };
   

// $scope.addNewTask2 = function(libelle, category) {
//   // j'envoie une requette POST pour demander au serveur de creer la tache en BDD
//   $http.post('../rest/savetache2', {"tacheLibelle": libelle, "tacheCategory" : category} 
//    ).success(function(response) {
//        // cette fonction est rappellée quand le serveur renvoie sa réponse
//        // le serveur nous renvoie la tache insérée sous format JSON
//        // angular désérialise le json dans response.data
//        // nous ajoutons la tache insérée dans le tableau des taches du scope
//       $scope.taches.push(response.tache);
//    }); 
// };
   
   // appelé quand le formulaire est soumis (click bouton)
   // on recoit le libelle de la tache a cree, et sa categorie
//   $scope.nouveauClient = function(nom, prenom, adresse) {
//     // j'envoie une requette POST pour demander au serveur de creer la tache en BDD
//     $http.post('../rest/saveclient/' + nom + '/' + prenom + '/' + adresse, /* ce client sera créé avec les parametres ci dessous*/ 
//      {}).success(function(response) {
//          // cette fonction est rappellée quand le serveur renvoie sa réponse
//          // le serveur nous renvoie le client inséré sous format JSON
//          // angular désérialisé le json dans response.data
//          // nous ajoutons le client inséré dans le tableau des clients du scope
//         $scope.clients.push(response.client);
//      }); 
//   };
//   
//   $scope.deleteClient = function(id) {
//       console.log("delete client no " + id);
//   };



   
   /*
   $scope.addNewTask = function(libelle, category) {
     var tid =   $scope.taches.length + 1;
     var tdate = "" + new Date();
     $scope.taches.push({
       "libelle" : libelle,
       "category" : category,
       "completed" : false,
       "id" : tid,
       "dateCreated" : tdate 
     });
   };
   */
});

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