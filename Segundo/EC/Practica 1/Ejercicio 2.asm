data segment
                                  
    CADENA      DB 1,1,1,1
    PESO        DB 1000b,100b,10b,1b
    VALOR_BCS   DB 0
    SIGNO_BCS   DB 0
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
                                     
    MOV AL, CADENA[0]
    MOV SIGNO_BCS, AL
    
    MOV AL, CADENA[1]
    MUL PESO[1]       
    MOV BX, AX
                            
    MOV AL, CADENA[2]
    MUL PESO[2]       
    ADD BX, AX
                            
    MOV AL, CADENA[3]
    MUL PESO[3]       
    ADD BX, AX
    
    MOV VALOR_BCS, BL
    
    
    ;Evaluar si es positivo o no
    MOV AL, CADENA[0]
    AND AL, AL         ;Esto es lo que se me ha ocurrido a mi para comprobarlo
    JNZ ESNEGATIVO:
    ;No es negativo luego
    MOV BX, 0
    JMP FINALIZAR
    
    ESNEGATIVO:       
    ;Es negativo, lo invierto, lo indico y le sumo uno
    
    MOV AL, CADENA[1]
    NOT AL
    AND AL, 00000001b
    MOV CADENA[1], AL
    
    MOV AL, CADENA[2]
    NOT AL   
    AND AL, 00000001b
    MOV CADENA[2], AL
    
    MOV AL, CADENA[3]
    NOT AL  
    AND AL, 00000001b
    MOV CADENA[3], AL
    
    MOV SIGNO_CO2, 1
    MOV BX, 1
    
    FINALIZAR:
    
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
    
    MOV VALOR_CO2, BL
           
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  

ends

end start
