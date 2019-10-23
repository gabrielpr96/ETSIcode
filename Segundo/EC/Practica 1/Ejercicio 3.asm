data segment
                                  
    CADENA      DB 1,1,1,1
    PESO        DB 1000b,100b,10b,1b
    VALOR_CO1   DB 0
    SIGNO_CO1   DB 0
    VALOR_CO2   DB 0
    SIGNO_CO2   DB 0
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:

    ;Inicializar el segmento de datos
    MOV AX, SEG CADENA
    MOV DS, AX              
    
    
    MOV AH, 0
                                     
    ;Evaluar si es positivo o no
    MOV AL, CADENA[0]
    AND AL, AL         ;Esto es lo que se me ha ocurrido a mi para comprobarlo
    JNZ ESNEGATIVO1:
    ;No es negativo luego
    
    MOV BX, 0
    MOV AH, 00h  
    MOV AL, CADENA[1]
    MUL PESO[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[2]
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[3]
    MUL PESO[3]       
    ADD BX, AX
    
    JMP FINALIZAR1
    
    ESNEGATIVO1:       
    ;Es negativo, lo invierto y lo indico
    
    MOV BX, 0
    MOV AH, 00h  
    MOV AL, CADENA[1]
    NOT AL
    AND AL, 00000001b
    MUL PESO[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[2]
    NOT AL   
    AND AL, 00000001b
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[3]
    NOT AL  
    AND AL, 00000001b
    MUL PESO[3]       
    ADD BX, AX
    
    MOV SIGNO_CO1, 1
    
    FINALIZAR1:
    
    MOV VALOR_CO1, BL
    
    
    ;Evaluar si es positivo o no
    MOV AH, 0
    MOV AL, CADENA[0]
    AND AL, AL         ;Esto es lo que se me ha ocurrido a mi para comprobarlo
    JNZ ESNEGATIVO2:
    ;No es negativo luego
    MOV BX, 0
    
    MOV AH, 00h  
    MOV AL, CADENA[1]
    MUL PESO[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[2]
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[3]
    MUL PESO[3]       
    ADD BX, AX
    
    JMP FINALIZAR2
    
    ESNEGATIVO2:       
    ;Es negativo, lo invierto, lo indico y le sumo uno
    MOV BX, 1
    MOV AH, 00h
      
    MOV AL, CADENA[1]
    NOT AL
    AND AL, 00000001b
    MUL PESO[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[2]
    NOT AL   
    AND AL, 00000001b
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[3]
    NOT AL  
    AND AL, 00000001b
    MUL PESO[3]       
    ADD BX, AX
    
    MOV SIGNO_CO2, 1
    
    FINALIZAR2:
    
    MOV VALOR_CO2, BL
           
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  

ends

end start
