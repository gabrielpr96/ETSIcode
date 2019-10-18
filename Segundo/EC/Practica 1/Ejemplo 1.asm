data segment

ends

stack segment
    dw   128  dup(0)
ends

code segment
start:

    ;Ejercicio que resta 500 menos 500
    mov ax, 500
    sub ax, 500

mov ax, 4c00h
int 21h  

ends

end start
