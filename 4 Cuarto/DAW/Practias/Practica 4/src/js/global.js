const navBar = [
    { name: "Inicio", target: "index.html" },
    {
        name: "Categorias", target: "categorias.html", submenus: [
            { name: "Electronica", target: "categorias.html" },
            { name: "Lectura", target: "categorias.html" },
            { name: "Juegos", target: "categorias.html" },
            { name: "Otros", target: "categorias.html" }
        ]
    },
    { name: "Articulos", target: "articulos.html" },
    { name: "Formulario", target: "formulario.html" }
];

function innitNavbar(selected) {
    let container = document.getElementById("navBarItems");
    navBar.forEach(element => {
        /*
        <li class="nav-item">
            <a class="nav-link" href="#">Pricing</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                data-bs-toggle="dropdown" aria-expanded="false">
                Dropdown link
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
        </li>
        */
        let item, link;
        if (element.submenus === undefined) {
            item = document.createElement("li");
            item.className = "nav-item";
            link = document.createElement("a");
            link.className = "nav-link";
            link.href = element.target;
            link.innerText = element.name;
            item.appendChild(link);
        }else{
            item = document.createElement("li");
            item.className = "nav-item dropdown";
            link = document.createElement("a");
            link.className = "nav-link dropdown-toggle";
            link.href = element.target;
            link.role = "button";
            link.dataset.bsToggle = "dropdown";
            link.innerText = element.name;
            let submenu = document.createElement("ul");
            submenu.className = "dropdown-menu";
            element.submenus.forEach(subElement => {
                let subItem = document.createElement("li");
                let subLink = document.createElement("a");
                subLink.className = "dropdown-item";
                subLink.href = subElement.target;
                subLink.innerText = subElement.name;
                subItem.appendChild(subLink);
                submenu.appendChild(subItem);
            });
            item.appendChild(link);
            item.appendChild(submenu);
        }
        if(element.name == selected) link.classList.add("active");
        container.appendChild(item);
    });
}