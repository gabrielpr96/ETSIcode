function validar(f) {
    if(f.nombre.value == "") {
        alert("Debes introducir tu nombre");
        return false;
    }
    return true;
}