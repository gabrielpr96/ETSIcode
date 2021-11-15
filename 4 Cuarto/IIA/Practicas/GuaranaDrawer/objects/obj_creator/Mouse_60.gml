/// @description Seleccionar arriba

selected++;

if(selectMode == 1){
	if(selected > 14){
		selected = 14;
	}
} else if(selectMode == 2) {
	if(selected > 9){
		selected = 9;
	}
}

