data segment
                                  
    CADENA      DB 5, 0, 0, 0, 0, 0, 0
    PESO        DB 1000b,100b,10b,1b
    VALOR_BIN   DB 0
    VALOR_CO2   DB 0
    SIGNO_CO2   DB '+'
    
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
    
    
    ;Calcular binario
    MOV AH, 0
                                     
    MOV AL, CADENA[2]
    MUL PESO[0]       
    MOV BX, AX
    
    MOV AL, CADENA[3]
    MUL PESO[1]       
    ADD BX, AX
                            
    MOV AL, CADENA[4]
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AL, CADENA[5]
    MUL PESO[3]       
    ADD BX, AX
    
    MOV VALOR_BIN, BL
    
    
    ;Evaluar si es positivo o no
    CMP CADENA[2], 1
    JE ESNEGATIVO:
    ;No es negativo luego
    MOV BX, 0
    
    MOV AH, 00h  
    MOV AL, CADENA[3]
    MUL PESO[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[4]
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[5]
    MUL PESO[3]       
    ADD BX, AX
    
    JMP FINALIZAR
    
    ESNEGATIVO:       
    ;Es negativo, lo invierto, lo indico y le sumo uno
    MOV BX, 1
    MOV AH, 00h
      
    MOV AL, CADENA[3]
    NOT AL
    AND AL, 00000001b
    MUL PESO[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[4]
    NOT AL   
    AND AL, 00000001b
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[5]
    NOT AL  
    AND AL, 00000001b
    MUL PESO[3]       
    ADD BX, AX
    
    MOV SIGNO_CO2, '-'
    
    FINALIZAR:
    
    MOV VALOR_CO2, BL
     
     
    ;Mostrar el resultado por pantalla
    ;Mover el segmento a la memoria de video
    MOV AX, 0B800h
    MOV ES, AX
    
    ;Fondo negro y letras blancas
    MOV AH, 00001111b
    
    ;Mostrar binario natural
    MOV AL, 'B'
    MOV ES:[160], AX
    MOV AL, 'I'
    MOV ES:[162], AX
    MOV AL, 'N'
    MOV ES:[164], AX
    MOV AL, ':'
    MOV ES:[166], AX
    ;Poner el estilo primero
    MOV ES:[169], AH
    MOV ES:[171], AH
    ;Poner los digitos
    MOV AH, 0
    MOV AL, VALOR_BIN
    MOV BL, 10d
    DIV BL
    ADD AL, 30h
    MOV ES:[168], AL
    ADD AH, 30h
    MOV ES:[170], AH
    
    ;Mostrar complemento a 2
    MOV AH, 00001111b
    MOV AL, 'C'
    MOV ES:[320], AX
    MOV AL, '2'
    MOV ES:[322], AX
    MOV AL, ':'
    MOV ES:[324], AX
    MOV AL, SIGNO_CO2
    MOV ES:[328], AX
    MOV AL, VALOR_CO2
    ADD AL, 30h
    MOV ES:[330], AX
     
           
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  

ends

end start
