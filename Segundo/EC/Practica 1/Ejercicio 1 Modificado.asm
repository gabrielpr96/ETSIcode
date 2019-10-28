data segment
    
    ;CADENA      DB 02h,0Fh,05h,0Ah
    CADENA      DB 5,0,0,0,0,0,0
    PESO_HEX    DW 1000h,100h,10h,1h
    PESO_BIN    DB 8,4,2,1
    VALOR_HEX   DW 0
    VALOR_CO1   DB 0
    SIGNO_CO1   DB 0
    
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
     
    ;Calcular el binario
    MOV AH, 00h       ;Muy importante poner esa parte a 00h porque la multiplicacion deja residuos ahi                 
    MOV AL, CADENA[2]
    MUL PESO_HEX[0]       
    MOV BX, AX
    
    MOV AH, 00h  
    MOV AL, CADENA[3]
    MUL PESO_HEX[2]   ;MUY MUY IMPORTANTE sumar de 2 en dos posiciones de memoria, es un DW    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[4]
    MUL PESO_HEX[4]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[5]
    MUL PESO_HEX[6]       
    ADD BX, AX
    
    MOV VALOR_HEX, BX
     
     
    ;Calcular el hexadecimal
    ;Evaluar si es positivo o no
    MOV AL, CADENA[2]
    AND AL, AL         ;Esto es lo que se me ha ocurrido a mi para comprobarlo
    JNZ ESNEGATIVO:
    ;No es negativo luego
    JMP FINALIZAR
    
    ESNEGATIVO:       
    ;Es negativo, lo invierto y lo indico
    
    MOV AL, CADENA[3]
    NOT AL
    AND AL, 00000001b
    MOV CADENA[3], AL
    
    MOV AL, CADENA[4]
    NOT AL   
    AND AL, 00000001b
    MOV CADENA[4], AL
    
    MOV AL, CADENA[5]
    NOT AL  
    AND AL, 00000001b
    MOV CADENA[5], AL
    
    MOV SIGNO_CO1, 1
    
    FINALIZAR:
    
    MOV BX, 0
    MOV AH, 00h  
    MOV AL, CADENA[3]
    MUL PESO_BIN[1]    
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[4]
    MUL PESO_BIN[2]       
    ADD BX, AX
                            
    MOV AH, 00h  
    MOV AL, CADENA[5]
    MUL PESO_BIN[3]       
    ADD BX, AX
    
    MOV VALOR_CO1, BL                
    
    
    ;Mostrar el resultado por pantalla
    MOV AX, 0B800h
    MOV ES, AX
    
    ;MOV AH, 10101001b
    ;MOV AL, 'E'
    ;MOV ES:[0], AX
    ;MOV AH, 10101001b
    ;MOV AL, 'S'
    ;MOV ES:[2], AX
    ;MOV AH, 10101001b
    ;MOV AL, 'S'
    ;MOV ES:[4], AX
    
    MOV AH, 10101001b
    
    MOV AL, VALOR_CO1
    ADD AL, 30h
    MOV ES:[160], AX             
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  
ends

end start
