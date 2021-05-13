using System.Windows;
using System.Windows.Controls;
using Clase8c.src;

namespace Clase8c {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void siClick(object sender, RoutedEventArgs e) {
            MenuItem menuItem = sender as MenuItem;
            if(menuItem != null) {
                MessageBox.Show("Has elegido: " + menuItem.Header, "Selección");
            }
        }

        private void clickRojo(object sender, RoutedEventArgs e) {
            Rojo r = new Rojo();
            r.ShowDialog();
        }
    }
}
