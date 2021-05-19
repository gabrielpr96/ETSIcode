using System.Windows;
using System.Windows.Controls;

namespace SeptimaAplicacion {
    /// <summary>
    /// Lógica de interacción para Profesores.xaml
    /// </summary>
    public partial class Profesores : Window {
        public Profesores() {
            InitializeComponent();
        }

        private void onAbrirWeb(object sender, RoutedEventArgs e) {
            MenuItem menuItem = sender as MenuItem;
            if(menuItem != null) {
                System.Diagnostics.Process.Start(menuItem.Tag.ToString());
            }
        }
    }
}
