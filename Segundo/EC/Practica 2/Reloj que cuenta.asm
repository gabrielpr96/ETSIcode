data segment
    RELOJ dB '00:000'
	CONT dB 0
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
    
    
    ;Defino AL como el incremento de 1
	MOV AL,1
	ADD CONT,AL

	CMP CONT,1
	JE VALE18
	JMP FIN

	VALE18:
	
	MOV CONT,0
	CMP RELOJ[4], '9'
	JB MENOR
	CMP RELOJ[3], '5'
	JE MINUTO
	ADD RELOJ[3], AL
	MOV RELOJ[4], '0'

	JMP FIN		
		
	MINUTO:

	ADD RELOJ[1], AL 
	MOV RELOJ[3], '0'
	MOV RELOJ[4], '0'

	JMP FIN

	MENOR:
	
	ADD RELOJ[4],AL
	JMP FIN
	
	
	
	
	FIN:
	
	;Hago que el segmento apunte a la direccion B8000h
	MOV AL,03h 		
	MOV AH,00h
	INT 10h

	;Hago que el segmento apunte a la direccion B8000h
	MOV AX,0B800h		   
    	MOV ES,AX 
	

	MOV AH,00001111b
	
	;Mostrar contador
	MOV AL,'C'
	MOV ES:[190],AX
	MOV AL,'O'
	MOV ES:[192],AX
	MOV AL,'N'
	MOV ES:[194],AX
	MOV AL,'T'
	MOV ES:[196],AX
	MOV AL,':'
	MOV ES:[198],AX

	;Atributos para los numeros
	MOV ES:[201],AH
	MOV ES:[203],AH
	MOV ES:[205],AH
	MOV ES:[207],AH
	MOV ES:[209],AH
	
	
	;MOV AL,48
	;MOV BL,RELOJ[0]
	;ADD AL,BL
	;MOV ES:[200],BL
	MOV AL, RELOJ[0] 
	MOV ES:[200], AL 

	;MOV AL,48
	;MOV BL,RELOJ[1]
	;ADD AL,BL
	;MOV ES:[202],BL 
	MOV AL, RELOJ[1]
	MOV ES:[202], AL

	;MOV AL,RELOJ[2]	
	;MOV ES:[204],AL
	MOV AL, RELOJ[2]	
	MOV ES:[204], AL

	;MOV AL,48
	;MOV BL,RELOJ[3]
	;ADD AL,BL
	;MOV ES:[206],BL
	MOV AL, RELOJ[3]
	MOV ES:[206], AL

	
	;MOV AL,48
	;MOV BL,RELOJ[4]
	;ADD AL,BL
	;MOV ES:[208],AL
	MOV AL, RELOJ[4]
	MOV ES:[208], AL
    
    
    JMP START
    
mov ax, 4c00h
int 21h  

ends

end start
