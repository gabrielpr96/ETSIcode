<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:main>
    <style>
        #imagenCambiable{
            width:100%;
            object-fit: cover;
            transition: transform .2s;
        }
        #imagenCambiable:hover{
            transform: scale(1.1);
        }
    </style>
    <h1>Publicar un artículo</h1>
    <div class="row">
        <div class="col-12 col-lg-6">
            <img src="img/placeholder.jpg" id="imagenCambiable"/>
        </div>
        <div class="col-12 col-lg-6">
            <form>
                <div class="mb-3">
                    <label for="nombre" class="form-label"><i class="fas fa-asterisk me-2"></i>Nombre</label>
                    <input type="text" class="form-control" id="nombre">
                    <div class="form-text">Corto, descriptivo y que llame la atención.</div>
                </div>
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" id="descripcion"></textarea>
                </div>
                <div class="mb-3">
                    <label for="precio" class="form-label"><i class="fas fa-asterisk me-2"></i>Precio</label>
                    <input type="text" class="form-control" id="precio">
                    <div class="form-text">No te pases.</div>
                </div>
                <div class="mb-3">
                    <label for="ano" class="form-label">Año de adquisición</label>
                    <input type="text" class="form-control" id="ano">
                </div>
                <div class="mb-3">
                    <label for="categoria" class="form-label"><i class="fas fa-asterisk me-2"></i>Categoria</label>
                    <select class="form-select" id="categoria">
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.id}">${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado</label>
                    <select class="form-select" id="estado">
                        <option value="null">Sin especificar</option>
                        <c:forEach var="estado" items="${estados}">
                            <option>${estado}</option>
                        </c:forEach>
                    </select>
                </div>
            </form>
            <button type="submit" id="enviarBtn" class="btn btn-lg btn-primary"><i class="fas fa-upload me-2"></i>Subir</button>
        </div>
        <div class="row mt-3" id="errorsDiv"></div>
    </div>
    <input type="file" accept=".jpg, .png |image/*" class="visually-hidden" id="imagenInput" />
    <script>
        window.addEventListener("load", () => {
            const imagenCambiable = document.getElementById("imagenCambiable");
            imagenCambiable.addEventListener("click", () => {
                imagenInput.click();
            });
            const imagenInput = document.getElementById("imagenInput");
            imagenInput.addEventListener("change", () => {
                if (imagenInput.files.length > 0) {
                    let reader = new FileReader();
                    reader.onloadend = () => {
                        imagenCambiable.height = imagenCambiable.width;
                        imagenCambiable.src = reader.result;
                    };
                    reader.readAsDataURL(imagenInput.files[0]);
                }
            });

            const errorsDiv = document.getElementById("errorsDiv");
            const enviarBtn = document.getElementById("enviarBtn");
            enviarBtn.addEventListener("click", () => {
                const nombre = document.getElementById("nombre");
                const descripcion = document.getElementById("descripcion");
                const precio = document.getElementById("precio");
                const ano = document.getElementById("ano");
                const categoria = document.getElementById("categoria");
                const estado = document.getElementById("estado");

                removeAllChilds(errorsDiv);
                nombre.classList.remove("is-invalid");
                descripcion.classList.remove("is-invalid");
                precio.classList.remove("is-invalid");
                ano.classList.remove("is-invalid");

                const nombreVal = nombre.value.trim();
                const descripcionVal = descripcion.value.trim().length === 0 ? null : descripcion.value.trim();
                const precioVal = parseFloat(precio.value);
                const categoriaVal = categoria.value;
                const anoVal = ano.value.trim().length === 0 ? null : parseInt(ano.value);
                const estadoVal = estado.value === "null" ? null : estado.value;
                const imagenVal = imagenCambiable.src;

                if (nombreVal.length === 0) {
                    nombre.classList.add("is-invalid");
                    errorsDiv.appendChild(newAlert("El nombre no puede estar vacio", "danger"));
                }
                if (isNaN(precioVal)) {
                    precio.classList.add("is-invalid");
                    errorsDiv.appendChild(newAlert("El precio no es un número válido", "danger"));
                }
                if (anoVal !== null && isNaN(anoVal)) {
                    precio.classList.add("is-invalid");
                    errorsDiv.appendChild(newAlert("El año no es un número válido", "danger"));
                }
                if (imagenVal.includes("img/placeholder.jpg")) {
                    errorsDiv.appendChild(newAlert("No se ha seleccionado ninguna imagen. Haga click en la imagen para cambiarla", "danger"));
                }

                if (errorsDiv.childElementCount === 0) {
                    enviarBtn.disabled = true;
                    enviarBtn.firstChild.className = "fas fa-spin fa-sync-alt me-2";
                    enviarBtn.lastChild.innerText = "Subiendo";
                    publicarArticulo(nombreVal, descripcionVal, precioVal, categoriaVal, estadoVal, anoVal, imagenVal)
                            .then(msg => location.href = "/Proyecto/articulo/" + msg.id)
                            .catch(err => {
                                enviarBtn.disabled = false;
                                enviarBtn.firstChild.className = "fas fa-upload me-2";
                                enviarBtn.lastChild.innerText = "Subir";
                                generalUnespectedError(err);
                            });
                }
            });
        });
    </script>
</t:main>
