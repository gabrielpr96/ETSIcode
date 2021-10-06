using System;
using System.Collections.Generic;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Navigation;

namespace Examen2020.paginas {
    /// <summary>
    /// Lógica de interacción para PaginaVerde.xaml
    /// </summary>
    public partial class PaginaVerde : Page {
        public PaginaVerde() {
            InitializeComponent();
            DataContext = this;

            sabores = new List<String>();

            try {
                StreamReader sr = new StreamReader("../../src/sabores.txt");
                string linea = sr.ReadLine();
                while(linea != null) {
                    sabores.Add(linea);
                    linea = sr.ReadLine();
                }
                sr.Close();
            } catch(Exception ex) {
                Console.WriteLine(ex.Message);
            }
        }

        private List<String> sabores;

        public List<String> Sabores {
            get { return sabores; }
            set { sabores = value; }
        }

        private void irARoja(object sender, MouseButtonEventArgs e) {
            NavigationService.Navigate(new PaginaRoja());
        }

        private void longitudCheck(object sender, TextChangedEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                if(tb.Text.Length >= 4 && tb.Text.Length <= 6) {
                    LongitudText.Text = "Ok";
                } else {
                    LongitudText.Text = "Error";
                }
            }
        }

        private void digitoCheck(object sender, TextCompositionEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                int c = Convert.ToInt32(Convert.ToChar(e.Text));
                if(c >= 48 && c <= 57) {
                    DigitosText.Text = "Error";
                    e.Handled = true;
                } else {
                    DigitosText.Text = "Ok";
                    e.Handled = false;
                }
            }
        }

        private void longitudEntra(object sender, MouseEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                tb.Background = Brushes.Aquamarine;
            }
        }

        private void longitudSale(object sender, MouseEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                tb.Background = Brushes.White;
            }
        }

        private void rangoCheck(object sender, TextChangedEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                try {
                    int n = Int32.Parse(tb.Text);
                    Console.WriteLine(n);
                    if(n >= 100 && n <= 1000) {
                        RangoText.Text = "Ok";
                    } else {
                        throw new Exception();
                    }
                } catch(Exception ex) {
                    RangoText.Text = "Error";
                }
            }
        }

        private void cambioTemperatura(object sender, RoutedPropertyChangedEventArgs<double> e) {
            Slider sl = sender as Slider;
            if(sl != null) {
                TemperaturaText.Text = sl.Value + "º";
            }
        }

        private int temperatura;

        public int Temperatura {
            get { return temperatura; }
            set {
                temperatura = value;
                Console.WriteLine(temperatura);
            }
        }

    }
}
