//TODO: Preguntar si se deberia buscar e indicar almacenes por su Fichero en lugar de por su Nombre

#include "GestorAlmacenes.h"
#include "string.h"

struct TAlmacenMemoria {
    int nAbierto;
    TDatosAlmacen Datos;
    int nProductos;
    TProducto **Productos;
    Cadena Fichero;
};

int nAlmacenesMemoria = 0;
struct TAlmacenMemoria **Almacenes = NULL;

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

    while (pos == -1 && i < nAlmacenesMemoria) {
        printf("Comparo %d %s %s\n", i, Almacenes[i]->Datos.Fichero, Fichero);
        if (Almacenes[i]->nAbierto > 0 && strcmp(Almacenes[i]->Datos.Fichero, Fichero) == 0)
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
    while (pAlmacen < nAlmacenesMemoria && Almacenes[pAlmacen]->nAbierto > 0) pAlmacen++;
    printf("Lo cargo en la posicion %d\n", pAlmacen);

    //Si se han recorrido todos los almacenes sin encontrar ninguno libre, se agrega un espacio nuevo
    if (pAlmacen == nAlmacenesMemoria) {
        //Crear un nuevo vector de punteros
        struct TAlmacenMemoria **NuevosAlmacenes = malloc(sizeof(struct TAlmacenMemoria *) * (nAlmacenesMemoria + 1));
        //Copiar los almacenes en memoria ya existentes
        for (int i = 0; i < nAlmacenesMemoria; i++) {
            NuevosAlmacenes[i] = Almacenes[i];
        }
        //Crear el espacio para el nuevo elemento
        NuevosAlmacenes[pAlmacen] = malloc(sizeof(struct TAlmacenMemoria));
        //Aumentar el numero de almacenes en memoria
        nAlmacenesMemoria++;
        //Liverar la estructura anterior
        if (Almacenes != NULL)
            free(Almacenes);
        //Remplazar la estructura por la nueva
        Almacenes = NuevosAlmacenes;
    }
    //Este es el hueco en el que se cargaran los datos
    struct TAlmacenMemoria *Almacen = Almacenes[pAlmacen];

    //Cargar la estructura de datos
    Almacen->nAbierto = 1;
    fread(&Almacen->nProductos, sizeof(int), 1, ptr);
    fread(Almacen->Datos.Nombre, sizeof(Cadena), 1, ptr);
    fread(Almacen->Datos.Direccion, sizeof(Cadena), 1, ptr);
    strcpy(Almacen->Datos.Fichero, Fichero);
    Almacen->Productos = malloc(sizeof(struct TProducto *) * (Almacen->nProductos));
    for (int i = 0; i < Almacen->nProductos; i++) {
        Almacen->Productos[i] = malloc(sizeof(struct TProducto));
        fread(Almacen->Productos[i], sizeof(struct TProducto), 1, ptr);
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
    struct TAlmacenMemoria *Almacen = Almacenes[pAlmacen];
    FILE *ptr = fopen(Almacen->Datos.Fichero, "wb");
    if (ptr == NULL) return FALSE;

    fwrite(&Almacen->nProductos, sizeof(int), 1, ptr);
    fwrite(Almacen->Datos.Nombre, sizeof(Cadena), 1, ptr);
    fwrite(Almacen->Datos.Direccion, sizeof(Cadena), 1, ptr);
    for (int i = 0; i < Almacen->nProductos; i++) {
        fwrite(Almacen->Productos[i], sizeof(struct TProducto), 1, ptr);
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
        struct TAlmacenMemoria *Almacen = Almacenes[pAlmacen];
        int i = 0, pos = -1;

        while (i < Almacen->nProductos && pos == -1) {
            if (strcmp(Almacen->Productos[i]->CodProducto, CodProducto) == 0)
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

/**
 * Posicion Valida
 * 
 * Attr: pAlmacen
 * 
 * Devuelve verdadero si la posicion pasada se corresponde con un hueco del vector de almacenes cargados en memoria que está activo
 */
bool_t PosicionValida(int pAlmacen) {
    return pAlmacen < nAlmacenesMemoria && Almacenes[pAlmacen]->nAbierto > 0;
}

void CopiarProducto(struct TProducto *destino, struct TProducto *origen) {
    strcpy(destino->CodProducto, origen->CodProducto);
    destino->Cantidad = origen->Cantidad;
    strcpy(destino->NombreProducto, origen->NombreProducto);
    destino->Precio = origen->Precio;
    strcpy(destino->Descripcion, origen->Descripcion);
    destino->Caducidad.Dia = origen->Caducidad.Dia;
    destino->Caducidad.Mes = origen->Caducidad.Mes;
    destino->Caducidad.Anyo = origen->Caducidad.Anyo;
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
        strcpy(result.Nombre, Almacenes[pAlmacen]->Datos.Nombre);
        strcpy(result.Direccion, Almacenes[pAlmacen]->Datos.Direccion);
        strcpy(result.Fichero, Almacenes[pAlmacen]->Datos.Fichero);
    } else {
        strcpy(result.Nombre, "NULL");
        strcpy(result.Direccion, "NULL");
        strcpy(result.Fichero, "NULL");
        //return NULL;
    }
    //TODO: Preguntar si se puede copiar el struct entero
    //TODO: Preguntar como hacer saber que algo ha fallado si no se puede devolver NULL

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
        result = Almacenes[pAlmacen]->nProductos;
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
        Almacenes[result]->nAbierto++;
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
        Almacenes[result]->nAbierto++;
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
        if (Almacenes[pAlmacen]->nAbierto == 1) {
            //Guardar el almacen antes de cerrarlo
            GuardarAlmacenDisco(pAlmacen);
            //Liverar la memoria en la que se guardan los productos
            for (int i = 0; i < Almacenes[pAlmacen]->nProductos; i++) {
                free(Almacenes[pAlmacen]->Productos[i]);
            }
            free(Almacenes[pAlmacen]->Productos);
            //Operacion completada con exito
        }
        //Reducir la cantidad de usuarios que lo tienen abierto
        Almacenes[pAlmacen]->nAbierto--;
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
    if (PosicionValida(pAlmacen) && pProducto < Almacenes[pAlmacen]->nProductos) {
        //Copia los datos del producto al espacio designado
        CopiarProducto(&result, Almacenes[pAlmacen]->Productos[pProducto]);
        //TODO: Preguntar si se puede devolver una referencia a producto en lugar de copiar los datos
    } else {
        //El producto no existe y se envian datos invalidos
        strcpy(result.CodProducto, "NULL");
        result.Cantidad = 0;
        strcpy(result.NombreProducto, "NULL");
        result.Precio = 0;
        strcpy(result.Descripcion, "NULL");
        result.Caducidad.Dia = 0;
        result.Caducidad.Mes = 0;
        result.Caducidad.Anyo = 0;
        //return NULL;
        //TODO: Preguntar como podria hacer algo parecido a devolver null
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
        struct TAlmacenMemoria *Almacen = Almacenes[pAlmacen];
        struct TProducto *Producto;
        if (Almacen->nProductos == 0) {
            //Si no tiene productos, creamos un nuevo vector de un solo elemenmto
            Almacen->Productos = malloc(sizeof(struct TProducto *));
            Producto = Almacen->Productos[0] = malloc(sizeof(struct TProducto));
        } else {
            //Reservar un nuevo espacio con un hueco más
            struct TProducto **Productos = malloc(sizeof(struct TProducto *) * (Almacen->nProductos + 1));
            //Espacio para el nuevo producto
            Producto = Productos[Almacen->nProductos] = malloc(sizeof(struct TProducto));
            //Copiar los elementos presentes
            for (int i = 0; i < Almacen->nProductos; i++) {
                Productos[i] = Almacen->Productos[i];
            }
            //Liberar el vector anterior e intercambiarlo
            free(Almacen->Productos);
            Almacen->Productos = Productos;
        }
        Almacen->nProductos++;

        //Copiar los datos del nuevo producto al hueco reservado
        CopiarProducto(Producto, &(argp->Producto));

        result = TRUE;
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
        CopiarProducto(Almacenes[argp->Almacen]->Productos[PosProd], &(argp->Producto));
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
        struct TAlmacenMemoria *Almacen = Almacenes[argp->Almacen];
        //Crear un nuevo espacio para los productos con un hueco menos
        struct TProducto **Productos = malloc(sizeof(struct TProducto *) * (Almacen->nProductos - 1));
        //Copiar los productos antes del que se pretende eliminar
        int i;
        for (i = 0; i < PosProd; i++) {
            Productos[i] = Almacen->Productos[i];
        }
        //Liberar la memoria del producto que se elimina
        free(Almacen->Productos[i]);
        //Avanzar una posicion y continuar copiando al nuevo vector
        i++;
        for (; i < Almacen->nProductos; i++) {
            Productos[i - 1] = Almacen->Productos[i];
        }
        Almacen->nProductos--;
        //Liverar la estructura anterior y reemplazarla
        free(Almacen->Productos);
        Almacen->Productos = Productos;
        result = TRUE;
    }
    //TODO: malloc de 0 bytes devuelve NULL?

    return &result;
}