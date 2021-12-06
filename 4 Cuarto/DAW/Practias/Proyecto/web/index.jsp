<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
    <h1>Tienda de segunda mano</h1>
    <h3>Articulos recientes:</h3>
    <div class="d-flex flex-row flex-wrap justify-content-center mb-3" id="articulosDiv"></div>
    <div class="d-flex justify-content-center">
        <button type="button" class="btn btn-outline-primary" onclick="location.href='/Proyecto/articulos'">Ver todos los articulos</button>
    </div>
    <script>
        window.addEventListener("load", () => {
            const articulosDiv = document.getElementById("articulosDiv");
            listarArticulos()
                    .then(msg => msg.articulos.forEach(articulo => articulosDiv.appendChild(newArticuloCard(articulo)))
                    ).catch(generalUnespectedError);
        });
    </script>
</t:main>
