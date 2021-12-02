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
        <title>Alta Usuario</title>
    </head>
    <body>
        <h1>Alta Usuario</h1>
        <div>${requestScope.msg}</div>
        <form action="/Practica5B/usuarios/persistUser" method="POST">
            <table border="1">
                <tr> <td><label for="nameUser">Nombre: </label></td>
                    <td><input required type="text" id="nameUser" name="nombre" /></td> </tr>
                <tr> <td><label for="emailUser">Correo: </label></td>
                    <td><input required type="email" id="emailUser" name="correo" /></td> </tr>
                <tr>
                    <td><label for="rolUser">Rol: </label></td>
                    <td>
                <c:forEach var="roles" items="${requestScope.roles}">
                    <input id="${roles.id}" type="checkbox" name="roles" value="${roles.id}" />
                    <label for="${roles.id}">${roles.nombre}</label><br />
                </c:forEach>
                </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Crear Usuario" /></td>
                </tr>
            </table>
        </form>
        <hr />
        <p><a href="/Practica5B/usuarios/show">| Inicio |</a></p>
    </body>
</html>
