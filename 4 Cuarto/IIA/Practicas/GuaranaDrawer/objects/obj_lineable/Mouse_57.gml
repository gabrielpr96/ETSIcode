/// @description Acabar el emparejado

if(isPairing){
	isPairing = false;
	var target = instance_place(mouse_x, mouse_y, obj_lineable);
	if(position_meeting(mouse_x, mouse_y, self)){
		instance_destroy(self);
	}
	if(target != noone){
		var pos = ds_list_find_index(conectados, target);
		if(pos == -1)
			ds_list_add(conectados, target);
		else
			ds_list_delete(conectados, pos);
	}
}
