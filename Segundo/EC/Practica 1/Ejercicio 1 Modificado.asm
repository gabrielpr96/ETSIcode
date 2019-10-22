data segment
    
    CADENA      DB 1,1,1,1
    PESO_BIN    DB 8,4,2,1
    PESO_HEX    DW 1000h,100h,10h,1h
    VALOR_DEC   DB 0
    VALOR_HEX   DW 0
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
    ;Inicializar el segmento de datos
    MOV AX, SEG CADENA
    MOV DS, AX              
                                
    MOV AX, 0000h      
    MOV AX, PESO_HEX[0]
    MOV AX, PESO_HEX[1]
    MOV AX, PESO_HEX[2]
    MOV AX, PESO_HEX[3]          
    
    MOV AH, 00h       ;Muy importante poner esa parte a 00h porque si no no se limpia                 
    MOV AL, CADENA[0]
    MUL PESO_HEX[0]       
    MOV BX, AX
    
    MOV AH, 00h  
    MOV AL, CADENA[1]
    MUL PESO_HEX[1]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[2]
    MUL PESO_HEX[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[3]
    MUL PESO_HEX[3]       
    ADD BX, AX
    
    MOV VALOR_HEX, BX
           
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  
ends

end start
