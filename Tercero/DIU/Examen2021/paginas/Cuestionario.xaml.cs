using System;
using System.Collections.Generic;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace Examen2021.paginas {
    /// <summary>
    /// Lógica de interacción para Cuestionario.xaml
    /// </summary>
    public partial class Cuestionario : Page {
        public Cuestionario() {
            InitializeComponent();
            Paises = new List<String>();
            DataContext = Paises;
            try {
                //En esta ruta relativa se asume que el proyecto es ejecutado desde el IDE.
                //La ruta falla cuando el ejecutable no está bin/Debug
                StreamReader sr = new StreamReader("../../src/paises.txt");
                String linea = sr.ReadLine();
                while(linea != null) {
                    Paises.Add(linea);
                    linea = sr.ReadLine();
                }
                sr.Close();
            } catch(Exception ex) {
                Paises.Add("Error " + ex.Message);
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

        private void passFiltrar(object sender, TextCompositionEventArgs e) {
            PasswordBox tb = sender as PasswordBox;
            if(tb != null) {
                int c = Convert.ToInt32(Convert.ToChar(e.Text));
                if(c >= 48 && c <= 57) {
                    e.Handled = false;
                } else {
                    //El siguiente comentario evita que se descarte todo lo que no sean dígitos.
                    //Porque en el enunciado especifica "Comprobar" y no "Prohibir".
                    //Descomentar para asegurar totalmente que la contraseña solo está compuesta de dígitos.
                    //e.Handled = true;
                }
            }
        }

        private void passComprobar(object sender, System.Windows.RoutedEventArgs e) {
            PasswordBox pb = sender as PasswordBox;
            if(pb != null) {
                //Podría intentar convertir el texto a un entero y ver si sale excepción, pero fallaría cuando el número sea muy grande.
                //En su lugar compruebo caracter a caracter.
                char[] pass = pb.Password.ToCharArray();
                bool valido = true;
                int i = 0;
                while(i < pass.Length && valido) {
                    if(pass[i] >= 48 && pass[i] <= 57) {
                        i++;
                    } else {
                        valido = false;
                    }
                }
                if(valido) {
                    pb.Foreground = Brushes.Black;
                } else {
                    pb.Foreground = Brushes.Red;
                }
            }
        }

        private void saleNombre(object sender, System.Windows.RoutedEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                if(tb.Foreground == Brushes.Red) {
                    MessageBox.Show("El nombre introducido no tiene entre 3 y 10 caracteres.", "Campos erroneos");
                }
            }
        }

        private void saleEdad(object sender, System.Windows.RoutedEventArgs e) {
            TextBox tb = sender as TextBox;
            if(tb != null) {
                if(tb.Foreground == Brushes.Red) {
                    MessageBox.Show("La edad introducida no es un nümero o no está entre 18 y 100.", "Campos erroneos");
                }
            }
        }

        private void salePass(object sender, System.Windows.RoutedEventArgs e) {
            PasswordBox pb = sender as PasswordBox;
            if(pb != null) {
                if(pb.Foreground == Brushes.Red) {
                    MessageBox.Show("La contraseña no está únicamente formada por dígitos.", "Campos erroneos");
                }
            }
        }

        private void actualizarSueldo(object sender, System.Windows.RoutedPropertyChangedEventArgs<double> e) {
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
