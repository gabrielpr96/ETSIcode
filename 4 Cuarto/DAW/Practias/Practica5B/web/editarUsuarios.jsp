<%-- 
    Document   : VistaAccion1
    Created on : 01-dic-2021, 19:45:26
    Author     : borja
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.b0ve.daw.practica5b.model.Usuario"%>
<%@page import="com.b0ve.daw.practica5b.model.Permiso"%>
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
        <form action="/Practica5B/usuarios/merge" method="POST">
            <table border="1">
                <tr> <td><label for="nameUser">Nombre: </label></td>
                    <td><input required type="text" id="nameUser" name="nombre" value="${usuario.nombre}" /></td> </tr>
                <tr> <td><label for="emailUser">Correo: </label></td>
                    <td><input required type="email" id="emailUser" name="correo" value="${usuario.correo}" /></td> </tr>
                <tr>
                    <td><label for="rolUser">Rol: </label></td>
                    <td>
                        <%
                            List<Permiso> permisos = (List<Permiso>) request.getAttribute("roles");
                            for (Permiso permiso : permisos) {
                        %>
                        <input id="<%= permiso.getId()%>" type="checkbox" name="roles" value="<%= permiso.getId()%>" <%= (((Usuario) request.getAttribute("usuario")).getPermisos().contains(permiso)) ? "checked" : ""%> />
                        <label for="<%= permiso.getId()%>"><%= permiso.getNombre() %></label><br />
                        <%
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td></td>
                <input type="hidden" name="id" value="${usuario.id}"/>
                <td><input type="submit" value="Editar Usuario" /></td>
                </tr>
            </table>
        </form>
        <hr />
        <p><a href="/Practica5B/usuarios/show">| Inicio |</a></p>
    </body>
</html>
