.model small

.data
	file db "V2NLANDS.BIN",0
	img db 0
.code
	;Iniciar segmento de datos
	MOV AX, SEG file
	MOV DS, AX
	
	;Establecer modo grafico
	MOV AL, 13h
	MOV AH, 00h
	INT 10h
	
	;Poner segmento extra en memoria de video
	MOV AX, 0A000h
	MOV ES, AX
	
	;Abrir el archivo para lectura
	MOV AH, 03Dh	;Abrir el fichero
	MOV AL, 0	 	;Para lectura
	MOV DX, offset file	
	INT 21h
	MOV BX, AX		;Paso el handle del archivo
	MOV CX, 64000 	;Numero de bytes a leer
	MOV DX, offset img 	;Variable para guardar los datos
	
	BCLF:
		MOV AH, 03Fh	;Lectura del handle en BX
		INT 21h
		
		CMP AX, 0		;Si ha llegado al final
		JE FINAL
		
		MOV SI, 0
		BCLE:
			MOV AL, img[SI]
			MOV ES:[SI], AL
			INC SI
		CMP SI, 64000
		JB BCLE
	
	JMP BCLF
	
	
	;FInalizar el programa
	FINAL:
	MOV AH, 4Ch
	INT 21h
end