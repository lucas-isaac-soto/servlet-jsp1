<%--
    Document   : error.jsp
    Created on : Sep 14, 2023, 4:46:12 PM
    Author     : uniluk
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>PAGINA DE ERRORES</h1>
        <p>A continuacion se detallaran los errores en su operacion: </p>

        <ul>
            <c:forEach items="${errorCuentas}" var="item">
                <li>${item.getMessage()}</li>
            </c:forEach>
            
            <c:if test="${not empty errorFormato}">
                <li>${errorFormato.getMessage()}</li>
            </c:if>
                
            <c:if test="${not empty errorSaldo}">
                <li>${errorSaldo.getMessage()}</li>
            </c:if>
        </ul>
        
        <a href="${pageContext.request.contextPath}/">volver a inicio</a>   
    </body>
</html>
