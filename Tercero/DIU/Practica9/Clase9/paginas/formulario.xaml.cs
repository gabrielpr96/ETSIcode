using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace Clase9.paginas {
    /// <summary>
    /// Lógica de interacción para formulario.xaml
    /// </summary>
    public partial class formulario : Page {
        public formulario() {
            InitializeComponent();
        }

        private void comprobar(object sender, RoutedEventArgs e) {
            TextBox textBox = sender as TextBox;
            if(textBox != null) {
                if(textBox.Text.Length < 4) {
                    MessageBox.Show("Error al menos cuatro caracteres");
                }
                textBox.Background = Brushes.White;
            }
        }

        private void entrada(object sender, MouseEventArgs e) {
            TextBox textBox = sender as TextBox;
            if(textBox != null) {
                textBox.Cursor = Cursors.Pen;
                textBox.Background = Brushes.LightBlue;
            }
        }

        private void borrar(object sender, MouseEventArgs e) {

        }

        private void validar(object sender, TextCompositionEventArgs e) {
            int n = Convert.ToInt32(Convert.ToChar(e.Text));
            e.Handled = n < 48 || n > 57;
        }
    }
}
