<%-- 
    Document   : transferencia
    Created on : Sep 12, 2023, 3:38:00 PM
    Author     : uniluk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transferencia Realizada </title>
    </head>
    <body>
        <h1>Hello Transferencia!</h1>
        <p>A ${origen} ${cuentaOrigen}</p>
        <p>B ${destino} ${cuentaDestino}</p>


        <a href="${pageContext.request.contextPath}/">volver porfavor</a>        
    </body>
</html>
