<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
    <h3>Tus intereses:</h3>
    <div class="d-flex flex-row flex-wrap justify-content-center mb-3" id="articulosDiv"></div>
    <script>
        window.addEventListener("load", () => {
            cargarIntereses();
        });

        function cargarIntereses() {
            console.log("cargar");
            const articulosDiv = document.getElementById("articulosDiv");
            removeAllChilds(articulosDiv);
            listarIntereses()
                    .then(msg => {
                        msg.articulos === undefined ?
                                articulosDiv.appendChild(newAlert("No tienes articulos guardados, pulsa en el corazón para guardar los articulos que te interesen", "warning")) :
                                msg.articulos.forEach(articulo => articulosDiv.appendChild(newArticuloCard(articulo)));
                        //[...document.getElementsByClassName("btn-favver")].forEach(btn => btn.addEventListener("click", cargarIntereses));
                    }).catch(generalUnespectedError);
        }
    </script>
</t:main>
