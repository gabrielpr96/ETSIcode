<%-- 
    Document   : VistaAccion1
    Created on : 01-dic-2021, 19:45:26
    Author     : borja
--%>

<%@page import="com.b0ve.daw.practica5b.model.Permiso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show roles</title>
    </head>
    <body>
        <h1>Roles</h1>
        <c:choose>
            <c:when test="${!empty requestScope.roles}">
                <table border='1'>
                    <c:forEach var="roles" items="${requestScope.roles}">
                        <tr>
                            <td>${roles.nombre}</td>
                            <td><a href="/Practica5B/roles/delete?id=${roles.id}">Delete</a></td>
                            <td><a href="/Practica5B/roles/edit?id=${roles.id}">Editar</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hay Roles</p>
            </c:otherwise>
        </c:choose>
        
        <hr />
        <nav> |<a href="/Practica5B/"> Inicio </a>
            |<a href="/Practica5B/roles/altaRol"> Nuevo Rol </a> |
        </nav>
    </body>
</html>
