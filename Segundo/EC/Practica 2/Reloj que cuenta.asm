data segment
    CONT DB 0
    MSJ DB '00:00'
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
    MOV AX, SEG CONT
    MOV DS, AX    
    MOV AX, 0B800h
    MOV ES, AX
    
    
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
    
    
    JMP START
    
mov ax, 4c00h
int 21h  

ends

end start
