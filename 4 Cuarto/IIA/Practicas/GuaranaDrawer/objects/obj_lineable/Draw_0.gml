/// @description Dibujar las lineas

draw_set_color(c_black);
	
var n = ds_list_size(conectados);
for(var i = 0; i < n; i++){
	var conectado = ds_list_find_value(conectados, i);
	var centerX = x+(conectado.x - x)/2
	var myY = y-(sprite_height/2)+((sprite_height/n)*i)+((sprite_height/n)/2)
	draw_line_width(x, myY, centerX, myY, 2);
	draw_line_width(centerX, myY, centerX, conectado.y, 2);
	draw_line_width(centerX, conectado.y, conectado.x, conectado.y, 2);
	draw_sprite_ext(spr_arrow, 0, conectado.x-conectado.sprite_width/2, conectado.y, 1, 1, 0, c_black, 1);
}

if(isPairing){
	draw_set_color(c_red);
	draw_line(x, y, mouse_x, mouse_y);
}
