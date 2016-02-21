<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bonjour</title>
        <link  type="text/css" rel="stylesheet" href="css/bootstrap.css" />
        <link  type="text/css" rel="stylesheet" href="css/bootstrap-theme" />        
       <link  type="text/css" rel="stylesheet" href="css/chezYenApp.css" />
        <script type="text/javascript" src="js/jquery-2.2.0.js">
        </script>
        <script type="text/javascript" src="js/angular.js">
        </script>      
        <script type="text/javascript" src="js/angular-route.js">
        </script>              
        <script type="text/javascript" src="js/angularapp.js">
        </script>
        <script type="text/javascript" src="js/productController.js">
        </script>
        <script type="text/javascript" src="js/chezYenDirective.js">
        </script>
</head>
<body>

 <div class="container theme-showcase" role="main">
 	<!DOCTYPE html>
<html lang="en">
<head>
<title>Chez Yen</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
	.navbar{
		margin-top: 20px;
	}
</style>
</head> 
<body>
<div class="container">

    <%@include file="commons/navbar.html" %>
    
    
        <div class="row">
	        <div class="col-sm-2" >
	            <button id="boutonCommandes" type="button" class="btn btn-primary" onclick="location.href='views/gestionCommandes.html'">Gestion des commandes</button><br />
	            <button id="boutonProduits" type="button" class="btn btn-primary" onclick="location.href='views/gestionProduitss.html'">Gestion des produits</button><br />
	            <button id="boutonClients" type="button" class="btn btn-primary" onclick="location.href='views/gestionClients.html'">Gestion des clients</button><br />
	        </div>
	        <div class="col-sm-2" >
			</div>
	        <div class="col-sm-8" ng-include="currentView"  >
	        	<h2><s:property value="message" /> </h2>
	        </div>
        </div>
       <!-- <ng-include src="'views/productsList2.html'"></ng-include> -->
 </div>
</body>
</html>