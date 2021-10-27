/// @description Dibujar la preview

if(selected >= 0)
	draw_sprite_ext(spr_tareas, selected, mouse_x, mouse_y, 1, 1, 0, c_white, 0.5);

if(selected == -2)
	draw_sprite_ext(spr_puerto, 0, mouse_x, mouse_y, 1, 1, 0, c_white, 0.5);