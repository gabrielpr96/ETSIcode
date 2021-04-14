package patronsingletronpractica;

import patronsingletronpractica.interfaz.MenuPrincipal;

public class PatronSingletronPractica {

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setLocationRelativeTo(null);
        menuPrincipal.setVisible(true);
    }
    
}
