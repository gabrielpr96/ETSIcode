/// @description Seleccionar abajo

if(selected == -1)
	selected = 0;

selected--;

if(selected < 0){
	selected = 13;
}

window_set_cursor(cr_none);