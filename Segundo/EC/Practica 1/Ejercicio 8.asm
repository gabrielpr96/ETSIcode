data segment
    
    CADENA      DB 5,0,0,0,0,0,0
    PESO_HEX    DW 1000h,100h,10h,1h
    PESO_BIN    DB 8,4,2,1
    VALOR_HEX   DW 0
    VALOR_BCS   DB 0
    SIGNO_BCS   DB '+'
    
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
    ;Comprobar si se han introducido digitos de hexadecimal
    CMP CADENA[2], 9d
    JA C2HEX
    HEX3CHK:
    CMP CADENA[3], 9d
    JA C3HEX
    HEX4CHK:
    CMP CADENA[4], 9d
    JA C4HEX
    HEX5CHK:
    CMP CADENA[5], 9d
    JA C5HEX
    JMP HEXFIN
    
    C2HEX:
        SUB CADENA[2], 7d
    JMP HEX3CHK
    C3HEX:
        SUB CADENA[3], 7d
    JMP HEX4CHK
    C4HEX:
        SUB CADENA[4], 7d
    JMP HEX5CHK
    C5HEX:
        SUB CADENA[5], 7d
    JMP HEXFIN
    
    HEXFIN: 
    
     
    ;Calcular el hexadecimal
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
     
     
    ;Calcular el signo magnitud
    MOV AH, 0
    
    CMP CADENA[2], 1
    JE BCS_NEGATIVO
    JMP BCS_FIN
    BCS_NEGATIVO:
        MOV SIGNO_BCS, '-'
    BCS_FIN:          
    
    MOV AL, CADENA[3]
    MUL PESO_BIN[1]       
    MOV BX, AX
                            
    MOV AL, CADENA[4]
    MUL PESO_BIN[2]       
    ADD BX, AX
                            
    MOV AL, CADENA[5]
    MUL PESO_BIN[3]       
    ADD BX, AX
    
    MOV VALOR_BCS, BL                
    
    
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
    MOV AL, SIGNO_BCS
    MOV ES:[168], AX
    MOV AL, VALOR_BCS
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
    ;Poner el estilo primero
    MOV ES:[329], AH
    MOV ES:[331], AH
    MOV ES:[333], AH
    MOV ES:[335], AH
    MOV ES:[337], AH
    ;Poner los digitos
    ;Quinto digito
    MOV AX, VALOR_HEX
    MOV CX, 10000d
    MOV DX, 0
    DIV CX
    ADD AL, 30h
    MOV ES:[328], AL
    MOV AX, DX
    ;Cuarto digito
    MOV CX, 1000d
    MOV DX, 0
    DIV CX
    ADD AL, 30h
    MOV ES:[330], AL
    MOV AX, DX
    ;Tercero digito
    MOV CX, 100d
    MOV DX, 0
    DIV CX
    ADD AL, 30h
    MOV ES:[332], AL
    MOV AX, DX
    ;Segundo digito
    MOV CX, 10d
    MOV DX, 0
    DIV CX
    ADD AL, 30h
    MOV ES:[334], AL
    MOV AX, DX
    ;Primer digito
    ADD AL, 30h
    MOV ES:[336], AL
    
                 
                    
    ;Devolver el control al OS    
    MOV AX, 4C00h
    INT 21h  
ends

end start
