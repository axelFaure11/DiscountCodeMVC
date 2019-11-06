<%-- 
    Document   : vue
    Created on : 5 nov. 2019, 13:30:07
    Author     : pedago
--%>
<!– JSP --!>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
				.table
				{
					display:table;
					border-collapse:separate;
					border-spacing:2px;
				}
				.thead
				{
					display:table-header-group;
					color:white;
					font-weight:bold;
					background-color:grey;
				}
				.tbody
				{
					display:table-row-group;
				}
				.tr
				{
					display:table-row;
				}
				.td
				{
					display:table-cell;
					border:1px solid black;
					padding:1px;
				}			
		</style>		
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    <body>
        <h1>Edition des taux de remise</h1>
        <form method='GET'>
            Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES"><br/>
            Taux : <input name="rate" type="number" step="0.01" min="0.0" max="99.99" size="5"><br/>
            <input type="hidden" name="action" value="ADD">
            <input type="submit" value="Ajouter">
	</form>
        
        <table><div class="table">
			<div class="thead"><div class="td">Code</div><div class="td">Taux</div><div class="td">Action</div></div>
			<div class="tbody">
                            <c:set var="listDiscountCode" value="${discount_code_table}"/>
                            <c:forEach var="entry" items="${listDiscountCode}">
				<form class="tr" method="get">
					<div class="td"><input type="text" name="code" value="${entry.code}" readonly/></div>
					<div class="td"><input name="rate" type="number" step="0.01" min="0.0" max="99.99" size="5" value="${entry.rate}"/></div>
					<div class="td"><input type="submit" name="action" value="DELETE"/></div>
				</form>	  		    
                            </c:forEach>
			</div>
		</div>
        
    </body>
</html>
