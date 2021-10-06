namespace OctabaAplicacion {
    class Usuario {
        private readonly string Nombre, Pass;
        public Usuario(string nombre, string pass) {
            Nombre = nombre;
            Pass = pass;
        }
        public bool comprobar(string pass) {
            return Pass == pass;
        }

        override
        public string ToString() {
            return Nombre;
        }
    }
}
