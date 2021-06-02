using System;
using System.Collections.Generic;
using System.IO;
using System.Windows;
using System.Windows.Controls;

namespace OctabaAplicacion {
    /// <summary>
    /// Lógica de interacción para IniciarSesion.xaml
    /// </summary>
    public partial class IniciarSesion : Page {
        public IniciarSesion() {
            InitializeComponent();
            Usuarios = new List<Usuario>();
            DataContext = Usuarios;
            StreamReader sr = new StreamReader("../../src/usuarios.txt");
            string linea = sr.ReadLine();
            while(linea != null) {
                usuarios.Add(new Usuario(linea, sr.ReadLine()));
                linea = sr.ReadLine();
            }
            sr.Close();
        }

        private void onEnviar(object sender, RoutedEventArgs e) {
            Button boton = sender as Button;
            if(boton != null) {
                Page p;
                if((User.SelectedItem as Usuario).comprobar(PassHide.Password)) {
                    p = new Profesores();
                } else {
                    p = new Alumnos();
                }
                NavigationService.Navigate(p);
            }
        }

        private List<Usuario> usuarios;

        private List<Usuario> Usuarios {
            get { return usuarios; }
            set { usuarios = value; }
        }

        private void mostrarPass(object sender, System.Windows.Input.MouseButtonEventArgs e) {
            Console.WriteLine("Mostrar");
            PassHide.Visibility = Visibility.Collapsed;
            PassShow.Visibility = Visibility.Visible;
            PassShow.Text = PassHide.Password;
        }

        private void ocultarPass(object sender, System.Windows.Input.MouseButtonEventArgs e) {
            Console.WriteLine("Ocultar");
            PassHide.Visibility = Visibility.Visible;
            PassShow.Visibility = Visibility.Collapsed;
        }
    }
}
