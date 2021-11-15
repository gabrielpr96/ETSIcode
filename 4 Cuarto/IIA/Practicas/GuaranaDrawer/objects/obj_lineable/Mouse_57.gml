/// @description Acabar el emparejado

if(isPairing){
	isPairing = false;
	var target = instance_place(mouse_x, mouse_y, obj_lineable);
	if(position_meeting(mouse_x, mouse_y, self)){
		instance_destroy(self);
	}
	if(target == noone){
		ds_list_clear(conectados);
	} else {
		ds_list_add(conectados, target);
	}
}
