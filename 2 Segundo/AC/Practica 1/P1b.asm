.MODEL SMALL
.CODE
	org 100h
Programa_Int:
	JMP Reside
	CONT DW 0
	
Rutina_Servicio PROC
	CLI
	
	INC CONT
	CMP CONT, 182 ;10*18.2 = 182
	JB SALIR
	
	MOV AH, 0
	INT 16h
	
	MOV AH, 4Ch
	INT 21h
	
	SALIR:
	
	STI
	IRET
ENDP
Reside:
	MOV DX, offset Rutina_Servicio
	MOV AX, 0
	MOV ES, AX
	MOV SI, 1Ch*4
	CLI
	MOV ES:[SI], DX
	MOV ES:[SI+2], CS
	STI
	MOV DX, offset Reside
	INT 27h
END Programa_Int