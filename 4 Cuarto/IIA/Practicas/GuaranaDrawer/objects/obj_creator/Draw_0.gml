/// @description Dibujar la preview

if(selectMode == 1)
	draw_sprite_ext(spr_tareas, selected, mouse_x, mouse_y, 1, 1, 0, c_white, 0.5);
else if (selectMode == 2)
	draw_sprite_ext(spr_adapters, selected, mouse_x, mouse_y, 1, 1, 0, c_white, 0.5);