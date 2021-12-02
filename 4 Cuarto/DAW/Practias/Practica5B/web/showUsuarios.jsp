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
        <title>Show Usuarios</title>
    </head>
    <body>
        <h1>Usuarios</h1>
        <c:choose>
            <c:when test="${!empty requestScope.usuarios}">
                <table border='1'>
                    <c:forEach var="usuario" items="${requestScope.usuarios}">
                        <tr>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.correo}</td>
                            <td>
                                <div>Roles:</div>
                                <ul>
                                    <c:forEach var="roles" items="${usuario.permisos}">
                                        <li>${roles.nombre}</li>
                                        </c:forEach>
                                </ul>
                            </td>
                            <td><a href="/Practica5B/usuarios/delete?id=${usuario.id}">Delete</a></td>
                            <td><a href="/Practica5B/usuarios/edit?id=${usuario.id}">Editar</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hay Usuarios</p>
            </c:otherwise>
        </c:choose>

        <hr />
        <nav> |<a href="/Practica5B/"> Inicio </a>
            |<a href="/Practica5B/usuarios/alta"> Nuevo usuario </a> |
        </nav>
    </body>
</html>
