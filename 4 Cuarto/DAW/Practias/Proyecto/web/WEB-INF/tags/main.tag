<%@tag description="Main wrapper tag" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <title>Proyecto DAW</title>
        <style>
            .card{
                transition: transform .2s;
            }
            .card:hover{
                transform: scale(1.1);
            }
        </style>
    </head>
    <body>

        <!-- Global Loader -->
        <div id="globalWaiting" style="position: fixed;top:0;left:0;width: 100%;height: 100%;background-color: #64B5F680;z-index: 1000000;" class="d-flex flex-column justify-content-center align-items-center visually-hidden">
            <h1></h1>
            <div class="spinner-border text-primary" style="width: 5rem;height: 5rem;" role="status"></div>
        </div>

        <!-- Modals -->
        <div id="msgModal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content text-dark">
                    <div class="modal-header">
                        <h5 class="modal-title" id="msgTitle"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <span id="msgBody"></span>
                        <textarea id="msgTextarea" style='width:100%;margin-top: 10px;' rows='5'></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-bs-dismiss="modal" class="btn btn-outline-secondary">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="confModal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content text-dark">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confTitle"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="confBody">
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-bs-dismiss="modal" class="btn btn-outline-secondary" id="confCancelBTN">Cancelar</button>
                        <button type="button" class="btn btn-outline-primary" id="confBTN">
                            <div class="spinner-border spinner-border-sm visually-hidden" role="status" id="confSpinner"></div>
                            Confirmar
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalRegistrar" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Registrar</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label for="modalRegistrarEmail" class="form-label"><i class="fas fa-asterisk me-2"></i>Correo electrónico</label>
                                <input type="email" class="form-control" id="modalRegistrarEmail">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarPass1" class="form-label"><i class="fas fa-asterisk me-2"></i>Contraseña</label>
                                <input type="password" class="form-control" id="modalRegistrarPass1">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarPass2" class="form-label"><i class="fas fa-asterisk me-2"></i>Repetir Contraseña</label>
                                <input type="password" class="form-control" id="modalRegistrarPass2">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarNombre" class="form-label"><i class="fas fa-asterisk me-2"></i>Nombre</label>
                                <input type="text" class="form-control" id="modalRegistrarNombre">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarDireccion" class="form-label">Direccion</label>
                                <input type="text" class="form-control" id="modalRegistrarDireccion">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarCP" class="form-label"><i class="fas fa-asterisk me-2"></i>Código postal</label>
                                <input type="text" class="form-control" id="modalRegistrarCP">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarTelefono" class="form-label"><i class="fas fa-asterisk me-2"></i>Teléfono</label>
                                <input type="text" class="form-control" id="modalRegistrarTelefono">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarFacebook" class="form-label">Facebook</label>
                                <input type="text" class="form-control" id="modalRegistrarFacebook">
                            </div>
                            <div class="mb-3">
                                <label for="modalRegistrarTwitter" class="form-label">Twitter</label>
                                <input type="text" class="form-control" id="modalRegistrarTwitter">
                            </div>
                            <div class="mb-3">
                                <div class="g-recaptcha" data-sitekey="6LcUXYAdAAAAADwTxjdWE66H8tgXbZGpqexqhhlv"></div>
                            </div>
                        </form>
                        <div id="modalRegistrarErrores"></div>
                    </div>
                    <div class="modal-footer d-flex flex-row flex-wrap-reverse">
                        <button type="button" data-bs-toggle="modal" data-bs-target="#modalLogin" class="btn btn-sm btn-outline-dark">Iniciar sesión</button>
                        <button type="button" class="btn btn-primary" id="modalRegistrarBtn">Registrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalLogin" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Iniciar sesión</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label for="modalLoginEmail" class="form-label">Correo electrónico</label>
                                <input type="email" class="form-control" id="modalLoginEmail">
                            </div>
                            <div class="mb-3">
                                <label for="modalLoginPass" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" id="modalLoginPass">
                            </div>
                        </form>
                        <div id="modalLoginErrores"></div>
                    </div>
                    <div class="modal-footer d-flex flex-row flex-wrap-reverse">
                        <button type="button" data-bs-toggle="modal" data-bs-target="#modalRecuperar" class="btn btn-sm btn-outline-dark">Recuperar contraseña</button>
                        <button type="button" data-bs-toggle="modal" data-bs-target="#modalRegistrar" class="btn btn-sm btn-outline-dark">Registrar</button>
                        <button type="button" class="btn btn-primary" id="modalLoginBtn">Iniciar sesión</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalActivar" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Activar</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <strong>Su cuenta no esta activada. Debe introducir el código que se ha enviado a su correo electrónico para poder iniciar sesión.</strong>
                        <form>
                            <div class="mb-3">
                                <label for="modalActivarCodigo" class="form-label">Codigo</label>
                                <input type="text" class="form-control" id="modalActivarCodigo">
                            </div>
                            <input type="hidden" id="modalActivarEmail">
                            <input type="hidden" id="modalActivarPass">
                        </form>
                        <div id="modalActivarErrores"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="modalActivarBtn">Activar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalRecuperar" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Recuperar</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <strong>Introduzca su correo electrónico para recuperar la cuenta.</strong>
                        <form>
                            <div class="my-3">
                                <label for="modalRecuperarEmail" class="form-label">Correo electrónico</label>
                                <input type="email" class="form-control" id="modalRecuperarEmail">
                            </div>
                        </form>
                        <div id="modalRecuperarErrores"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="modalRecuperarBtn">Recuperar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Scripts -->
        <script src="<%= request.getContextPath()%>/libs/api.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/libs/msg.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/libs/helpex.js" type="text/javascript"></script>
        <script>
            window.addEventListener("load", () => {
                const btnRegistrar = document.getElementById("modalRegistrarBtn");
                if (btnRegistrar !== null) {
                    btnRegistrar.addEventListener("click", () => {
                        let email = document.getElementById("modalRegistrarEmail");
                        let pass1 = document.getElementById("modalRegistrarPass1");
                        let pass2 = document.getElementById("modalRegistrarPass2");
                        let nombre = document.getElementById("modalRegistrarNombre");
                        let direccion = document.getElementById("modalRegistrarDireccion");
                        let cp = document.getElementById("modalRegistrarCP");
                        let telefono = document.getElementById("modalRegistrarTelefono");
                        let facebook = document.getElementById("modalRegistrarFacebook");
                        let twitter = document.getElementById("modalRegistrarTwitter");
                        let errores = document.getElementById("modalRegistrarErrores");

                        email.classList.remove("is-invalid");
                        pass1.classList.remove("is-invalid");
                        pass2.classList.remove("is-invalid");
                        nombre.classList.remove("is-invalid");
                        direccion.classList.remove("is-invalid");
                        cp.classList.remove("is-invalid");
                        telefono.classList.remove("is-invalid");
                        facebook.classList.remove("is-invalid");
                        twitter.classList.remove("is-invalid");
                        removeAllChilds(errores);

                        let emailVal = email.value.trim();
                        let pass1Val = pass1.value.trim();
                        let pass2Val = pass2.value.trim();
                        let nombreVal = nombre.value.trim();
                        let direccionVal = direccion.value.trim();
                        let cpVal = parseInt(cp.value.trim());
                        let telefonoVal = telefono.value.trim();
                        let facebookVal = facebook.value.trim();
                        let twitterVal = twitter.value.trim();

                        if (emailVal.length === 0) {
                            email.classList.add("is-invalid");
                            errores.appendChild(newAlert("El email no puede estar vacio", "danger"));
                        }
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
                        if (nombreVal.length === 0) {
                            nombre.classList.add("is-invalid");
                            errores.appendChild(newAlert("El nombre no puede estar vacio", "danger"));
                        }
                        if (direccionVal.length === 0)
                            direccionVal = null;
                        if (isNaN(cpVal)) {
                            cp.classList.add("is-invalid");
                            errores.appendChild(newAlert("El código postal no es un número valido", "danger"));
                        }
                        if (telefonoVal.length === 0) {
                            telefono.classList.add("is-invalid");
                            errores.appendChild(newAlert("El teléfono no puede estar vacio", "danger"));
                        }
                        if (facebookVal.length === 0)
                            facebookVal = null;
                        if (twitterVal.length === 0)
                            twitterVal = null;

                        if (errores.childElementCount === 0) {
                            registrarUsuario(emailVal, pass1Val, nombreVal, direccionVal, cpVal, telefonoVal, facebookVal, twitterVal, grecaptcha.getResponse())
                                    .then(() => {
                                        document.getElementById("modalActivarEmail").value = emailVal;
                                        document.getElementById("modalActivarPass").value = pass1Val;
                                        hideAllModals(null, "modalActivar");
                                    }).catch(generalUnespectedError);
                        }
                    });
                }

                const btnLogin = document.getElementById("modalLoginBtn");
                if (btnLogin !== null) {
                    btnLogin.addEventListener("click", () => {
                        let email = document.getElementById("modalLoginEmail");
                        let pass = document.getElementById("modalLoginPass");
                        let errores = document.getElementById("modalLoginErrores");

                        email.classList.remove("is-invalid");
                        pass.classList.remove("is-invalid");
                        removeAllChilds(errores);

                        let emailVal = email.value.trim();
                        let passVal = pass.value.trim();

                        if (emailVal.length === 0) {
                            email.classList.add("is-invalid");
                            errores.appendChild(newAlert("El email no puede estar vacio", "danger"));
                        }
                        if (passVal.length === 0) {
                            pass.classList.add("is-invalid");
                            errores.appendChild(newAlert("La contraseña no puede estar vacia", "danger"));
                        }

                        if (errores.childElementCount === 0) {
                            loginUsuario(emailVal, passVal)
                                    .then(msg => {
                                        console.log("login", msg);
                                        if (msg.activated === false) {
                                            document.getElementById("modalActivarEmail").value = emailVal;
                                            document.getElementById("modalActivarPass").value = passVal;
                                            hideAllModals(null, "modalActivar");
                                        } else {
                                            if (msg.activated === undefined) {
                                                errores.appendChild(newAlert("Credenciales incorrectas", "danger"));
                                            } else {
                                                location.reload();
                                            }
                                        }
                                    }).catch(generalUnespectedError);
                        }
                    });
                }

                const btnActivar = document.getElementById("modalActivarBtn");
                if (btnActivar !== null) {
                    btnActivar.addEventListener("click", () => {
                        let codigo = document.getElementById("modalActivarCodigo");
                        let errores = document.getElementById("modalActivarErrores");

                        codigo.classList.remove("is-invalid");
                        removeAllChilds(errores);

                        let codigoVal = codigo.value.trim();

                        if (codigoVal.length === 0) {
                            codigo.classList.add("is-invalid");
                            errores.appendChild(newAlert("El codigo no puede estar vacio", "danger"));
                        }

                        if (errores.childElementCount === 0) {
                            activarUsuario(codigoVal)
                                    .then(res => {
                                        console.log(res);
                                        if (res.activado) {
                                            loginUsuario(document.getElementById("modalActivarEmail").value, document.getElementById("modalActivarPass").value)
                                                    .then(() => {
                                                        location.reload();
                                                    })
                                                    .catch(generalUnespectedError);
                                        } else {
                                            errores.appendChild(newAlert("Codigo no reconocido", "danger"));
                                        }
                                    }).catch(generalUnespectedError);
                        }
                    });
                }

                const logoutBtn = document.getElementById("logoutBtn");
                if (logoutBtn !== null) {
                    logoutBtn.addEventListener("click", () => {
                        logoutUsuario().then(() => location.reload()).catch(generalUnespectedError);
                    });
                }

                const btnRecuperar = document.getElementById("modalRecuperarBtn");
                if (btnRecuperar !== null) {
                    btnRecuperar.addEventListener("click", () => {
                        let email = document.getElementById("modalRecuperarEmail");
                        let errores = document.getElementById("modalRecuperarErrores");

                        email.classList.remove("is-invalid");
                        removeAllChilds(errores);

                        let emailVal = email.value.trim();

                        if (emailVal.length === 0) {
                            email.classList.add("is-invalid");
                            errores.appendChild(newAlert("El email no puede estar vacio", "danger"));
                        }

                        if (errores.childElementCount === 0) {
                            solicitarRecuperacion(emailVal)
                                    .then(res => {
                                        console.log(res);
                                        showMSG("Email de recuperacion enviado", "Recibirás un correo con un enlace para cambiar la contraseña.");
                                    }).catch(generalUnespectedError);
                        }
                    });
                }

                const carritoBtn = document.getElementById("carritoBtn");
                if (carritoBtn !== null) {
                    carritoBtn.addEventListener("click", showCarrito);
                }
            });

            function showCarrito() {
                pintarCarrito(getCarrito())
                        .then(msg => showMSG("Carrito", msg, null, true))
                //.catch(generalUnespectedError());
            }
        </script>

        <!-- Navegacion -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="/Proyecto">Proyecto DAW</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link ${pageContext.request.contextPath == "/Proyecto"?"active":""}" href="/Proyecto">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(pageContext.request.contextPath, '/articulos')?"active":""}" href="/Proyecto/articulos">Articulos</a>
                        </li>
                        <% if (request.getSession().getAttribute("id") != null) { %>
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(pageContext.request.contextPath, '/publicar')?"active":""}" href="/Proyecto/publicar">Publicar</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(pageContext.request.contextPath, '/interes')?"active":""}" href="/Proyecto/interes">Interés</a>
                        </li>
                        <%}%>
                    </ul>
                    <span class="navbar-text">
                        <div class="d-flex flex-row align-items-center">
                            <% if (request.getSession().getAttribute("id") != null) {%>
                            <span class="me-2">Bienvenido <%= request.getSession().getAttribute("nombre")%>.</span>
                            <button type="button" class="btn btn-outline-dark mx-1" id="logoutBtn">Cerrar sesión</button>
                            <%}%>
                            <% if (request.getSession().getAttribute("id") == null) { %>
                            <button type="button" class="btn btn-outline-dark mx-1" data-bs-toggle="modal" data-bs-target="#modalLogin">Iniciar sesión</button>
                            <button type="button" class="btn btn-outline-dark mx-1" data-bs-toggle="modal" data-bs-target="#modalRegistrar">Registrar</button>
                            <%}%>
                            <button type="button" class="btn btn-outline-dark mx-1" id="carritoBtn"><i class="fas fa-shopping-cart"></i></button>
                        </div>
                    </span>
                </div>
            </div>
        </nav>

        <!-- Contenido -->
        <div class="container mt-2" style="margin-bottom: 5rem;">
            <jsp:doBody/>
        </div>

        <!-- Footer -->
        <footer class="footer fixed-bottom bg-light p-3">
            <div class="container">
                <span>(c) copyright 2021 - B0vE - Borja López Pineda - Proyecto DAW UHU</span>
            </div>
        </footer>
    </body>
</html>
