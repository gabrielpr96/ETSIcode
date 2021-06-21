using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Navigation;

namespace Examen2020.paginas {
    /// <summary>
    /// Lógica de interacción para PaginaRoja.xaml
    /// </summary>
    public partial class PaginaRoja : Page {
        public PaginaRoja() {
            InitializeComponent();
        }

        private void irAVerde(object sender, MouseButtonEventArgs e) {
            NavigationService.Navigate(new PaginaVerde());
        }

        private void cambiarFondo(object sender, RoutedEventArgs e) {
            RadioButton radio = sender as RadioButton;
            if(radio != null) {
                var converter = new System.Windows.Media.BrushConverter();
                Background = (Brush)converter.ConvertFromString(radio.Tag.ToString());
            }
        }

        private void cambiarPosicion(object sender, MouseEventArgs e) {
            Grid g = sender as Grid;
            if(g != null) {
                PosicionRaton.Text = "[" + e.GetPosition(g).X + ", " + e.GetPosition(g).Y + "]";
            }
        }

        private void ocultarCoordenadas(object sender, RoutedEventArgs e) {
            ToggleButton btn = sender as ToggleButton;
            if(btn != null) {
                btn.Content = "Mostrar coordenadas";
                PosicionRaton.Visibility = Visibility.Collapsed;
            }
        }

        private void mostrarCoordenadas(object sender, RoutedEventArgs e) {
            ToggleButton btn = sender as ToggleButton;
            if(btn != null) {
                btn.Content = "Ocultar coordenadas";
                PosicionRaton.Visibility = Visibility.Visible;
            }
        }
    }
}
