   M S B A M L   `   `   `  ����E  AExamen2020, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null7 clr-namespace:Examen2020.paginasExamen2020.paginas  5      #    Examen2020.paginas.PaginaRoja   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      2local clr-namespace:Examen2020.paginas     5        N� 
Background
  �  �� N� Title$ 
PaginaRoja��5
      .��5      � -   5        ��5   
   �� 5       j� TileMode$
 Tile=�6    �� Opacity$ .5Q�6)    j� Viewport$ 0, 0, .05, .05�66    �� ImageSource$ ../src/RN.jpg��6P    � RowDefinitions 5   
   �� 5      $��4* �6   �� 5      $��4* �6   �� 5      $��1* �6   .+�5   
   �� -   5        $��2��5      $��5q�6   $��Center=�6*   $��Center=�6G   .��6�   Cambiar de página5      �� 5   
   ���	#7FFFFFFF$��Center=�6(   $��Center=�6E   $��5q�6`   ._�5      ��  �� Orientation$ Vertical=�6   $��5q�60   .+�5      �� $��Monotype)�6   $��Oblique$�62   $��16(�6F   $��
0, 0, 0, 5q�6T   Fondo de la página5      	� 5      -   5        
 � Tag$	 Red��5      	 	� 	GroupName$	 Fondo��6(   .��6Q   
Fondo rojo5       	� 5!      -   5        $ Green��5!      $	 Fondo��6*   .��6S   Fondo verde5#      	� 5$      -   5        $
 Blue��5$      $	 Fondo��6)   .��6R   
Fondo azul5&      	� 5'      -   5        $ Yellow��5'      $	 Fondo��6+   .��6T   Fondo amarillo5)      	� 5*      -   5        $ Magenta��5*      $	 Fondo��6,   .��6U   Fondo magenta5,      	� 5-      -   5        $
 Cyan��5-      $	 Fondo��6)   .��6R   
Fondo cyan5/      	� 50      -	   5        $ White��50      $	 Fondo��6*   .��6S   Fondo blanco52      	� 53      -
   5        $ Black��53      $	 Fondo��6*   .��6S   Fondo negro55      56      57      �� 58   
   $��1��6   $ 
Horizontal=�6"   $��Center=�6;   $��Center=�6X   .+�59      �� -   5        $��PosicionRaton��59   B   $y�	Collapsed=�6   
�������$��Monotype)�6W   $��Oblique$�6m   $��16(�6�   6�   d� 5:      -   5        $��4q�5:      $��10, 0, 0, 0q�6'   .��6x   Mostrar coordenadas6�   5;      5<      5=      