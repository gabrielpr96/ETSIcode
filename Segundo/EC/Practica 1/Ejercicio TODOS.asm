data segment
    
    CADENA      DB 0,1,0,1
    PESO        DB 8,4,2,1
    VALOR_DEC   DB 0
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:

    ;Inicializar el segmento de datos
    MOV AX, SEG CADENA
    MOV DS, AX              
    
    MOV BX, 0h
    
    ;BX += CADENA[0] * PESO[0]       
    MOV AL, CADENA[0]
    MUL PESO[0]       
    ADD BX, AX
    
    ;BX += CADENA[1]*PESO[1]
    MOV AL, CADENA[1]
    MUL PESO[1]       
    ADD BX, AX
                            
    ;BX += CADENA[2]*PESO[2]
    MOV AL, CADENA[2]
    MUL PESO[2]       
    ADD BX, AX
                            
    ;BX += CADENA[3]*PESO[3]
    MOV AL, CADENA[3]
    MUL PESO[3]       
    ADD BX, AX
    
    MOV VALOR_DEC, BL
           
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  

ends

end start
