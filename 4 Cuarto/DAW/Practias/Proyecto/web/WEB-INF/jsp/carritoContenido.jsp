<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table style="width: 100%">
    <c:forEach var="articulo" items="${carrito}" varStatus="loop">
        <tr>
            <td>
                <img src="${base64Images[loop.index]}" style="height: 5rem;width: 5rem;">
            </td>
            <td>
                <span>${articulo.nombre}</span>
            </td>
            <td>
                <span>${articulo.precio}€</span>
            </td>
            <td>
                <button type="button" class="btn btn-outline-danger" onclick="removeCarritoByIndex(${loop.index});this.parentElement.parentElement.parentElement.removeChild(this.parentElement.parentElement)"><i class="fas fa-trash"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>
