using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace Clase9.paginas {
    /// <summary>
    /// Lógica de interacción para paginaazul.xaml
    /// </summary>
    public partial class paginaazul : Page {
        public paginaazul() {
            InitializeComponent();
        }

        private void accesoFormulario(object sender, MouseButtonEventArgs e) {
            Image image = sender as Image;
            if(image != null) {
                int x = (int)e.GetPosition(image).X;
                int y = (int)e.GetPosition(image).Y;//142 198 209 267
                if(x > 142 && x < 209 && y > 198 && y < 267) {
                    NavigationService.Navigate(new formulario());
                } else {
                    Application.Current.Shutdown();
                }
            }
        }
    }
}
