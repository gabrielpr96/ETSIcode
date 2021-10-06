using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace QuintaAplicacion {
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {
        public MainWindow() {
            InitializeComponent();
        }

        private void onChecked(object sender, RoutedEventArgs e) {
            BrushConverter converter = new BrushConverter();
            RadioButton radio = sender as RadioButton;
            if(radio != null) {
                Brush brush = (Brush)converter.ConvertFromString(radio.Tag.ToString());
                switch(radio.GroupName) {
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
