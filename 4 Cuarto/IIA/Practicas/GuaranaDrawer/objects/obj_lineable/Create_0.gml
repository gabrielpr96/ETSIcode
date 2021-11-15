/// @description Inicializar a no lineas
event_inherited();

conectados = ds_list_create();
isPairing = false;

text = instance_create(x, y, obj_lineable_text)
text.width = sprite_width*2;