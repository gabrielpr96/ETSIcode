using System.Windows;

namespace Clase9 {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void onMinimizar(object sender, RoutedEventArgs e) {
            WindowState = WindowState.Minimized;
        }

        private void onCerar(object sender, RoutedEventArgs e) {
            Application.Current.Shutdown();
        }
    }
}
