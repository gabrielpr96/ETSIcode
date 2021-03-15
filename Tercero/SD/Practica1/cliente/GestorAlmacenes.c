#include "GestorAlmacenes.h"

void supermercado_1(CLIENT *clnt) {
    //	3
    TDatosAlmacen crearalmacen_1_arg;
    int *result_3;
    strcpy(crearalmacen_1_arg.Nombre, "Almacen1");
    strcpy(crearalmacen_1_arg.Direccion, "Huelva");
    strcpy(crearalmacen_1_arg.Fichero, "Almacen1.sd");
    result_3 = crearalmacen_1(&crearalmacen_1_arg, clnt);
    if (result_3 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Posicion: %d\n", *result_3);

    //	1
    int datosalmacen_1_arg = 0;
    TDatosAlmacen *result_1;
    result_1 = datosalmacen_1(&datosalmacen_1_arg, clnt);
    if (result_1 == (TDatosAlmacen *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("%s %s %s\n", result_1->Nombre, result_1->Direccion, result_1->Fichero);

    //	2
    int nproductos_1_arg = 0;
    int *result_2;
    result_2 = nproductos_1(&nproductos_1_arg, clnt);
    if (result_2 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("nProductos: %d\n", *result_2);

    //	5
    bool_t *result_5;
    int guardaralmacen_1_arg = 1;
    result_5 = guardaralmacen_1(&guardaralmacen_1_arg, clnt);
    if (result_5 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Resultado: %d\n", *result_5);

    //	6
    bool_t *result_6;
    int cerraralmacen_1_arg = 1;
    result_6 = cerraralmacen_1(&cerraralmacen_1_arg, clnt);
    if (result_6 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Resultado: %d\n", *result_6);

    //	7
    bool_t *result_7;
    int almacenabierto_1_arg = 1;
    result_7 = almacenabierto_1(&almacenabierto_1_arg, clnt);
    if (result_7 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Resultado: %d\n", *result_7);

    // 4
    Cadena abriralmacen_1_arg;
    int *result_4;
    strcpy(abriralmacen_1_arg, "Almacen2.sd");
    result_4 = abriralmacen_1(&abriralmacen_1_arg, clnt);
    if (result_4 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Hubicacion en memoria: %d\n", *result_4);

    //	10
    TActProd anadirproducto_1_arg;
    anadirproducto_1_arg.Almacen = 0;
    strcpy(anadirproducto_1_arg.Producto.CodProducto, "CAPc10");
    anadirproducto_1_arg.Producto.Cantidad = 5;
    strcpy(anadirproducto_1_arg.Producto.NombreProducto, "Aceite de oliva");
    anadirproducto_1_arg.Producto.Precio = 8.21;
    strcpy(anadirproducto_1_arg.Producto.Descripcion, "Una botella de aciete del bueno");
    anadirproducto_1_arg.Producto.Caducidad.Dia = 12;
    anadirproducto_1_arg.Producto.Caducidad.Mes = 12;
    anadirproducto_1_arg.Producto.Caducidad.Anyo = 2018;
    bool_t *result_10;
    result_10 = anadirproducto_1(&anadirproducto_1_arg, clnt);
    if (result_10 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Resultado Insertar: %d\n", *result_10);

    //	11
    TActProd actualizarproducto_1_arg;
    actualizarproducto_1_arg.Almacen = 0;
    strcpy(actualizarproducto_1_arg.Producto.CodProducto, "CAPc10");
    actualizarproducto_1_arg.Producto.Cantidad = 5;
    strcpy(actualizarproducto_1_arg.Producto.NombreProducto, "Aceite de oliva");
    actualizarproducto_1_arg.Producto.Precio = 12.21;
    strcpy(actualizarproducto_1_arg.Producto.Descripcion, "Una botella de aciete del muy bueno");
    actualizarproducto_1_arg.Producto.Caducidad.Dia = 12;
    actualizarproducto_1_arg.Producto.Caducidad.Mes = 12;
    actualizarproducto_1_arg.Producto.Caducidad.Anyo = 2018;
    bool_t *result_11;
    result_11 = actualizarproducto_1(&actualizarproducto_1_arg, clnt);
    if (result_11 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Resultado Actualizar: %d\n", *result_11);

    //	12
    TBusProd eliminarproducto_1_arg;
    eliminarproducto_1_arg.Almacen = 0;
    strcpy(eliminarproducto_1_arg.CodProducto, "CAPc10");
    bool_t *result_12;
    result_12 = eliminarproducto_1(&eliminarproducto_1_arg, clnt);
    if (result_12 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Resultado Eliminar: %d\n", *result_12);

    //	8
    TBusProd buscaproducto_1_arg;
    buscaproducto_1_arg.Almacen = 0;
    strcpy(buscaproducto_1_arg.CodProducto, "CAPc10");
    int *result_8;
    result_8 = buscaproducto_1(&buscaproducto_1_arg, clnt);
    if (result_8 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Producto encontrado: %d\n", *result_8);

    //	9
    TObtProd obtenerproducto_1_arg;
    obtenerproducto_1_arg.Almacen = 0;
    obtenerproducto_1_arg.PosProducto = 0;
    TProducto *result_9;
    result_9 = obtenerproducto_1(&obtenerproducto_1_arg, clnt);
    if (result_9 == (TProducto *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    printf("Producto: %s %d %s %f %s %d %d %d\n", result_9->CodProducto, result_9->Cantidad, result_9->NombreProducto, result_9->Precio, result_9->Descripcion, result_9->Caducidad.Dia, result_9->Caducidad.Mes, result_9->Caducidad.Anyo);
}

bool_t LeerCadena(Cadena c) {
    fgets(c, sizeof(Cadena) - 1, stdin);
    if ((strlen(c) > 0) && (c[strlen(c) - 1] == '\n')) {
        c[strlen(c) - 1] = '\0';
    }
    return strlen(c) == 0 ? FALSE : TRUE;
}
bool_t LeerEntero(int *d) {
    Cadena c;
    fgets(c, sizeof(Cadena) - 1, stdin);
    sscanf(c, "%d", d);
    return strlen(c) <= 1 ? FALSE : TRUE;
}
bool_t LeerReal(float *f) {
    Cadena c;
    fgets(c, sizeof(Cadena) - 1, stdin);
    sscanf(c, "%f", f);
    return strlen(c) <= 1 ? FALSE : TRUE;
}
bool_t LeerFecha(int *d, int *m, int *y) {
    Cadena c;
    fgets(c, sizeof(Cadena) - 1, stdin);
    sscanf(c, "%d/%d/%d", d, m, y);
    return strlen(c) <= 1 ? FALSE : TRUE;
}

TDatosAlmacen *RPC_DatosAlmacen(CLIENT *clnt, int pAlmacen) {
    TDatosAlmacen *result_1;
    result_1 = datosalmacen_1(&pAlmacen, clnt);
    if (result_1 == (TDatosAlmacen *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return result_1;
}
int RPC_NProductos(CLIENT *clnt, int pAlmacen) {
    int *result_2;
    result_2 = nproductos_1(&pAlmacen, clnt);
    if (result_2 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_2;
}
int RPC_CrearAlmacen(CLIENT *clnt, Cadena Nombre, Cadena Direccion, Cadena Fichero) {
    TDatosAlmacen crearalmacen_1_arg;
    int *result_3;
    strcpy(crearalmacen_1_arg.Nombre, Nombre);
    strcpy(crearalmacen_1_arg.Direccion, Direccion);
    strcpy(crearalmacen_1_arg.Fichero, Fichero);
    result_3 = crearalmacen_1(&crearalmacen_1_arg, clnt);
    if (result_3 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_3;
}
int RPC_AbrirAlmacen(CLIENT *clnt, Cadena Fichero) {
    int *result_4;
    result_4 = abriralmacen_1(Fichero, clnt);
    if (result_4 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_4;
}
bool_t RPC_GuardarAlmacen(CLIENT *clnt, int pAlmacen) {
    bool_t *result_5;
    result_5 = guardaralmacen_1(&pAlmacen, clnt);
    if (result_5 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_5;
}
bool_t RPC_CerrarAlmacen(CLIENT *clnt, int pAlmacen) {
    bool_t *result_6;
    result_6 = cerraralmacen_1(&pAlmacen, clnt);
    if (result_6 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_6;
}
bool_t RPC_AlmacenAbierto(CLIENT *clnt, int pAlmacen) {
    bool_t *result_7;
    result_7 = almacenabierto_1(&pAlmacen, clnt);
    if (result_7 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_7;
}
int RPC_BuscarProductos(CLIENT *clnt, int pAlmacen, Cadena CodProducto) {
    TBusProd buscaproducto_1_arg;
    buscaproducto_1_arg.Almacen = pAlmacen;
    strcpy(buscaproducto_1_arg.CodProducto, CodProducto);
    int *result_8;
    result_8 = buscaproducto_1(&buscaproducto_1_arg, clnt);
    if (result_8 == (int *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_8;
}
TProducto *RPC_ObtenerProducto(CLIENT *clnt, int pAlmacen, int pProducto) {
    TObtProd obtenerproducto_1_arg;
    obtenerproducto_1_arg.Almacen = pAlmacen;
    obtenerproducto_1_arg.PosProducto = pProducto;
    TProducto *result_9;
    result_9 = obtenerproducto_1(&obtenerproducto_1_arg, clnt);
    if (result_9 == (TProducto *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return result_9;
}
bool_t RPC_AnadirProducto(CLIENT *clnt, int pAlmacen, Cadena CodProducto, int Cantidad, Cadena NombreProducto, float Precio, Cadena Descripcion, int CaducidadDia, int CaducidadMes, int CaducidadAnyo) {
    TActProd anadirproducto_1_arg;
    anadirproducto_1_arg.Almacen = pAlmacen;
    strcpy(anadirproducto_1_arg.Producto.CodProducto, CodProducto);
    anadirproducto_1_arg.Producto.Cantidad = Cantidad;
    strcpy(anadirproducto_1_arg.Producto.NombreProducto, NombreProducto);
    anadirproducto_1_arg.Producto.Precio = Precio;
    strcpy(anadirproducto_1_arg.Producto.Descripcion, Descripcion);
    anadirproducto_1_arg.Producto.Caducidad.Dia = CaducidadDia;
    anadirproducto_1_arg.Producto.Caducidad.Mes = CaducidadMes;
    anadirproducto_1_arg.Producto.Caducidad.Anyo = CaducidadAnyo;
    bool_t *result_10;
    result_10 = anadirproducto_1(&anadirproducto_1_arg, clnt);
    if (result_10 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_10;
}
bool_t RPC_ActualizarProducto(CLIENT *clnt, int pAlmacen, Cadena CodProducto, int Cantidad, Cadena NombreProducto, float Precio, Cadena Descripcion, int CaducidadDia, int CaducidadMes, int CaducidadAnyo) {
    TActProd actualizarproducto_1_arg;
    actualizarproducto_1_arg.Almacen = pAlmacen;
    strcpy(actualizarproducto_1_arg.Producto.CodProducto, CodProducto);
    actualizarproducto_1_arg.Producto.Cantidad = Cantidad;
    strcpy(actualizarproducto_1_arg.Producto.NombreProducto, NombreProducto);
    actualizarproducto_1_arg.Producto.Precio = Precio;
    strcpy(actualizarproducto_1_arg.Producto.Descripcion, Descripcion);
    actualizarproducto_1_arg.Producto.Caducidad.Dia = CaducidadDia;
    actualizarproducto_1_arg.Producto.Caducidad.Mes = CaducidadMes;
    actualizarproducto_1_arg.Producto.Caducidad.Anyo = CaducidadAnyo;
    bool_t *result_11;
    result_11 = actualizarproducto_1(&actualizarproducto_1_arg, clnt);
    if (result_11 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_11;
}
bool_t RPC_EliminarProducto(CLIENT *clnt, int pAlmacen, Cadena CodProducto) {
    TBusProd eliminarproducto_1_arg;
    eliminarproducto_1_arg.Almacen = pAlmacen;
    strcpy(eliminarproducto_1_arg.CodProducto, CodProducto);
    bool_t *result_12;
    result_12 = eliminarproducto_1(&eliminarproducto_1_arg, clnt);
    if (result_12 == (bool_t *)NULL) {
        clnt_perror(clnt, "call failed");
    }
    return *result_12;
}

void CrearAlmacenVacioOP(CLIENT *clnt, Cadena AlmacenActualNombre, int *AlmacenActualPosicion) {
    //TODO: Preguntar que pasa si el nombre de archivo coincide con uno abierto, pero el nombre del almacen no. ¿Hay que pedir el nombre del almacen porque puede ser distinto al estar ya en memoria?
    //Solo se puede tener un almacen abierto a la vez
    if (*AlmacenActualPosicion != -1) {
        RPC_CerrarAlmacen(clnt, *AlmacenActualPosicion);
    }
    //Solicitar los datos del almacen
    Cadena Nombre, Direccion, Fichero;
    printf("Nombre: ");
    LeerCadena(Nombre);
    printf("Direccion: ");
    LeerCadena(Direccion);
    printf("Fichero: ");
    LeerCadena(Fichero);
    //Llamar al procedimiento para crear el almacen
    (*AlmacenActualPosicion) = RPC_CrearAlmacen(clnt, Nombre, Direccion, Fichero);
    if (*AlmacenActualPosicion == -1) {
        strcpy(AlmacenActualNombre, "");
        printf("No se ha podido crear el almacen.\n");
    } else {
        //No es seguro que el nombre del almacen abierto sea el mismo que se introdujo para crearlo porque puede ser un almacen ya creado o abierto
        strcpy(AlmacenActualNombre, RPC_DatosAlmacen(clnt, *AlmacenActualPosicion)->Nombre);
        printf("Almacen abierto correctamente.\n");
    }
    //TODO: Si todos los clientes vuelcan informacion antes de cerrarlo, el ultimo guarda los datos dos veces. Una manual y la otra utomatica antes de cerrar. Porque la operacion de cerrado guarda si eres el ultimo
}
void AbrirFicheroAlmacenOP(CLIENT *clnt, Cadena AlmacenActualNombre, int *AlmacenActualPosicion) {
    //Solo se puede tener un almacen abierto a la vez
    if (*AlmacenActualPosicion != -1) {
        RPC_CerrarAlmacen(clnt, *AlmacenActualPosicion);
    }
    //Solocitar el fichero del almacen a abrir
    Cadena Fichero;
    printf("Fichero: ");
    LeerCadena(Fichero);
    //Llamada al procedimiento para abrir un almacen
    (*AlmacenActualPosicion) = RPC_AbrirAlmacen(clnt, Fichero);
    printf("Numero de almacen abierto: %d\n", *AlmacenActualPosicion);
    if (*AlmacenActualPosicion == -1) {
        strcpy(AlmacenActualNombre, "");
        printf("No se ha podido abrir el almacen.\n");
    } else {
        //Actualizar el nombre del almacen seleccionado y abierto
        strcpy(AlmacenActualNombre, RPC_DatosAlmacen(clnt, *AlmacenActualPosicion)->Nombre);
        printf("Almacen abierto correctamente.\n");
    }
}
void CerrarAlmacenOP(CLIENT *clnt, Cadena AlmacenActualNombre, int *AlmacenActualPosicion) {
    if (*AlmacenActualPosicion == -1) {
        printf("No se ha seleccionado ningun almacen.\n");
    } else {
        if (RPC_GuardarAlmacen(clnt, *AlmacenActualPosicion)) {
            if (RPC_CerrarAlmacen(clnt, *AlmacenActualPosicion)) {
                strcpy(AlmacenActualNombre, "");
                (*AlmacenActualPosicion) = -1;
                printf("Almacen cerrado correctamente.\n");
            } else {
                printf("Error al cerrar el almacen.\n");
            }
        } else {
            printf("Error al guardar el almacen. No se cerrara.\n");
        }
    }
}
void GuardarDatosOP(CLIENT *clnt, int AlmacenActualPosicion) {
    if (AlmacenActualPosicion == -1) {
        printf("No se ha seleccionado ningun almacen.\n");
    } else {
        if (RPC_GuardarAlmacen(clnt, AlmacenActualPosicion)) {
            printf("Almacen guardado correctamente.\n");
        } else {
            printf("Error al guardar el almacen.\n");
        }
    }
}
void ListarProductosAlmacenOP(CLIENT *clnt, int AlmacenActualPosicion) {
    if (AlmacenActualPosicion == -1) {
        printf("No se ha seleccionado ningun almacen.\n");
    } else {
        int nProductos = RPC_NProductos(clnt, AlmacenActualPosicion);
        if (nProductos == -1) {
            printf("Error al mostrar productos.\n");
        } else if (nProductos == 0) {
            printf("El almacen no tiene productos.\n");
        } else {
            printf("Codigo     Nombre                         Precio     Cantidad     Caducidad\n");
            for (int i = 0; i < nProductos; i++) {
                TProducto *Producto = RPC_ObtenerProducto(clnt, AlmacenActualPosicion, i);
                printf("%-10s %-30s %-6.2f   %3d            %d/%d/%d \n", Producto->CodProducto, Producto->NombreProducto, Producto->Precio, Producto->Cantidad, Producto->Caducidad.Dia, Producto->Caducidad.Mes, Producto->Caducidad.Anyo);
            }
        }
    }
}
void AnadirProductoOP(CLIENT *clnt, int AlmacenActualPosicion) {
    if (AlmacenActualPosicion == -1) {
        printf("No se ha seleccionado ningun almacen.\n");
    } else {
        Cadena CodProducto, Nombre, Descripcion;
        int Cantidad, CaducidadDia, CaducidadMes, CaducidadAnyo;
        float Precio;
        printf("Datos del producto a insertar.\nCodigo: ");
        LeerCadena(CodProducto);
        printf("Nombre: ");
        LeerCadena(Nombre);
        printf("Descripcion: ");
        LeerCadena(Descripcion);
        printf("Precio: ");
        LeerReal(&Precio);
        printf("Cantidad: ");
        LeerEntero(&Cantidad);
        printf("Fecha de caducidad (dd/mm/aaaa): ");
        LeerFecha(&CaducidadDia, &CaducidadMes, &CaducidadAnyo);
        if (RPC_AnadirProducto(clnt, AlmacenActualPosicion, CodProducto, Cantidad, Nombre, Precio, Descripcion, CaducidadDia, CaducidadMes, CaducidadAnyo)) {
            printf("Producto agregado correctamente.\n");
        } else {
            printf("No se ha podido agregar el producto.\n");
        }
    }
}
void ActualizarProductoOP(CLIENT *clnt, int AlmacenActualPosicion) {
    if (AlmacenActualPosicion == -1) {
        printf("No se ha seleccionado ningun almacen.\n");
    } else {
        Cadena CodProducto, Nombre, Descripcion;
        int Cantidad, CaducidadDia, CaducidadMes, CaducidadAnyo;
        float Precio;

        printf("Codigo del producto a actualizar: ");
        LeerCadena(CodProducto);

        int pProducto = RPC_BuscarProductos(clnt, AlmacenActualPosicion, CodProducto);
        if (pProducto == -1) {
            printf("No se ha encontrado el producto. No se puede actualizar.\n");
        } else {
            TProducto *Producto = RPC_ObtenerProducto(clnt, AlmacenActualPosicion, pProducto);

            printf("Nuevos datos del producto.\nNombre [%s]: ", Producto->NombreProducto);
            if (!LeerCadena(Nombre)) {
                strcpy(Nombre, Producto->NombreProducto);
            }
            printf("Descripcion [%s]: ", Producto->Descripcion);
            if (!LeerCadena(Descripcion)) {
                strcpy(Descripcion, Producto->Descripcion);
            }
            printf("Precio [%.2f]: ", Producto->Precio);
            if (!LeerReal(&Precio)) {
                Precio = Producto->Precio;
            }
            printf("Cantidad [%d]: ", Producto->Cantidad);
            if (!LeerEntero(&Cantidad)) {
                Cantidad = Producto->Cantidad;
            }
            printf("Fecha de caducidad (dd/mm/aaaa) [%d/%d/%d]: ", Producto->Caducidad.Dia, Producto->Caducidad.Mes, Producto->Caducidad.Anyo);
            if (!LeerFecha(&CaducidadDia, &CaducidadMes, &CaducidadAnyo)) {
                CaducidadDia = Producto->Caducidad.Dia;
                CaducidadMes = Producto->Caducidad.Mes;
                CaducidadAnyo = Producto->Caducidad.Anyo;
            }

            if (RPC_ActualizarProducto(clnt, AlmacenActualPosicion, CodProducto, Cantidad, Nombre, Precio, Descripcion, CaducidadDia, CaducidadMes, CaducidadAnyo)) {
                printf("Producto actualizado correctamente.\n");
            } else {
                printf("No se ha podido actualizar el producto. Es posible que no exista ningun producto con ese codigo\n");
            }
        }
    }
}
void ConsultarProductoOP(CLIENT *clnt, int AlmacenActualPosicion) {
    Cadena CodProducto;
    printf("Codigo del producto: ");
    LeerCadena(CodProducto);
    int pProducto = RPC_BuscarProductos(clnt, AlmacenActualPosicion, CodProducto);
    if (pProducto == -1) {
        printf("No se ha encontrado el producto.\n");
    } else {
        TProducto *Producto = RPC_ObtenerProducto(clnt, AlmacenActualPosicion, pProducto);
        printf(
            "Codigo: %s\n"
            "Nombre: %s\n"
            "Descripcion: %s\n"
            "Precio: %.2f\n"
            "Cantidad: %d\n"
            "Fecha de caducidad: %d/%d/%d\n",
            Producto->CodProducto, Producto->NombreProducto, Producto->Descripcion, Producto->Precio, Producto->Cantidad, Producto->Caducidad.Dia, Producto->Caducidad.Mes, Producto->Caducidad.Anyo);
    }
}
void EliminarProductoOP(CLIENT *clnt, int AlmacenActualPosicion) {
    Cadena CodProducto;
    printf("Codigo del producto a eliminar: ");
    LeerCadena(CodProducto);
    if (RPC_EliminarProducto(clnt, AlmacenActualPosicion, CodProducto)) {
        printf("Producto eliminado correctamente.\n");
    } else {
        printf("No se ha podido eliminar el producto. Es posible que no exista.\n");
    }
}

int menu(Cadena AlmacenActualNombre) {
    int n;

    do {
        printf(
            "----- Menu Almacenes ----- %s\n"
            "1.- Crear un almacen vacio.\n"
            "2.- Abrir un fichero de almacen.\n"
            "3.- Cerrar almacen.\n"
            "4.- Guardar datos.\n"
            "5.- Listar productos del almacen.\n"
            "6.- Anadir un producto.\n"
            "7.- Actualizar un producto.\n"
            "8.- Consultar un producto.\n"
            "9.- Eliminar un producto.\n"
            "0.- Salir.\n\n"
            "Opcion: ",
            AlmacenActualNombre);
        LeerEntero(&n);
    } while (n < 0 || n > 9);

    return n;
}

int main(int argc, char *argv[]) {
    char *host;

    if (argc < 2) {
        printf("Uso: %s server_host\n", argv[0]);
        exit(1);
    }
    host = argv[1];
    CLIENT *clnt = clnt_create(host, SUPERMERCADO, SUPERMERCADO_VER, "tcp");
    if (clnt == NULL) {
        clnt_pcreateerror(host);
        exit(1);
    }
    //supermercado_1(clnt);

    Cadena AlmacenActualNombre;
    strcpy(AlmacenActualNombre, "");
    int AlmacenActualPosicion = -1;

    int opt;
    do {
        opt = menu(AlmacenActualNombre);
        printf("\n");
        switch (opt) {
            case 1:
                CrearAlmacenVacioOP(clnt, AlmacenActualNombre, &AlmacenActualPosicion);
                break;
            case 2:
                AbrirFicheroAlmacenOP(clnt, AlmacenActualNombre, &AlmacenActualPosicion);
                break;
            case 3:
                CerrarAlmacenOP(clnt, AlmacenActualNombre, &AlmacenActualPosicion);
                break;
            case 4:
                GuardarDatosOP(clnt, AlmacenActualPosicion);
                break;
            case 5:
                ListarProductosAlmacenOP(clnt, AlmacenActualPosicion);
                break;
            case 6:
                AnadirProductoOP(clnt, AlmacenActualPosicion);
                break;
            case 7:
                ActualizarProductoOP(clnt, AlmacenActualPosicion);
                break;
            case 8:
                ConsultarProductoOP(clnt, AlmacenActualPosicion);
                break;
            case 9:
                EliminarProductoOP(clnt, AlmacenActualPosicion);
                break;
        }
        if (opt != 0) {
            printf("\nEnter para regresar al menú.");
            getchar();
            printf("\033[H\033[J");
        }
    } while (opt != 0);

    if (AlmacenActualPosicion != -1) {
        RPC_CerrarAlmacen(clnt, AlmacenActualPosicion);
    }

    clnt_destroy(clnt);
    exit(0);
}
