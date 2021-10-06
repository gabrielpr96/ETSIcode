using System.Windows;
using System.Windows.Controls.Primitives;

namespace Clase7b {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void chequeado(object sender, RoutedEventArgs e) {
            ToggleButton boton = sender as ToggleButton;
            if(boton != null) {
                boton.Content = "ACTIVADO";
            }
        }

        private void unchequeado(object sender, RoutedEventArgs e) {
            ToggleButton boton = sender as ToggleButton;
            if(boton != null) {
                boton.Content = "DESACTIVADO";
            }
        }
    }
}
