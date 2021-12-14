<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:main>
    <h3>Todos los articulos:</h3>
    <div class="row">
        <div class="col-12 col-md-4 col-lg-3 p-2">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="filtroCategoria">
                <label class="form-check-label" for="filtroCategoria">Filtrar por categoria</label>
            </div>
            <select class="form-select" id="categoria">
                <c:forEach var="categoria" items="${categorias}">
                    <option value="${categoria.id}">${categoria.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-12 col-md-4 col-lg-3 p-2">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="filtroCP">
                <label class="form-check-label" for="filtroCategoria">Filtrar por código postal</label>
            </div>
            <input type="text" class="form-control" id="cp">
        </div>
        <div class="col-12 col-md-4 col-lg-3 p-2">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="filtroPrecioMin">
                <label class="form-check-label" for="filtroPrecioMin">Filtrar por precio mínimo</label>
            </div>
            <input type="text" class="form-control" id="precioMin">
        </div>
        <div class="col-12 col-md-4 col-lg-3 p-2">
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" id="filtroPrecioMax">
                <label class="form-check-label" for="filtroPrecioMax">Filtrar por precio máximo</label>
            </div>
            <input type="text" class="form-control" id="precioMax">
        </div>
    </div>
    <div id="errorsDiv"></div>
    <div class="d-flex flex-row flex-wrap justify-content-center mb-3" id="articulosDiv"></div>
    <div class="d-flex flex-row justify-content-center">
        <button type="button" class="btn btn-lg btn-outline-primary" id="cargarBtn"><i class="fas fa-sync-alt me-2"></i>Cargar más</button>
    </div>
    <script>
        const cargarBtn = document.getElementById("cargarBtn");
        const filtroCategoria = document.getElementById("filtroCategoria");
        const categoria = document.getElementById("categoria");
        const filtroCP = document.getElementById("filtroCP");
        const cp = document.getElementById("cp");
        const filtroPrecioMin = document.getElementById("filtroPrecioMin");
        const precioMin = document.getElementById("precioMin");
        const filtroPrecioMax = document.getElementById("filtroPrecioMax");
        const precioMax = document.getElementById("precioMax");
        const errorsDiv = document.getElementById("errorsDiv");
        const articulosDiv = document.getElementById("articulosDiv");
        window.addEventListener("load", () => {
            filtroCategoria.addEventListener("change", () => actualizar());
            filtroCP.addEventListener("change", () => actualizar());
            filtroPrecioMin.addEventListener("change", () => actualizar());
            filtroPrecioMax.addEventListener("change", () => actualizar());
            categoria.addEventListener("change", () => {if(filtroCategoria.checked)actualizar()});
            cp.addEventListener("change", () => {if(filtroCP.checked)actualizar()});
            precioMin.addEventListener("change", () => {if(filtroPrecioMin.checked)actualizar()});
            precioMax.addEventListener("change", () => {if(filtroPrecioMax.checked)actualizar()});
            actualizar();
        });
        
        var paginaActual;
        function actualizar(pagina = 0){
            paginaActual = pagina;
            
            removeAllChilds(errorsDiv);
            cp.classList.remove("is-invalid");
            precioMin.classList.remove("is-invalid");
            precioMax.classList.remove("is-invalid");
            
            const categoriaVal = filtroCategoria.checked?categoria.value:null;
            const cpVal = filtroCP.checked?parseInt(cp.value):null;
            const precioMinVal = filtroPrecioMin.checked?parseFloat(precioMin.value):null;
            const precioMaxVal = filtroPrecioMax.checked?parseFloat(precioMax.value):null;
            
            if(cpVal !== null && isNaN(cpVal)){
                cp.classList.add("is-invalid");
                errorsDiv.appendChild(newAlert("El código postal no es valido"));
            }
            if(precioMinVal !== null && isNaN(precioMinVal)){
                precioMin.classList.add("is-invalid");
                errorsDiv.appendChild(newAlert("El precio mínimo no es valido"));
            }
            if(precioMaxVal !== null && isNaN(precioMaxVal)){
                precioMax.classList.add("is-invalid");
                errorsDiv.appendChild(newAlert("El precio máximo no es valido"));
            }
            if(errorsDiv.childElementCount === 0){
                if(pagina === 0) removeAllChilds(articulosDiv);
                cargaEmpieza();
                listarArticulos(categoriaVal, cpVal, precioMinVal, precioMaxVal, pagina)
                        .then(msg => msg.articulos.forEach(articulo => articulosDiv.appendChild(newArticuloCard(articulo)))
                        ).catch(generalUnespectedError)
                        .then(cargaTermina);
            }
        }
        
        function cargaEmpieza(){
            cargarBtn.disabled = true;
            cargarBtn.firstChild.classList.add("fa-spin");
        }
        function cargaTermina(){
            cargarBtn.disabled = false;
            cargarBtn.firstChild.classList.remove("fa-spin");
        }
        
        cargarBtn.addEventListener("click", () => actualizar(paginaActual-(-1)));
    </script>
</t:main>
