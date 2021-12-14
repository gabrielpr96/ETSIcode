<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
    <h3>Cambiar contraseña:</h3>
    <div class="d-flex flex-column align-items-center justify-content-center">
        <form>
            <div class="mb-3">
                <label for="pass1" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="pass1">
            </div>
            <div class="mb-3">
                <label for="pass2" class="form-label">Repetir contraseña</label>
                <input type="password" class="form-control" id="pass2">
            </div>
        </form>
        <button type="submit" class="btn btn-primary" id="recuperarBtn">Cambiar</button>
        <div id="errores" class="mt-3"></div>
    </div>
    <script>
        window.addEventListener("load", () => {
            const recuperarBtn = document.getElementById("recuperarBtn");
            recuperarBtn.addEventListener("click", () => {
                let pass1 = document.getElementById("pass1");
                let pass2 = document.getElementById("pass2");
                let errores = document.getElementById("errores");

                pass1.classList.remove("is-invalid");
                pass2.classList.remove("is-invalid");
                removeAllChilds(errores);

                let pass1Val = pass1.value.trim();
                let pass2Val = pass2.value.trim();

                if (pass1Val.length === 0) {
                    pass1.classList.add("is-invalid");
                    errores.appendChild(newAlert("La contraseña no puede estar vacia", "danger"));
                } else {
                    if (pass1Val !== pass2Val) {
                        pass1.classList.add("is-invalid");
                        pass2.classList.add("is-invalid");
                        errores.appendChild(newAlert("Las contraseñas no coinciden", "danger"));
                    }
                }

                if (errores.childElementCount === 0) {
                    recuperarPass("${codigo}", pass1Val)
                            .then(() => {
                                showConf("Contraseña cambiada", "Se ha cambiado la contraseña de su cuenta. Incie sesión desde la barra de navegación.")
                                        .then(() => location.href = "/Proyecto")
                                        .catch(() => hideAllModals());
                            })
                            .catch(generalUnespectedError);
                }

            });
        });
    </script>
</t:main>
