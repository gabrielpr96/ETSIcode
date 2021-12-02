<%-- 
    Document   : VistaAccion1
    Created on : 01-dic-2021, 19:45:26
    Author     : borja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Roles</title>
    </head>
    <body>
        <h1>Editar Rol</h1>
        <form action="/Practica5B/roles/mergeRol" method="POST">
            <label for="nRol">Rol: </label>
            <input type="text" id="nRol" name="nombre" value="${rol.nombre}" /> <br />
            <input type="hidden" name="id" value="${rol.id}" />
            <input type="submit" value="Editar Rol" />
        </form>

        <hr />
        <nav> |<a href="/Practica5B/roles/show"> Inicio </a> |
        </nav>
    </body>
</html>
