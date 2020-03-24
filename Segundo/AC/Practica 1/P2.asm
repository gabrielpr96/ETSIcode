.MODEL SMALL
.CODE
	org 100h
Programa_Int:
	JMP Reside
	CONT DB 18
	MSJ DB '00:00'
	
Rutina_Servicio PROC
	CLI
	         
	     
	INC CONT
	CMP CONT, 18
	JB SALIR
	MOV CONT, 0 
	
	MOV AX, 0B800h
	MOV ES, AX
	MOV AH, 00001111b
	MOV SI, 0
	MOV DI, 148
	BSTR:
		MOV AL, MSJ[SI]
		MOV ES:[DI], AX
		ADD DI, 2
		INC SI
	CMP SI, 4
	JBE BSTR
	
	;Controlar el reloj
	
	CMP MSJ[4], '9'
	JNE TIMEINC
	MOV MSJ[4], '0'
	INC MSJ[3]
	
	CMP MSJ[3], '6'
	JNE SALIR
	MOV MSJ[3], '0'
	INC MSJ[1]
	
	CMP MSJ[1], '9'
	JNE SALIR
	MOV MSJ[1], '0'
	INC MSJ[0]
	
	CMP MSJ[0], '6'
	JNE SALIR
	MOV MSJ[0], '0'
	JMP SALIR
	
	
	TIMEINC:
	INC MSJ[4]
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