using Examen2020.paginas;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Examen2020
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void salir(object sender, MouseButtonEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void regresarPrincipal(object sender, MouseButtonEventArgs e)
        {
            Contenedor.Navigate(new PaginaRoja());
        }
    }
}
