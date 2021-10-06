using System.Windows;
using System.Windows.Controls.Primitives;

namespace Clase7d {
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
                boton.Content = "VER";
                progress.Opacity = 1;
            }
        }

        private void unchequeado(object sender, RoutedEventArgs e) {
            ToggleButton boton = sender as ToggleButton;
            if(boton != null) {
                boton.Content = "NO VER";
                progress.Opacity = 0;
            }
        }
    }
}
