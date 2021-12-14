
function newElement(type, clases = null, text = null, data = []) {
    let e = document.createElement(type);

    if (clases !== null)
        e.className = clases;
    if (text !== null)
        e.innerText = text;
    for (var dat in data) {
        e.dataset[data[dat].name] = data[dat].value;
    }

    if (type === "button")
        e.type = "button";
    if (clases !== null && clases.includes("alert"))
        e.role = "alert";

    return e;
}

function removeAllChilds(e) {
    while (e.firstChild)
        e.removeChild(e.lastChild);
}

function newAlert(text, type = "warning") {
    let div = newElement("div", `alert alert-${type} alert-dismissible fade show`);
    let msg = newElement("span", null, text);
    let closeBtn = newElement("button", "btn-close", null, [{"name": "bsDismiss", "value": "alert"}]);
    div.appendChild(msg);
    div.appendChild(closeBtn);
    return div;
}

function getCarrito() {
    let cart = localStorage.cart;
    if (cart === undefined)
        cart = [];
    else
        cart = JSON.parse(cart);
    return cart;
}

function setCarrito(cart){
    localStorage.cart = JSON.stringify(cart);
}

function addCarrito(id){
    let cart = getCarrito();
    cart.push(id);
    setCarrito(cart);
}

function removeCarritoByIndex(index){
    let cart = getCarrito();
    cart.splice(index, 1);
    setCarrito(cart);
}

function newFavBtn(id, lg = false) {
    let btn = newElement("button", `btn ${lg ? "btn-lg" : ""} btn-outline-warning btn-favver`);
    let heart = newElement("i", "fas fa-heart text-secondary");
    btn.appendChild(heart);
    isFaved(id).then(msg => {
        heart.classList.remove("text-secondary");
        heart.classList.add(msg.faved ? "text-danger" : "text-dark");
    }).catch(() => btn.classList.add("visually-hidden"));
    btn.addEventListener("click", () => {
        //heart.classList.remove("fa-heart");
        //heart.classList.add("fa-spin");
        //heart.classList.add("fa-sync");
        toggleFav(id).then(msg => {
            heart.classList.remove("text-danger");
            heart.classList.remove("text-dark");
            heart.classList.add(msg.faved ? "text-danger" : "text-dark");
            //heart.classList.add("fa-heart");
            //heart.classList.remove("fa-spin");
            //heart.classList.remove("fa-sync");
        }).catch(generalUnespectedError);
    });
    return btn;
}

function newCarritoBtn(id, lg = false) {
    let btn = newElement("button", `btn ${lg ? "btn-lg" : ""} btn-outline-warning btn-carriter`);
    let heart = newElement("i", "fas fa-shopping-cart text-secondary");
    btn.appendChild(heart);
    btn.addEventListener("click", () => {
        addCarrito(id);
        showCarrito();
    });
    return btn;
}

function newArticuloCard(articulo) {
    let col = newElement("div", "col-11 col-md-5 col-lg-4 col-xl-3 m-2");
    let card = newElement("div", "card shadow");
    let img = newElement("img", "card-img-top");
    img.style.cursor = "pointer";
    img.onclick = () => location.href = "/Proyecto/articulo/" + articulo.id;
    img.src = articulo.imagen;
    let body = newElement("div", "card-body");
    let title = newElement("h5", "card-title", articulo.nombre);
    let text = newElement("p", "card-text", "Precio: " + articulo.precio + "€");
    let btnsDiv = newElement("div", "d-flex flex-row justify-content-between");
    let fav = newFavBtn(articulo.id);
    let carriter = newCarritoBtn(articulo.id);


    col.appendChild(card);
    card.appendChild(img);
    card.appendChild(body);
    body.appendChild(title);
    body.appendChild(text);
    body.appendChild(btnsDiv);
    btnsDiv.appendChild(fav);
    btnsDiv.appendChild(carriter);

    return col;
}

function mostrarUsuario(usuarioId) {
    getDatosUsuario(usuarioId)
            .then(msg => {
                console.log(msg);
                showMSG("Usuario", `
                    <strong>Nombre:</strong>
                    <span>${msg.nombre}</span><br>
                    <strong>Email:</strong>
                    <span>${msg.email}</span><br>
                    <strong>Teléfono:</strong>
                    <span>${msg.telefono}</span><br>
                    <strong>Código postal:</strong>
                    <span>${msg.cp}</span><br>
                    ${msg.direccion ? `
                        <strong>Dirección:</strong>
                        <span>${msg.direccion}</span><br>
                    ` : ""}
                    ${msg.facebook ? `
                        <strong>Facebook:</strong>
                        <a target="_blank" href="https://www.facebook.com/${msg.facebook}">${msg.facebook}</a><br>
                    ` : ""}
                    ${msg.twitter ? `
                        <strong>Twitter:</strong>
                        <a target="_blank" href="https://twitter.com/${msg.twitter}">${msg.twitter}</a><br>
                    ` : ""}
                    
                `, null, true);
            })
            .catch(generalUnespectedError);
}