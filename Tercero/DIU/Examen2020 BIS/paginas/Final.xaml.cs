using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;

namespace Examen2020_BIS.paginas {
    /// <summary>
    /// Lógica de interacción para Final.xaml
    /// </summary>
    public partial class Final : Page {
        public Final() {
            InitializeComponent();
        }

        private void mostrarCoordenadas(object sender, RoutedEventArgs e) {
            ToggleButton tb = sender as ToggleButton;
            if(tb != null) {
                tb.Content = "Ocultar";
                CordText.Visibility = Visibility.Visible;
            }
        }

        private void ocultarCoordenadas(object sender, RoutedEventArgs e) {
            ToggleButton tb = sender as ToggleButton;
            if(tb != null) {
                tb.Content = "Mostrar";
                CordText.Visibility = Visibility.Hidden;
            }
        }

        private void actualizarCords(object sender, System.Windows.Input.MouseEventArgs e) {
            CordText.Text = "[" + e.GetPosition(sender as Grid).X + ", " + e.GetPosition(sender as Grid).Y + "]";
        }
    }
}
