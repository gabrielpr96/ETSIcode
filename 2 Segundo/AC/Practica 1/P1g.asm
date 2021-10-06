.model small

.data
	colour db 0
	row db 0
	
.code
	;Iniciar segmento de datos
	MOV AX, SEG colour
	MOV DS, AX
	
	;Establecer modo grafico
	MOV AL, 13h
	MOV AH, 00h
	INT 10h
	
	;Poner segmento extra en memoria de video
	MOV AX, 0A000h
	MOV ES, AX
	
	MOV colour, 0F3h
	MOV DI, 0
	BCLF:
	MOV AL, colour
		MOV SI, 0
		BCLE:
			MOV ES:[DI], AL
			INC SI
			INC DI
		CMP SI, 320*10
		JB BCLE
	INC colour
	CMP colour, 0F3h
	JBE BCLF
	
	
	MOV AH, 0
	INT 16h
	
	;FInalizar el programa
	MOV AH, 4Ch
	INT 21h
end