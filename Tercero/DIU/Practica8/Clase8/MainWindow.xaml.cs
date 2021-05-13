using System.Windows;
using System.Windows.Controls;

namespace Clase8 {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void seleccionado(object sender, SelectionChangedEventArgs e) {
            ListBox lista = sender as ListBox;
            if(lista != null) {
                MessageBox.Show("Has seleccionado: " + (lista.SelectedIndex == 0 ? "UNO" : (lista.SelectedIndex == 1 ? "DOS" : (lista.SelectedIndex == 2 ? "TRES" : "CUATRO"))), "Seleccionado");
            }
        }

        private void siClick(object sender, RoutedEventArgs e) {
            Button boton = sender as Button;
            if(boton != null) {
                lista.SelectedIndex = (lista.SelectedIndex + 1) % 4;
            }
        }
    }
}
