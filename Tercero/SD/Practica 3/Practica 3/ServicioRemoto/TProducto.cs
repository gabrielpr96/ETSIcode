using System;

namespace ServicioRemoto {
    public class TProducto : MarshalByRefObject {
        private readonly String CodProducto, NombreProducto, Descripcion;
        private readonly float Precio;
        private readonly int Cantidad;
        private readonly TFecha Caducidad;

        public TProducto(String CodProducto, String NombreProducto, String Descripcion, float Precio, int Cantidad, TFecha Caducidad) {
            this.CodProducto = CodProducto;
            this.NombreProducto = NombreProducto;
            this.Descripcion = Descripcion;
            this.Precio = Precio;
            this.Cantidad = Cantidad;
            this.Caducidad = Caducidad;
        }

        public String getCodProducto() {
            return CodProducto;
        }

        public String getNombreProducto() {
            return NombreProducto;
        }

        public String getDescripcion() {
            return Descripcion;
        }

        public float getPrecio() {
            return Precio;
        }

        public int getCantidad() {
            return Cantidad;
        }

        public TFecha getCaducidad() {
            return Caducidad;
        }

        public String toString() {
            return NombreProducto;
        }
    }
}
