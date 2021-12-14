<%@page import="com.b0ve.daw.proyecto.model.Articulo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:main>
    <h1>Articulo</h1>
    <div class="row">
        <div class="col-12 col-lg-6">
            <img src="${imagen64}" style="width:100%;"/>
        </div>
        <div class="col-12 col-lg-6">
            <h2>${articulo.nombre}</h2>
            <c:if test="${articulo.descripcion != null}">
                <h5 class="mt-3">Descripcion: </h5>
                <p>${articulo.descripcion}</p>
            </c:if>
            <c:if test="${articulo.estado != null}">
                <h5 class="mt-3">Estado: ${articulo.estado}</h5>
            </c:if>
            <h5 class="mt-3">Categoria: ${articulo.categoria.nombre}</h5>
            <h5 class="mt-3">Precio: ${articulo.precio}€</h5>
            <h5 class="mt-3">Usuario: <a href="#" onclick="mostrarUsuario('${articulo.usuario.id}')">${articulo.usuario.nombre}</a></h5>
            <div id="buttonsDiv"></div>
        </div>
    </div>
    <div class="row mt-3">
        <h2>Comentarios (${fn:length(comentarios)}):</h2>
        <div id="comentarios" class="mt-2">
            <c:forEach var="comentario" items="${comentarios}">
                <div class="mt-2 border-dark bg-light p-3 rounded-3">
                    <strong>${comentario.usuario.nombre}</strong>
                    <p>${comentario.texto}</p>
                </div>
            </c:forEach>
        </div>
        <div class="mt-2 border-dark bg-light p-3 rounded-3">
            <strong>Publicar comenratio</strong>
            <div class="mt-2">
                <textarea class="form-control mb-3" id="comentario"></textarea>
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <label for="visibilidad" class="form-label">Visibilidad</label>
                        <select class="form-select" id="visibilidad">
                            <c:forEach var="visibilidad" items="${visibilidades}">
                                <option>${visibilidad}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-12 col-lg-6 d-flex align-items-start justify-content-end">
                        <button type="button" class="btn btn-primary" id="comentarBtn">Comentar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        window.addEventListener("load", () => {
            const buttonsDiv = document.getElementById("buttonsDiv");
            buttonsDiv.appendChild(newFavBtn("${articulo.id}", true));
            buttonsDiv.appendChild(newCarritoBtn(${articulo.id}, true));
            [...buttonsDiv.getElementsByTagName("button")].forEach(btn => btn.classList.add("m-2"));

            const comentarBtn = document.getElementById("comentarBtn");
            comentarBtn.addEventListener("click", () => {
                let articuloId = "${articulo.id}";
                let texto = document.getElementById("comentario").value;
                let visibilidad = document.getElementById("visibilidad").value;
                comentar(articuloId, texto, visibilidad)
                        .then(() => location.href = "/Proyecto/articulo/" + articuloId)
                        .catch(generalUnespectedError);
            });
        });
    </script>
</t:main>
