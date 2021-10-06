data segment
    
    A DW 500
    B DW 400 
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:

    ;Inicializar el segmento de datos
    MOV AX, SEG A
    MOV DS, AX      
    
    ;Ejercicio que resta 500 menos 400 guardado en variables
    MOV AX, A
    SUB AX, B

mov ax, 4c00h
int 21h  

ends

end start
