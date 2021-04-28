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

namespace Clase6
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

        private void onMouseClick(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Has hecho click sobre el Button");
            Button boton = sender as Button;
            if (boton != null)
                boton.Width = boton.Width * .5f;
        }

        private void onRightDown(object sender, MouseButtonEventArgs e)
        {
            Button boton = sender as Button;
            if(boton != null)
            {
                string texto = (string) boton.Content;
                boton.Content = texto == "Una vez" ? "Otra vez" : "Una vez";
                boton.Width = boton.Width * 2f;
            }
        }
    }
}
