<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello</title>
</head>
<body>

 <div class="container theme-showcase" role="main">
        <div class="col-md-2" >
            <button id="bouton1" type="button" class="btn btn-primary" ng-click="navigateTo('views/editProduct.html')">Creer Produit</button><br />
            <button id="bouton2" type="button" class="btn btn-primary" ng-click="navigateTo('views/productsList.html')">Liste Produit</button><br />
        </div>
        <div class="col-md-10" ng-include="currentView"  >
        <h2><s:property value="message" /> </h2>
        </div>
       <!-- <ng-include src="'views/productsList2.html'"></ng-include> -->
 </div>
</body>
</html>