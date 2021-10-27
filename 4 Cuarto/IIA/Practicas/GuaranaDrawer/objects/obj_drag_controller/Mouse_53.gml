/// @description Detectar en quien se ha pulsado

dragging = noone;

with(obj_draggable){
	if(position_meeting(mouse_x, mouse_y, self) and (other.dragging == noone or depth < other.dragging.depth)){
		other.dragging = self;
	}
}

if(dragging != noone){
	with(obj_draggable){
		depth++;
	}
	dragging.depth = -100;
	dragging.drwagOffsetX = dragging.x - mouse_x;
	dragging.drwagOffsetY = dragging.y - mouse_y;
	dragging.isBeingDragged = true;
}