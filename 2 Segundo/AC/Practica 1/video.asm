.MODEL SMALL
.CODE
	org 100h
Programa_Int:
	JMP Reside
	handle DW 0
	file db "VIDEO.BIN",0
	
Rutina_Servicio PROC
	CLI
	
	MOV AX, CS
	MOV DS, AX
	
	MOV AL, 13h
	MOV AH, 00h
	INT 10h
	
	MOV AX, 0A000h
	MOV ES, AX
	     
	CMP handle, 0
	JNE NOOPEN
		MOV AH, 03Dh
		MOV AL, 0
		MOV DX, offset file	
		INT 21h
		MOV handle, AX
	NOOPEN:
	MOV BX, handle
	MOV CX, 64000
	MOV DX, offset img
	MOV AH, 03Fh
	INT 21h
	
	CMP AX, 0
	JNE CONTINUE
		MOV AH, 42h
		MOV AL, 00h
		MOV CX, 0
		MOV DX, 0
		INT 21h
		MOV BX, handle
		MOV CX, 64000
		MOV DX, offset img
		MOV AH, 03Fh
		INT 21h
	CONTINUE:
	
	MOV SI, 0
	BCLE:
		MOV AL, img[SI]
		MOV ES:[SI], AL
		INC SI
	CMP SI, 64000
	JB BCLE
	
	STI
	IRET
	
	img db 0
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