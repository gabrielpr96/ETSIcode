
const API_ENDPOINT = "/Proyecto/api";

function fetchAPI(method, url, data) {
    return new Promise((resolve, reject) => {
        fetch(API_ENDPOINT + url, {
            method: method,
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json'
            },
            redirect: 'follow',
            body: method==="GET"?null:JSON.stringify(data)
        })
                .then(response => response.text())
                .then(data => {
                    try {
                        data = JSON.parse(data);
                    } catch (error) {
                        reject(data);
                    }
                    if (data.status === "success")
                        resolve(data.msg);
                    else
                        reject(data.msg.error);
                })
                .catch(error => {
                    reject(error);
                });
    });
}

function fetchHtmlAPI(method, url, data) {
    return new Promise((resolve, reject) => {
        fetch(API_ENDPOINT + url, {
            method: method,
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json'
            },
            redirect: 'follow',
            body: method==="GET"?null:JSON.stringify(data)
        })
                .then(response => response.text())
                .then(data => {
                    resolve(data);
                })
                .catch(error => {
                    console.log("close error", error);
                    reject(error);
                });
    });
}

function generalUnespectedError(error) {
    showMSG("Error en el proceso", "La acción no terminó satisfactoriamente debido al siguiente error:", error);
    console.log(error)
}

function registrarUsuario(email, pass, nombre, direccion, cp, telefono, facebook, twitter, captcha) {
    return fetchAPI("POST", "/usuario", {
        email, pass, nombre, direccion, cp, telefono, facebook, twitter, captcha
    });
}

function activarUsuario(codigo) {
    return fetchAPI("POST", "/activar", {
        codigo
    });
}

function loginUsuario(email, pass) {
    return fetchAPI("POST", "/login", {
        email, pass
    });
}

function logoutUsuario() {
    return fetchAPI("GET", "/logout", {});
}

function publicarArticulo(nombre, descripcion, precio, categoria, estado, ano, imagen) {
    return fetchAPI("POST", "/publicar", {
        nombre, descripcion, precio, categoria, estado, ano, imagen
    });
}

function listarArticulos(categoria = null, cp = null, precioMin = null, precioMax = null, pagina = 0, cantidad = 10) {
    return fetchAPI("POST", "/articulo", {
        categoria, cp, precioMin, precioMax, pagina, cantidad
    });
}

function listarIntereses(categoria = null, cp = null, precioMin = null, precioMax = null, pagina = 0, cantidad = 10) {
    return fetchAPI("POST", "/intereses", {
        categoria, cp, precioMin, precioMax, pagina, cantidad
    });
}

function isFaved(articulo) {
    return fetchAPI("POST", "/is-faved", {
        articulo
    });
}

function toggleFav(articulo) {
    return fetchAPI("POST", "/toggle-faved", {
        articulo
    });
}

function getDatosUsuario(usuario) {
    return fetchAPI("POST", "/datos-usuario", {
        usuario
    });
}

function comentar(articulo, texto, visibilidad) {
    return fetchAPI("POST", "/comentar", {
        articulo, texto, visibilidad
    });
}

function solicitarRecuperacion(email) {
    return fetchAPI("POST", "/recuperar-pass", {
        email
    });
}

function recuperarPass(secreto, pass) {
    return fetchAPI("POST", "/cambiar-pass", {
        secreto, pass
    });
}

function pintarCarrito(carrito) {
    return fetchHtmlAPI("POST", "/carrito", {
        carrito
    });
}