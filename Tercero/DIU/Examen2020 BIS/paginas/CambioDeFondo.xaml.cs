using System.Windows;
using System.Windows.Controls;

namespace Examen2020_BIS.paginas {
    /// <summary>
    /// Lógica de interacción para CambioDeFondo.xaml
    /// </summary>
    public partial class CambioDeFondo : Page {
        public CambioDeFondo() {
            InitializeComponent();
        }

        private void cambiarFondo(object sender, RoutedEventArgs e) {
            RadioButton rb = sender as RadioButton;
            if(rb != null) {
                Background = rb.Background;
            }
        }

        private void irACuestionario(object sender, System.Windows.Input.MouseButtonEventArgs e) {
            NavigationService.Navigate(new Cuestionario());
        }
    }
}
