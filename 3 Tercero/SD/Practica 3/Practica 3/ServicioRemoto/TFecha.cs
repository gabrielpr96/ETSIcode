using System;

namespace ServicioRemoto {
    public class TFecha : MarshalByRefObject {
        private readonly int Dia, Mes, Anyo;

        public TFecha(int Dia, int Mes, int Anyo) {
            this.Dia = Dia;
            this.Mes = Mes;
            this.Anyo = Anyo;
        }

        public int getDia() {
            return Dia;
        }

        public int getMes() {
            return Mes;
        }

        public int getAnyo() {
            return Anyo;
        }

        public String toString() {
            return Dia + "/" + Mes + "/" + Anyo;
        }
    }
}
