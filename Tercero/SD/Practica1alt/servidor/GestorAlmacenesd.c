#include "GestorAlmacenes.h"
#include "string.h"

struct TAlmacenMemoria {
    //Cantidad de usuarios que tienen abierto el almacen. 0 indica que está cerrado y puede ser reutilizado.
    int nAbierto;
    TDatosAlmacen Datos;
    int nProductos;
    TProducto *Productos;
    Cadena Fichero;
};

int nAlmacenes = 0;
struct TAlmacenMemoria *Almacenes = NULL;

/**
 * Posicion Valida
 * 
 * Attr: pAlmacen
 * 
 * Devuelve verdadero si la posicion pasada se corresponde con un hueco del vector de almacenes cargados en memoria que está activo
 */
bool_t PosicionValida(int pAlmacen) {
    return pAlmacen < nAlmacenes && Almacenes[pAlmacen].nAbierto > 0;
}

/**
 * Buscar Almacen En Memoria
 * 
 * Attr: Fichero
 * 
 * Devuelve la posicion en la que se encuentra un almacen, buscado por el fichero en el que se guarda.
 * Solo se tienen en cuenta los huecos activos.
 * Devolverá -1 si no se encuentra.
 */
int BuscarAlmacenEnMemoria(Cadena Fichero) {
    int i = 0, pos = -1;

    while (pos == -1 && i < nAlmacenes) {
        printf("Comparo %d %s %s\n", i, Almacenes[i].Datos.Fichero, Fichero);
        if (Almacenes[i].nAbierto > 0 && strcmp(Almacenes[i].Datos.Fichero, Fichero) == 0)
            pos = i;
        else
            i++;
    }

    return pos;
}

/**
 * CargarAlmacenMemoria
 * 
 * Attr: Fichero
 * 
 * Intenta cargar un almacen desde un fichero en el primer hueco libre de memoria o uno nuevo. Devuelve la posición del vector en memoria en la que se ha guardado.
 */
int CargarAlmacenMemoria(Cadena Fichero) {
    //Abrir el fichero por nombre
    FILE *ptr = fopen(Fichero, "rb");

    //Si no se ha podido abrir el fichero, no existe y no se puede cargar
    if (ptr == NULL) return -1;

    //Buscar el espacio para guardar el almacen. Comenzando por buscar el primer hueco no activo de los almacenes en memoria
    int pAlmacen = 0;
    while (pAlmacen < nAlmacenes && Almacenes[pAlmacen].nAbierto > 0) pAlmacen++;
    printf("Lo cargo en la posicion %d\n", pAlmacen);

    //Si se han recorrido todos los almacenes sin encontrar ninguno libre, se agrega un espacio nuevo
    if (pAlmacen == nAlmacenes) {
        //Aumentar el numero de almacenes en memoria
        nAlmacenes++;
        //Cambiar el tamaño del vector
        struct TAlmacenMemoria *TmpAlmacenes = (struct TAlmacenMemoria *)realloc(Almacenes, sizeof(struct TAlmacenMemoria) * (nAlmacenes));
        if (TmpAlmacenes == NULL) {
            return -1;
        } else {
            Almacenes = TmpAlmacenes;
        }
    }
    //Este es el hueco en el que se cargaran los datos
    struct TAlmacenMemoria *Almacen = &Almacenes[pAlmacen];

    //Cargar la estructura de datos
    Almacen->nAbierto = 1;
    fread(&Almacen->nProductos, sizeof(int), 1, ptr);
    fread(Almacen->Datos.Nombre, sizeof(Cadena), 1, ptr);
    fread(Almacen->Datos.Direccion, sizeof(Cadena), 1, ptr);
    strcpy(Almacen->Datos.Fichero, Fichero);
    Almacen->Productos = malloc(sizeof(struct TProducto) * (Almacen->nProductos));
    for (int i = 0; i < Almacen->nProductos; i++) {
        fread(&Almacen->Productos[i], sizeof(struct TProducto), 1, ptr);
    }

    //Cerrar el fichero y devolver la posicion en la que se ha cargado el almacen
    fclose(ptr);
    return pAlmacen;
}

/**
 * Guardar Almacen Disco
 * 
 * Attr: pAlmacen
 * 
 * Guarda en un fichero la informacion de un almacen y sus productos
 */
bool_t GuardarAlmacenDisco(int pAlmacen) {
    //Comprobar si el numero es valido.
    //Guardar en su sitio, sobrescribieno si es necesario.
    //Si no se puede devolver falso
    if (!PosicionValida(pAlmacen)) {
        return FALSE;
    }
    struct TAlmacenMemoria *Almacen = &Almacenes[pAlmacen];
    FILE *ptr = fopen(Almacen->Datos.Fichero, "wb");
    if (ptr == NULL) return FALSE;

    fwrite(&Almacen->nProductos, sizeof(int), 1, ptr);
    fwrite(Almacen->Datos.Nombre, sizeof(Cadena), 1, ptr);
    fwrite(Almacen->Datos.Direccion, sizeof(Cadena), 1, ptr);
    for (int i = 0; i < Almacen->nProductos; i++) {
        fwrite(&Almacen->Productos[i], sizeof(struct TProducto), 1, ptr);
    }

    fclose(ptr);
    return TRUE;
}

/**
 * Buscar Producto
 * 
 * Attr: Almacen y CodProducto
 * 
 * Devuelve la posicion de un producto dentro de un almacen cargado en memoria
 * Busca el producto por su codigo
 * En caso de no encontrarlo, devuelve -1
 */
int BuscarProducto(int pAlmacen, Cadena CodProducto) {
    if (PosicionValida(pAlmacen)) {
        struct TAlmacenMemoria *Almacen = &Almacenes[pAlmacen];
        int i = 0, pos = -1;

        while (i < Almacen->nProductos && pos == -1) {
            if (strcmp(Almacen->Productos[i].CodProducto, CodProducto) == 0)
                pos = i;
            else
                i++;
        }

        return pos;
    } else {
        return -1;
    }
}

/**
 * Crear Almacen Disco
 * 
 * Attr: TDatosAlmacen (Nombre, Direccion, Fichero)
 * 
 * Crea un almacen en el disco.
 * La dirección del fichero es el atributo Fichero. El almacen guardado tiene los datos pasados por parametro y ningún producto.
 */
bool_t CrearAlmacenDisco(struct TDatosAlmacen *argp) {
    FILE *ptr = fopen(argp->Fichero, "wb");
    if (ptr == NULL) return FALSE;

    int nProductos = 0;
    fwrite(&nProductos, sizeof(int), 1, ptr);
    fwrite(argp->Nombre, sizeof(Cadena), 1, ptr);
    fwrite(argp->Direccion, sizeof(Cadena), 1, ptr);

    fclose(ptr);
    return TRUE;
}

/*******************
 *	FUNCIONES RPC
 *******************/

/**
 * Datos Almacen
 * 
 * Attr: int
 * 
 * Devolver los datos del almacen que se encuentra en una posición del vector en memoria
 */
TDatosAlmacen *datosalmacen_1_svc(int *argp, struct svc_req *rqstp) {
    static TDatosAlmacen result;
    int pAlmacen = *argp;

    //Comprobar que la posicion sea valida
    printf("La posicion %d es valida", pAlmacen);
    if (PosicionValida(pAlmacen)) {
        result = Almacenes[pAlmacen].Datos;
    } else {
        strcpy(result.Nombre, "NULL");
    }

    return &result;
}

/**
 * N Productos
 * 
 * Attr: int
 * 
 * Devuelve al cantidad de productos que tiene un almacen o -1 si el almacen no se puede encontrar
 */
int *nproductos_1_svc(int *argp, struct svc_req *rqstp) {
    static int result;
    int pAlmacen = *argp;

    if (PosicionValida(pAlmacen)) {
        //Si la posicion es valida, devolver la cantidad de productos que tiene
        result = Almacenes[pAlmacen].nProductos;
    } else {
        //Si la posicion no es valida, devolver -1
        result = -1;
    }

    return &result;
}

/**
 * Crear Almacen
 * 
 * Attr: TDatosAlmacen (Nombre, Direccion, Fichero)
 * 
 * Si existe un almacen en memoria con el mismo nombre de fichero, devuelve su posicion.
 * Si existe un almacen en el disco con el mismo nombre de fichero, lo carga en memoria y devuelve su posicion.
 * Si no existe ni en memoria ni en disco, crea un almacen vacío en el disco, lo carga en memoria y devuelve su posicion.
 */
int *crearalmacen_1_svc(TDatosAlmacen *argp, struct svc_req *rqstp) {
    static int result;

    //Buscar el almacen en la memoria
    result = BuscarAlmacenEnMemoria(argp->Fichero);
    //printf("Buscar en memoria: %d\n", result);
    if (result == -1) {
        //El almacen no está en memoria, se intenta cargar del disco
        result = CargarAlmacenMemoria(argp->Fichero);
        //printf("Cargar de disco: %d\n", result);
        if (result == -1) {
            //No se ha encontrado en el disco, se crea en el disco
            if (CrearAlmacenDisco(argp)) {
                //Cargarlo del disco
                result = CargarAlmacenMemoria(argp->Fichero);
                //printf("Cargar en memoria desde el disco: %d\n", result);
            } else {
                //No se ha podido crear
                result = -1;
            }
        }
    } else {
        //Si esta en memoria, hay que sumarle uno a la cantidad de usuarios que lo tienen abierto
        Almacenes[result].nAbierto++;
    }

    return &result;
}

/**
 * Abrir Almacen
 * 
 * Attr: Fichero
 * 
 * Carga un almacen en la primera posicion del vector libre o una nueva
 * El almacen se busca por su nombre de fichero
 * Devuelve -1 si la operación falla
 */
int *abriralmacen_1_svc(char *argp, struct svc_req *rqstp) {
    static int result;

    //Buscar si está en memoria
    printf("Intento buscarlo en memoria %s\n", argp);
    result = BuscarAlmacenEnMemoria(argp);
    if (result == -1) {
        //Si no se encuentra en memoria, se carga
        printf("No lo encuentro en memoria y los cargo\n");
        result = CargarAlmacenMemoria(argp);
    } else {
        //Si esta en memoria, hay que sumarle uno a la cantidad de usuarios que lo tienen abierto
        Almacenes[result].nAbierto++;
    }

    return &result;
}

/**
 * Guardar Almacen
 * 
 * Attr: int
 * 
 * Guarda el almacen de un hueco de memoria en su archivo en el disco
 * Si el numero no esta en el rango del vector, el hueco es invalido o hay un error en el guardado, devuelve -1
 */
bool_t *guardaralmacen_1_svc(int *argp, struct svc_req *rqstp) {
    static bool_t result;

    result = GuardarAlmacenDisco(*argp);

    return &result;
}

/**
 * Cerrar Almacen
 * 
 * Attr: int
 * 
 * Marca como disponible un hueco en el vector de almacenes cargados en memoria
 * Libera la memoria usada dinamica usada en ese hueco y lo deja listo para reutilizar
 * Si el hueco ya estaba libre o no es una posición válida devuelve false
 */
bool_t *cerraralmacen_1_svc(int *argp, struct svc_req *rqstp) {
    static bool_t result;
    int pAlmacen = *argp;

    if (PosicionValida(pAlmacen)) {
        //Solo se considera libre si no esta abierto por ningun cliente. Al quedar solo 1 el hueco se liberara
        if (Almacenes[pAlmacen].nAbierto == 1) {
            //Guardar el almacen antes de cerrarlo
            GuardarAlmacenDisco(pAlmacen);
            //Liverar la memoria en la que se guardan los productos
            free(Almacenes[pAlmacen].Productos);
        }
        //Reducir la cantidad de usuarios que lo tienen abierto
        Almacenes[pAlmacen].nAbierto--;
        //Operacion completada con exito
        result = TRUE;
    } else {
        result = FALSE;
    }

    return &result;
}

/**
 * Almacen Abierto
 * 
 * Attr: int
 * 
 * Devuelve verdadero si la posicion es valida, es decir, está dentro del array y no está libre.
 */
bool_t *almacenabierto_1_svc(int *argp, struct svc_req *rqstp) {
    static bool_t result;

    result = PosicionValida(*argp);

    return &result;
}

/**
 * Buscar Producto
 * 
 * Attr: TBusProd (pAlmacen, CodProducto)
 * 
 * Devuelve la posicion de un producto buscado por su codigo de producto, dentro de un almacen cargado en memoria, especificado por su posicion en el vector
 */
int *buscaproducto_1_svc(TBusProd *argp, struct svc_req *rqstp) {
    static int result;

    result = BuscarProducto(argp->Almacen, argp->CodProducto);

    return &result;
}

/**
 * Obtener Producto
 * 
 * TObtProd (pAlmacen, PosProducto)
 * 
 *  Devuelve los datos de un producto identificado por la posicion del almacen en memoria y la posicion del producto en el almacen
 */
struct TProducto *obtenerproducto_1_svc(TObtProd *argp, struct svc_req *rqstp) {
    static TProducto result;
    int pAlmacen = argp->Almacen;
    int pProducto = argp->PosProducto;

    //La posición del almacen y del producto debe ser válida
    if (PosicionValida(pAlmacen) && pProducto < Almacenes[pAlmacen].nProductos) {
        //Copia los datos del producto al espacio designado
        result = Almacenes[pAlmacen].Productos[pProducto];
    } else {
        //El producto no existe y se envian datos invalidos
        strcpy(result.CodProducto, "NULL");
    }

    return &result;
}

/**
 * Añadir Producto
 * 
 * Attr: TActProd (pAlmacen, Producto)
 * 
 * Agrega un producto a un almacen cargado en memoria identificado por su posicion
 * Devulve verdadero si la inserción se ha realizado correctamente
 */
bool_t *anadirproducto_1_svc(TActProd *argp, struct svc_req *rqstp) {
    static bool_t result;
    int pAlmacen = argp->Almacen;

    //Comprobar que el hueco sea válido y no exista un producto con el mismo codigo
    if (PosicionValida(pAlmacen) && BuscarProducto(argp->Almacen, argp->Producto.CodProducto) == -1) {
        struct TAlmacenMemoria *Almacen = &Almacenes[pAlmacen];

        //Agregar espacio para un producto más
        Almacen->nProductos++;
        struct TProducto *TmpProductos = (struct TProducto *)realloc(Almacen->Productos, sizeof(struct TProducto) * Almacen->nProductos);
        if (TmpProductos == NULL) {
            result = FALSE;
        } else {
            Almacen->Productos = TmpProductos;
            //Copiar los datos del nuevo producto al hueco reservado
            Almacen->Productos[Almacen->nProductos - 1] = argp->Producto;
            result = TRUE;
        }

    } else {
        result = FALSE;
    }

    return &result;
}

/**
 * Añadir Producto
 * 
 * Attr: TActProd (pAlmacen, Producto)
 * 
 * Actualiza los datos de un producto
 * El producto se identifica por la posicion en memoria en la que esta cargado el almacen y el codigo del producto en los nuevos datos
 * Devolvera verdadero si la actualizacion se completa correctamente
 * No insertara ningun producto si el codigo no se encuentra en el almacen
 */
bool_t *actualizarproducto_1_svc(TActProd *argp, struct svc_req *rqstp) {
    static bool_t result;

    //Buscar el producto por almacen y código de producto
    int PosProd = BuscarProducto(argp->Almacen, argp->Producto.CodProducto);
    if (PosProd == -1) {
        result = FALSE;
    } else {
        //Si el producto existe, se vuelcan los nuevos datos en su posición
        Almacenes[argp->Almacen].Productos[PosProd] = argp->Producto;
        result = TRUE;
    }

    return &result;
}

/**
 * Eliminar Producto
 * 
 * Attr: TBusProd (pAlmacen, CodProducto)
 * 
 * Elima un producto de un almacen cargado en memoria identificado por al posicion del almacen en memoria y el codigo de producto
 * Si el producto no se encuentra o no se ha podido eliminar, devolvera falso
 */
bool_t *eliminarproducto_1_svc(TBusProd *argp, struct svc_req *rqstp) {
    static bool_t result;

    //Buscar el producto por almacen y código de producto
    int PosProd = BuscarProducto(argp->Almacen, argp->CodProducto);
    if (PosProd == -1) {
        result = FALSE;
    } else {
        struct TAlmacenMemoria *Almacen = &Almacenes[argp->Almacen];
        //Desplazar todos los productos posteriores al que se va a eliminar
        for (int i = PosProd + 1; i < Almacen->nProductos; i++) {
            Almacen->Productos[i - 1] = Almacen->Productos[i];
        }
        //Eliminar el espacio de un producto
        Almacen->nProductos--;
        struct TProducto *TmpProductos = (struct TProducto *)realloc(Almacen->Productos, sizeof(struct TProducto) * Almacen->nProductos);
        if (TmpProductos == NULL) {
            result = FALSE;
        } else {
            Almacen->Productos = TmpProductos;
            result = TRUE;
        }
    }

    return &result;
}