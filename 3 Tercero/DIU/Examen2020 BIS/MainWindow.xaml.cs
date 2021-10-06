using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using Examen2020_BIS.paginas;

namespace Examen2020_BIS {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void sombrearSalir(object sender, MouseEventArgs e) {
            Border b = sender as Border;
            if(b != null) {
                b.Background = Brushes.PaleTurquoise;
            }
        }

        private void desSombrearSalir(object sender, MouseEventArgs e) {
            Border b = sender as Border;
            if(b != null) {
                b.Background = Brushes.White;
            }
        }

        private void salir(object sender, MouseButtonEventArgs e) {
            Application.Current.Shutdown();
        }

        private void sombrearLogo(object sender, MouseEventArgs e) {
            Border b = sender as Border;
            if(b != null) {
                b.BorderBrush = Brushes.Black;
            }
        }

        private void desSombrearLogo(object sender, MouseEventArgs e) {
            Border b = sender as Border;
            if(b != null) {
                b.BorderBrush = Brushes.Transparent;
            }
        }

        private void navegarLogo(object sender, MouseButtonEventArgs e) {
            Navegador.Navigate(new CambioDeFondo());
        }
    }
}
