using System;

namespace ServicioRemoto {
    public class TDatosAlmacen : MarshalByRefObject {
        private readonly String Nombre, Direccion, Fichero;

        public TDatosAlmacen(String Nombre, String Direccion, String Fichero) {
            this.Nombre = Nombre;
            this.Direccion = Direccion;
            this.Fichero = Fichero;
        }

        public String getNombre() {
            return Nombre;
        }

        public String getDireccion() {
            return Direccion;
        }

        public String getFichero() {
            return Fichero;
        }
    }
}
