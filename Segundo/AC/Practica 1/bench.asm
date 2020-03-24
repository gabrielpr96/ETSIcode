.model small

.data
	file db "VIDEO.BIN",0
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
		JNE CONTINUE
			MOV AH, 42h	;Cambiar posicion en el fichero
			MOV AL, 00h	;Desde el inicio
			MOV CX, 0	;Desplazamiento 0
			MOV DX, 0
			INT 21h
			MOV CX, 64000
			MOV DX, offset img
			MOV AH, 03Fh
			INT 21h		;Volver a leer
		CONTINUE:
		
		MOV SI, 0
		BCLE:
			MOV AL, img[SI]
			MOV ES:[SI], AL
			INC SI
		CMP SI, 64000
		JB BCLE
	
	CMP AH, 01h
	JNE BCLF
	
	
	
	;FInalizar el programa
	MOV AH, 4Ch
	INT 21h
end