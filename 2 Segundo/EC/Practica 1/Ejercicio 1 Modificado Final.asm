data segment
    
    CADENA      DB 5,0,0,0,0,0,0
    PESO        DB 8,4,2,1
    VALORES_HEX DB '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    VALOR_HEX   DB 0
    
    ;Valor absoluto en decimal y character con el signo correspondiente
    VALOR_CO1   DB 0
    SIGNO_CO1   DB '+'
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
    ;Inicializar el segmento de datos
    MOV AX, SEG CADENA
    MOV DS, AX       
    
    
    ;Leer por teclado
    MOV DX, OFFSET CADENA
    MOV AH, 0Ah
    INT 21h
    
    ;Cambiar caracteres ASCII por los numericos correspondientes
    SUB CADENA[2], 30h
    SUB CADENA[3], 30h
    SUB CADENA[4], 30h
    SUB CADENA[5], 30h 
    
     
    ;Calcular el hexadecimal
                                     
    MOV AL, CADENA[2]
    MUL PESO[0]       
    MOV BL, AL
    
    MOV AL, CADENA[3]
    MUL PESO[1]       
    ADD BL, AL
                            
    MOV AL, CADENA[4]
    MUL PESO[2]       
    ADD BL, AL
                            
    MOV AL, CADENA[5]
    MUL PESO[3]       
    ADD BL, AL
    
    MOV VALOR_HEX, BL
     
     
    ;Calcular el complemento a 1
    ;Evaluar si es positivo o no
    CMP CADENA[2], 1
    JE  ESNEGATIVO
    JMP FINALIZAR
    
    ESNEGATIVO:       
    ;Es negativo, lo invierto y lo indico
        
        NOT CADENA[3]
        AND CADENA[3], 00000001b
                                
        NOT CADENA[4]
        AND CADENA[4], 00000001b
        
        NOT CADENA[5]
        AND CADENA[5], 00000001b
        
        MOV SIGNO_CO1, '-'
        
    FINALIZAR:
                 
    MOV AL, CADENA[3]
    MUL PESO[1]    
    MOV BL, AL
    
    MOV AL, CADENA[4]
    MUL PESO[2]       
    ADD BL, AL
     
    MOV AL, CADENA[5]
    MUL PESO[3]       
    ADD BL, AL
    
    MOV VALOR_CO1, BL                
    
    
    ;Mostrar el resultado por pantalla
    ;Mover el segmento a la memoria de video
    MOV AX, 0B800h
    MOV ES, AX
    
    ;Fondo negro y letras blancas
    MOV AH, 00001111b
    
    ;Mostrar complemento a 1
    MOV AL, 'C'
    MOV ES:[160], AX
    MOV AL, '1'
    MOV ES:[162], AX
    MOV AL, ':'
    MOV ES:[164], AX
    MOV AL, SIGNO_CO1
    MOV ES:[168], AX
    MOV AL, VALOR_CO1
    ADD AL, 30h
    MOV ES:[170], AX
    
    ;Mostrar hexadecimal
    MOV AL, 'H'
    MOV ES:[320], AX
    MOV AL, 'E'
    MOV ES:[322], AX
    MOV AL, 'X'
    MOV ES:[324], AX
    MOV AL, ':'
    MOV ES:[326], AX
    ;Sacar el simbolo correspondiente
    MOV BH, 0
    MOV BL, VALOR_HEX
    MOV SI, BX
    MOV AL, VALORES_HEX[SI]
    MOV ES:[330], AX
                 
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  
ends

end start
