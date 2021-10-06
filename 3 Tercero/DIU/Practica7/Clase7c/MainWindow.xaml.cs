using System.Windows;
using System.Windows.Controls;

namespace Clase7c {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void valorCambiado(object sender, RoutedPropertyChangedEventArgs<double> e) {
            Slider slider = sender as Slider;
            if(slider != null) {
                texto.FontSize = slider.Value;
                texto.Text = "Texto escrito en " + slider.Value + " puntos.";
            }
        }
    }
}
