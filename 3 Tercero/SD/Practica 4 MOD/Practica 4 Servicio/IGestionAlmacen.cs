using System;
using System.Runtime.Serialization;
using System.ServiceModel;

namespace Practica_4_Servicio {
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de interfaz "IService1" en el código y en el archivo de configuración a la vez.
    [ServiceContract]
    public interface IGestionAlmacen {
        [OperationContract]
        TDatosAlmacen DatosAlmacen(int pAlmacen);

        [OperationContract]
        int NProductos(int pAlmacen);

        [OperationContract]
        int CrearAlmacen(String pNombre, String pDireccion, String pNomFichero);

        [OperationContract]
        int AbrirAlmacen(String pNomFichero);

        [OperationContract]
        Boolean GuardarAlmacen(int pAlmacen);

        [OperationContract]
        Boolean CerrarAlmacen(int pAlmacen);

        [OperationContract]
        Boolean AlmacenAbierto(int pAlmacen);

        [OperationContract]
        int BuscaProducto(int pAlmacen, String pCodProducto);

        [OperationContract]
        TProducto ObtenerProducto(int pAlmacen, int pPosProducto);

        [OperationContract]
        Boolean AnadirProducto(int pAlmacen, TProducto pProdNuevo);

        [OperationContract]
        Boolean ActualizarProducto(int pAlmacen, TProducto pProducto);

        [OperationContract]
        Boolean EliminarProducto(int pAlmacen, String pCodProducto);

        [OperationContract]
        int EliminarProductosCaducados(int pAlmacen, int pDia, int pMes, int pAnio);
    }

    [DataContract]
    public class TDatosAlmacen {
        string nombre, direccion, fichero;

        [DataMember]
        public string Nombre {
            get { return nombre; }
            set { nombre = value; }
        }

        [DataMember]
        public string Direccion {
            get { return direccion; }
            set { direccion = value; }
        }

        [DataMember]
        public string Fichero {
            get { return fichero; }
            set { fichero = value; }
        }
    }

    [DataContract]
    public class TFecha {
        int dia, mes, anyo;

        [DataMember]
        public int Dia {
            get { return dia; }
            set { dia = value; }
        }

        [DataMember]
        public int Mes {
            get { return mes; }
            set { mes = value; }
        }

        [DataMember]
        public int Anyo {
            get { return anyo; }
            set { anyo = value; }
        }
    }

    [DataContract]
    public class TProducto {
        string codProducto, nombreProducto, descripcion;
        float precio;
        int cantidad;
        TFecha caducidad;

        [DataMember]
        public string CodProducto {
            get { return codProducto; }
            set { codProducto = value; }
        }

        [DataMember]
        public string NombreProducto {
            get { return nombreProducto; }
            set { nombreProducto = value; }
        }

        [DataMember]
        public string Descripcion {
            get { return descripcion; }
            set { descripcion = value; }
        }

        [DataMember]
        public float Precio {
            get { return precio; }
            set { precio = value; }
        }

        [DataMember]
        public int Cantidad {
            get { return cantidad; }
            set { cantidad = value; }
        }
        [DataMember]
        public TFecha Caducidad {
            get { return caducidad; }
            set { caducidad = value; }
        }
    }
}
