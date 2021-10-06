using System.Windows;
using System.Windows.Controls;

namespace OctabaAplicacion {
    /// <summary>
    /// Lógica de interacción para Profesores.xaml
    /// </summary>
    public partial class Profesores : Page {
        PaginaWeb web;
        public Profesores() {
            InitializeComponent();
            web = new PaginaWeb();
        }

        private void onAbrirWeb(object sender, RoutedEventArgs e) {
            MenuItem menuItem = sender as MenuItem;
            if(menuItem != null) {
                web.Direccion = menuItem.Tag.ToString();
                NavigationService.Navigate(web);
                //System.Diagnostics.Process.Start(menuItem.Tag.ToString());
            }
        }
    }
}
