<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Saludado</title>
    </head>
    <body>
        <h1>Hola <%= request.getParameter("nombre")%> </h1>
        <p><a href="index.html">Volver...</a></p>
    </body>
</html>
