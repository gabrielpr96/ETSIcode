<%-- 
    Document   : VistaAccion1
    Created on : 01-dic-2021, 19:45:26
    Author     : borja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta roles</title>
    </head>
    <body>
        <h1>Nuevo Rol</h1>
        <form action="/Practica5B/roles/persistRol" method="POST">
            <label for="nRol">Rol: </label>
            <input type="text" id="nRol" name="nombre" /> <br />
            <input type="submit" value="Añadir Rol" />
        </form>

        <hr />
        <nav> |<a href="/Practica5B/roles/show"> Inicio </a> |
        </nav>
    </body>
</html>
