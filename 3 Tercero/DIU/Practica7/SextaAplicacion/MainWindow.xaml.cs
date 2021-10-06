using System.Windows;
using System.Windows.Controls.Primitives;
using System.Windows.Media;

namespace SextaAplicacion {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void slidersCambiados(object sender, RoutedPropertyChangedEventArgs<double> e) {
            Color color = Color.FromRgb((byte)sliderRojo.Value, (byte)sliderVerde.Value, (byte)sliderAzul.Value);
            pantalla.Background = new SolidColorBrush(color);
            valorRojo.Text = "Valor = " + sliderRojo.Value;
            valorVerde.Text = "Valor = " + sliderVerde.Value;
            valorAzul.Text = "Valor = " + sliderAzul.Value;

        }
        private void chequeado(object sender, RoutedEventArgs e) {
            ToggleButton boton = sender as ToggleButton;
            if(boton != null) {
                boton.Content = "Visualizar";
                boton.Background = new SolidColorBrush(Colors.LightBlue);
                pantalla.Opacity = 0;
            }
        }

        private void unchequeado(object sender, RoutedEventArgs e) {
            ToggleButton boton = sender as ToggleButton;
            if(boton != null) {
                boton.Content = "Borrar";
                boton.Background = new SolidColorBrush(Colors.Yellow);
                pantalla.Opacity = 1;
            }
        }

    }
}
