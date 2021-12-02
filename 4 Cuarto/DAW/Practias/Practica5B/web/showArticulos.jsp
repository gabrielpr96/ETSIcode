<%-- 
    Document   : VistaAccion1
    Created on : 01-dic-2021, 19:45:26
    Author     : borja
--%>

<%@page import="com.b0ve.daw.practica5b.model.Usuario"%>
<%@page import="com.b0ve.daw.practica5b.model.Permiso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Articulos</title>
    </head>
    <body>
        <h1>Articulos</h1>
        <c:choose>
            <c:when test="${!empty requestScope.articulos}">
                <table border='1'>
                    <c:forEach var="articulo" items="${requestScope.articulos}">
                        <tr>
                            <td>${articulo.nombre}</td>
                            <td>${articulo.precio}€</td>
                            <td>${articulo.usuario.nombre}</td>
                            <td><a href="/Practica5B/articulos/delete?id=${articulo.id}">Delete</a></td>
                            <td><a href="/Practica5B/articulos/edit?id=${articulo.id}">Editar</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hay articulos</p>
            </c:otherwise>
        </c:choose>

        <hr />
        <nav> |<a href="/Practica5B/"> Inicio </a>
            |<a href="/Practica5B/articulos/alta"> Nuevo articulo </a> |
        </nav>
    </body>
</html>
