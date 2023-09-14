<%-- 
    Document   : index
    Created on : Sep 11, 2023, 5:05:59 PM
    Author     : uniluk
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transferencia</title>
    </head>
    <body>
        <h1>App de transferencia de saldo de cuentas</h1>
        
        <form name="transferencia" action="${pageContext.request.contextPath}/transferencia" method="POST">
            
            <label for="cuentaOrigen">Cuenta Origen</label>
            <select id="cuentaOrigen" name="cuentaOrigen" required="true">
                
                <c:forEach items="${cuentas}" var="item">
                    <option value="${item.numeroCuenta}">${item.toString()}</option>
                </c:forEach>

            </select>
            
            <label for="cuentaDestino">Cuenta Destino</label>
            <select id="cuentaDestino" name="cuentaDestino" required="true">
                
                <c:forEach items="${cuentas}" var="item">
                    <option value="${item.numeroCuenta}">${item.toString()}</option>
                </c:forEach>
                    
            </select>
            <br>
            <input type="submit" name="transferir" value="Transferir">
        </form>
        
        
        
    </body>
</html>
