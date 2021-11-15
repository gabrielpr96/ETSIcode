/// @description Cambiar modo de seleccion

if(selectMode == 0){
	selectMode = 1;
	window_set_cursor(cr_none);
}else if(selectMode == 1){
	selectMode = 2;
	window_set_cursor(cr_none);
}else if(selectMode == 2){
	selectMode = 0;
	window_set_cursor(cr_default);
}

selected = 0;
