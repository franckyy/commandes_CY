angular.module("storeDirectives", [])
        .directive("productsCount", function(){
           return {
             restrict: "E",
             templateUrl: "components/productCount.html",
             controller: function($scope) {
                 $scope.testvalue = "bonjour";
                 
                 //console.log("demarrage scope directive");
                 $scope.inStockProducts = function() {
                     var count = 0;
                     angular.forEach($scope.produits, function(p) {
                         if (p.stock > 0) {
                             count++;
                         }
                     });
                     return count;
                 }
             }
           }; 
        });