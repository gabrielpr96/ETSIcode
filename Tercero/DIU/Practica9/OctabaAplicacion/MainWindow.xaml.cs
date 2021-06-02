using System.Windows;
using System.Windows.Input;

namespace OctabaAplicacion {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void Salir(object sender, MouseButtonEventArgs e) {
            Application.Current.Shutdown();
        }
    }
}
