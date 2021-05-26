using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;

namespace Clase9.paginas {
    /// <summary>
    /// Lógica de interacción para paginaroja.xaml
    /// </summary>
    public partial class paginaroja : Page {
        public paginaroja() {
            InitializeComponent();
        }

        private void onClick(object sender, RoutedEventArgs e) {
            NavigationService.Navigate(new paginaazul());
        }
    }
}
