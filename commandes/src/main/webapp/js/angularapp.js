
// cette ligne crée un nouveau module angular
// le tableau en deuxieme argument contient les
// dépendances a injecter dans ce module
var todoApp = angular.module("storeApp", []);

// Création d'un controlleur "StoreCtrl" dans notre module
// le scope est automatiquement injecté par angular
todoApp.controller("StoreCtrl", function($scope, $http) {
   $scope.produits = [];
   $scope.commandes = [];
   $scope.clients = [];
   
   $http.get('../gestComm/produits').then(function (response) {
       $scope.produits = response.data.produits;
   });
 
   $http.get('../gestComm/commandes').then(function (response) {
       $scope.commandes = response.data.commandes;
   });
   
   $http.get('../gestComm/clients').then(function (response) {
       $scope.clients = response.data.clients;
   });
//   $scope.produits = [
//        {   
//            "id":1,
//            "designation":"Poulet curry",
//            "prix":5.90,
//            "stock":8},
//        {   "id":2,
//            "designation":"Crabe",
//            "prix":2.00,
//            "stock":10}
//       ];
   
   $scope.incompleteCount = function() {
     var count = 0;
     angular.forEach($scope.produits, function(t) {
       if (!t.completed) {
           count++;
       }  
     });
     return count;
   };
   
   $scope.warningLevel = function() {
     if ($scope.incompleteCount() < 3 ) {
        return "label-success";  
     }
     else {
        return "label-warning";
     }
   };
   // appelé quand le formulaire est soumis (click bouton)
   // on recoit le libelle de la tache a cree, et sa categorie
   $scope.addNewTask = function(libelle, category) {
     // j'envoie une requette POST pour demander au serveur de creer la tache en BDD
     $http.post('../rest/savetache/' + libelle + '/' + category, /* cette tache sera cree avec les parametres ci dessous*/ 
      {}).success(function(response) {
          // cette fonction est rappellée quand le serveur renvoie sa réponse
          // le serveur nous renvoie la tache inséré sous format JSON
          // angular deserialise le json dans response.data
          // nous ajoutons la tache insérée dans le tableau des taches du scope
         $scope.taches.push(response.tache);
      }); 
   };
   $scope.deleteTask = function(id) {
       console.log("delete taks no " + id);
   };

   $scope.addNewTask2 = function(libelle, category) {
     // j'envoie une requette POST pour demander au serveur de creer la tache en BDD
     $http.post('../rest/savetache2', {"tacheLibelle": libelle, "tacheCategory" : category} 
      ).success(function(response) {
          // cette fonction est rappellée quand le serveur renvoie sa réponse
          // le serveur nous renvoie la tache inséré sous format JSON
          // angular deserialise le json dans response.data
          // nous ajoutons la tache insérée dans le tableau des taches du scope
         $scope.taches.push(response.tache);
      }); 
   };


   
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
StoreApp.filter("completedTask", function() {
    // 2 parametre , la collection sur laquelle on travaille
    // un parametrage du filtre (ici un bolean pour determiner le filtrage)
   return function (items, showComplete) {
       var resultItems = [];
       angular.forEach(items, function(item) {
          if (item.completed == false || showComplete == true) {
              resultItems.push(item);
          }
       });
       return resultItems;
   };
});