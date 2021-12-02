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
        <title>Editar Articulos</title>
    </head>
    <body>
        <h1>Editar Articulo</h1>
        <form action="/Practica5B/articulos/merge" method="POST">
            <label for="nArticulo">Nombre: </label>
            <input type="text" id="nArticulo" name="nombre" value="${articulo.nombre}" /> <br />
            <label for="nPrecio">Precio: </label>
            <input type="text" id="nPrecio" name="precio" value="${articulo.precio}" /> <br />
            <input type="hidden" name="id" value="${articulo.id}" />
            <label for="idUsuario">Usuario:</label>
            <input type="submit" value="Editar articulo" />
        </form>

        <hr />
        <nav> |<a href="/Practica5B/articulos/show"> Inicio </a> |
        </nav>
    </body>
</html>
