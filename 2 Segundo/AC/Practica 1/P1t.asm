.model small

.data
	msj db 'Hola Mundo'

.code
	;Iniciar segmento de datos
	MOV AX, SEG msj
	MOV DS, AX
	
	;Establecer modo texto
	MOV AL, 03h
	MOV AH, 00h
	
	;Poner segmento extra en memoria de video
	MOV AX, 0B800h
	MOV ES, AX
	
	;Establecer estilo letras blancas brillantes sobre fondo negro
	MOV AH, 00011100b
	
	;Copiar el mensaje a la memoria de video
	MOV SI, 0
	MOV DI, 0
	BCLE:
		MOV AL, msj[SI]
		MOV ES:[DI], AX
		INC SI
		ADD DI, 2
		CMP SI, 9
		JBE NEND
			MOV SI, 0
			MOV AL, ' '
			MOV ES:[DI], AX
			ADD DI, 2
		NEND:
		CMP DI, 80*2*25
	JMP BCLE
	JB BCLE
	
	;FInalizar el programa
	MOV AH, 4Ch
	INT 21h
end