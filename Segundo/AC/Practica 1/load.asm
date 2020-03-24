.model small

.data
	file db "12345678.123",0
	img db 0
.code
	;Iniciar segmento de datos
	MOV AX, SEG file
	MOV DS, AX
	
	MOV DI, 0
	MOV SI, 82h	;82 porque aparentemente el primer caracter es el espacio entre el programa y los argumentos
	BLOAD:
		MOV AL, ES:[SI]
		MOV file[DI], AL
		INC SI
		INC DI
	CMP ES:[SI], 0Dh
	JNE BLOAD
	MOV file[DI], 0
	
	;MOV AL, 03h
	;MOV AH, 00h
	;INT 10h
	;MOV AX, 0B800h
    ;MOV ES, AX
    ;MOV AH, 00001111b
	;MOV SI, 0
	;MOV DI, 0
	;BCL:
	;	MOV AL, file[SI]
	;	MOV ES:[DI], AX
	;	INC SI
	;	ADD DI, 2
	;CMP SI, 10
	;JB BCL
	
	;MOV AH, 0
	;INT 16h

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
	
		MOV AH, 0
		INT 16h
	CMP AH, 01h
	JNE BCLF
	
	
	
	;FInalizar el programa
	MOV AH, 4Ch
	INT 21h
end