using System;
using System.Windows.Controls;

namespace OctabaAplicacion {
    /// <summary>
    /// Lógica de interacción para PaginaWeb.xaml
    /// </summary>
    public partial class PaginaWeb : Page {

        public PaginaWeb() {
            InitializeComponent();
        }


        private string direccion;
        public string Direccion {
            get { return direccion; }
            set {
                direccion = value;
                Webview.Source = new Uri(value);
            }
        }
    }
}
