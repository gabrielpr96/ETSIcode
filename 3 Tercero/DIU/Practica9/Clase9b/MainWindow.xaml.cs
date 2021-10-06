using System.Collections.Generic;
using System.IO;
using System.Windows;

namespace Clase9b {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
            DataContext = this;
            Texto = "Hola";
            Lista = new List<string>();
            Lista.Add("Cadiz");
            Lista.Add("Cordoba");
            Lista.Add("Huelva");
            Lista.Add("Sevilla");

            StreamReader sr = new StreamReader("../../src/paises.txt");
            string linea = sr.ReadLine();
            while(linea != null) {
                lista.Add(linea);
                linea = sr.ReadLine();
            }
            sr.Close();
        }

        private string texto;

        public string Texto {
            get { return texto; }
            set { texto = value; }
        }

        private List<string> lista;

        public List<string> Lista {
            get { return lista; }
            set { lista = value; }
        }

    }
}
