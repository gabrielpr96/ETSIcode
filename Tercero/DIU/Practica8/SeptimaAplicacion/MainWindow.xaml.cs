using System.Windows;
using System.Windows.Controls;

namespace SeptimaAplicacion {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void onEnviar(object sender, RoutedEventArgs e) {
            Button boton = sender as Button;
            if(boton != null) {
                Window w;
                if(Pass.Password.Equals("Interfaces")) {
                    w = new Profesores();
                } else {
                    w = new Alumnos();
                }
                w.ShowDialog();
            }
        }
    }
}
