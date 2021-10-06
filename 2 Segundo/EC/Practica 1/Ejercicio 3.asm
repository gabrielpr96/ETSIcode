data segment
                                  
    CADENA      DB 5, 0, 0, 0, 0, 0, 0
    PESO        DB 1000b,100b,10b,1b
    VALOR_CO1   DB 0
    SIGNO_CO1   DB '+'
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
    
    
    ;Calcular complemento a 1
    MOV AH, 0
                                     
    ;Evaluar si es positivo o no
    CMP CADENA[2], 1    
    JE ESNEGATIVO1:
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
    
    JMP FINALIZAR1
    
    ESNEGATIVO1:       
    ;Es negativo, lo invierto y lo indico
    
    MOV BX, 0
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
    
    MOV SIGNO_CO1, '-'
    
    FINALIZAR1:
    
    MOV VALOR_CO1, BL
    
    
    ;Calcular complemento a 2
    ;Evaluar si es positivo o no
    CMP CADENA[2], 1
    JE ESNEGATIVO2:
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
    
    JMP FINALIZAR2
    
    ESNEGATIVO2:       
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
    
    FINALIZAR2:
    
    ADD VALOR_CO2, BL
    
    
    ;Mostrar el resultado por pantalla
    ;Mover el segmento a la memoria de video
    MOV AX, 0B800h
    MOV ES, AX
    
    ;Fondo negro y letras blancas
    MOV AH, 00001111b
    
    ;Mostrar complemento a 1
    MOV AH, 00001111b
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
