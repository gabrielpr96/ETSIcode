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

namespace QuintaAplicacion
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

        private void onChecked(object sender, RoutedEventArgs e)
        {
            var converter = new System.Windows.Media.BrushConverter();
            RadioButton radio = sender as RadioButton;
            if(radio != null)
            {
                var brush = (Brush)converter.ConvertFromString(radio.Content.ToString());
                switch (radio.GroupName)
                {
                    case "Background":
                        TextBackground.Background = brush;
                        ExternalBorder.Background = brush;
                        InternalBorder.Background = brush;
                        break;
                    case "BorderBrush":
                        TextBorderBrush.Background = brush;
                        ExternalBorder.BorderBrush = brush;
                        InternalBorder.BorderBrush = brush;
                        break;
                }
            }
        }
    }
}
