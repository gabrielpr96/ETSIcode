/// @description Crear

if (selectMode == 1) {
	var inst = instance_create_depth(mouse_x, mouse_y, -101, obj_task);
	inst.image_index = selected;
	window_set_cursor(cr_default);
	selectMode = 0;
} else if (selectMode == 2) {
	var inst = instance_create_depth(mouse_x, mouse_y, -101, obj_adapter);
	inst.image_index = selected;
	window_set_cursor(cr_default);
	selectMode = 0;
}
