using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace Examen2020_BIS.paginas {
    /// <summary>
    /// Lógica de interacción para Cuestionario.xaml
    /// </summary>
    public partial class Cuestionario : Page {
        public Cuestionario() {
            InitializeComponent();
            Paises = new List<String>();
            DataContext = Paises;
            try {
                StreamReader sr = new StreamReader("../../src/paises.txt");
                String linea = sr.ReadLine();
                while(linea != null) {
                    Paises.Add(linea);
                    linea = sr.ReadLine();
                }
                sr.Close();
            } catch(Exception ex) {
                Paises.Add("Error");
            }
        }

        private List<String> paises;

        public List<String> Paises {
            get { return paises; }
            set { paises = value; }
        }


        private void entraRaton(object sender, MouseEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                tb.Background = Brushes.LightBlue;
            } else {
                PasswordBox pb = sender as PasswordBox;
                if(pb != null) {
                    pb.Background = Brushes.LightBlue;
                }
            }
        }

        private void saleRaton(object sender, MouseEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                tb.Background = Brushes.White;
            } else {
                PasswordBox pb = sender as PasswordBox;
                if(pb != null) {
                    pb.Background = Brushes.White;
                }
            }
        }

        private void nombreComprobar(object sender, TextChangedEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                if(tb.Text.Length >= 3 && tb.Text.Length <= 10)
                    tb.Foreground = Brushes.Black;
                else
                    tb.Foreground = Brushes.Red;
            }
        }

        private void edadComprobar(object sender, TextChangedEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                try {
                    int edad = Convert.ToInt32(tb.Text);
                    if(edad < 18 || edad > 100)
                        throw new Exception();
                    tb.Foreground = Brushes.Black;
                } catch(Exception ex) {
                    tb.Foreground = Brushes.Red;
                }
            }
        }

        private void comprobarContrasena(object sender, TextCompositionEventArgs e) {
            PasswordBox tb = sender as PasswordBox;
            if(tb != null) {
                int c = Convert.ToInt32(Convert.ToChar(e.Text));
                if(c >= 48 && c <= 57) {
                    e.Handled = false;
                } else {
                    e.Handled = true;
                }
            }
        }

        private void mostrarContrasena(object sender, MouseButtonEventArgs e) {
            ClearText.Text = PassText.Password;
            ClearText.Visibility = System.Windows.Visibility.Visible;
            PassText.Visibility = System.Windows.Visibility.Collapsed;
        }

        private void ocultarContrasena(object sender, MouseButtonEventArgs e) {
            ClearText.Visibility = System.Windows.Visibility.Collapsed;
            PassText.Visibility = System.Windows.Visibility.Visible;
        }

        private void cambioSuelto(object sender, System.Windows.RoutedPropertyChangedEventArgs<double> e) {
            Slider s = sender as Slider;
            if(s != null) {
                SalarioText.Text = e.NewValue + "€";
            }
        }

        private void navegarFinal(object sender, System.Windows.RoutedEventArgs e) {
            NavigationService.Navigate(new Final());
        }
    }
}
