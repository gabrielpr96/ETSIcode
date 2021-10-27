/// @description Crear

if(selected >= 0){
	var inst = instance_create_depth(mouse_x, mouse_y, -101, obj_task);
	inst.image_index = selected;
	window_set_cursor(cr_default);
	selected = -1;
}

if(selected == -2){
	instance_create_depth(mouse_x, mouse_y, -101, obj_port);
	window_set_cursor(cr_default);
	selected = -1;
}
