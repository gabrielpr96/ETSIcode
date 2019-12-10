.MODEL SMALL
.CODE
	org 100h
	
	Programa_Int:
	
		JMP  Reside
		;;;;; Declaración de variables
		CONT DB 0
		MSJ DB '00:00'
		
	Rutina_Servicio PROC
		CLI ;Bioestable de interrupciones a 0 (no se aceptan)
		; Salvar el contenido de los registros implicados en la rutina
		
		INC CONT
		CMP CONT, 18
		JB SALIR
		MOV CONT, 0
		
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
		
		INC MSJ[4]
		;Controlar el reloj
		
		CMP MSJ[4], '9'
		JNE SALIR
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
		
		
		SALIR:
		
		;Recupera el valor de los registros implicados en la rutina
		STI ;Bioestable de interrupciones a 1 (se aceptan) 
		IRET
		
	ENDP
	Reside:   ;etiqueta para determinar la dirección siguiente a la última de la rutina que debe quedar residente
	
		MOV DX, offset Rutina_Servicio
		MOV AX, 0
		MOV ES, AX
		MOV  SI, Codigo_Int*4
		CLI
		MOV ES:[SI], DX
		MOV ES:[SI+2], CS
		STI
		MOV DX, offset Reside
		INT 27h
	
	END Programa_Int