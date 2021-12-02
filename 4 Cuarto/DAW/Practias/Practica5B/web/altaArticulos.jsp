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
        <title>Alta articulos</title>
    </head>
    <body>
        <h1>Nuevo Articulo</h1>
        <form action="/Practica5B/articulos/persist" method="POST">
            <label for="nArticulo">Nombre: </label>
            <input type="text" id="nArticulo" name="nombre" /> <br />
            <label for="nPrecio">Precio: </label>
            <input type="text" id="nPrecio" name="precio" /> <br />
            <select name="usuario">
                <c:forEach var="usuario" items="${requestScope.usuarios}">
                    <option value="${usuario.id}">${usuario.nombre}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Crear articulo" />
        </form>

        <hr />
        <nav> |<a href="/Practica5B/articulos/show"> Inicio </a> |
        </nav>
    </body>
</html>
