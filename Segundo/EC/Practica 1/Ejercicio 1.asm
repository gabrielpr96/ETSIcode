data segment
    
    A DW 0010h
    T DW 004Ah, 001Bh
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:

    ;Inicializar el segmento de datos
    MOV AX, SEG A
    MOV DS, AX          
    
    MOV DX, OFFSET T
    MOV AX, 0003h
    INT 10h
    
                    

mov ax, 4c00h
int 21h  

ends

end start
