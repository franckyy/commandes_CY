
// creation d'un module regroupant les fonctionnalités produit
angular.module("produitsModule", [])
       .controller("editCtrl", function($scope, $http) { //mise en place d'une controlleur pour l'edition
            // fonction d'ajout d'un produit depuis le formulaire
           $scope.addProduct = function (produit) {
                console.log(produit.designation + " - " + produit.prix);
                // requette post vers le serveur avec transimission d'un objet json
                // contenant les détails de notre produit
                // cet objet json sera désérialisé par l'intercepteur json de
                // struts2 dans le cas présent
                $http.post('gestComm/produitSave',
                  {
                      "produitDesignation" : produit.designation,
                      "produitPrix" : produit.prix,
                      "produitStock" : produit.stock,
                      "produitID" : produit.ID
                  }
                 ).then(function(response){
                     // dans la réponse, le serveur nous renvoie
                     // le produit inséré, nous l'ajoutons dans
                     // la liste des produits
                     // remarquons que nous pouvons acceder
                     // au $scope du controlleur parent (storeCtrl)
                     // grace a l'héritage de $scope d'angular
                    $scope.produits.push(response.data.produit);
                    // on retourne sur la vue liste des produits 
                    $scope.navigateTo('views/productsList.html');
                });

            };
       });