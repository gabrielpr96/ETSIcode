using System;
using System.ServiceModel;

namespace AppServicio {
    class Program {
        static void Main(string[] args) {
            using(ServiceHost host = new ServiceHost(typeof(Practica_4_Servicio.GestionAlmacen))) {
                host.Open();
                Console.WriteLine("Servidor funcionando ....\n");

                Console.WriteLine("Nombre: {0}", host.Description.ConfigurationName);
                Console.WriteLine("Puerto: {0}", host.BaseAddresses[0].Port);
                Console.WriteLine("Direccion: {0}", host.BaseAddresses[0].LocalPath);
                Console.WriteLine("Protocolo: {0}", host.BaseAddresses[0].Scheme);

                Console.WriteLine("Endpoint: {0}://{1}:{2}{3}\n", host.BaseAddresses[0].Scheme, host.BaseAddresses[0].Host, host.BaseAddresses[0].Port, host.BaseAddresses[0].LocalPath);

                Console.WriteLine("Pulsa una tecla para cerrarlo");
                Console.ReadLine();
                host.Close();
            }
        }
    }
}
