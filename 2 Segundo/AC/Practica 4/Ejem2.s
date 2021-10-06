;**************************
;***Suma A a un vector X***
;**************************
.data
	A: .word 10
	X: .word 0,1,2,3,4
	Xtop: .word 5
	Xpointer: .word 4
	
	k: .word 1

.text
start:	;Desenrollado de bucle
	
	LW R1, A			;Dato a sumar
	LW R2, Xtop			;Limite, contador
	LW R3, Xpointer		;Incremento
	LW R4, k			;1
	
	ADD R5, R0, R0		;Indice
	ADD R6, R0, R0		;tmp
	
loop:
		LW R6, X(R5)	;Carga el dato
		ADD R6, R1, R6	;Realiza la suma
		SW X(R5), R6	;Deja el dato en su sitio
		
		ADD R5, R5, R3	;Incrementar en el indice
		SUB R2, R2, R4	;Decrementar el contador
		
		
		LW R6, X(R5)	;Carga el dato
		ADD R6, R1, R6	;Realiza la suma
		SW X(R5), R6	;Deja el dato en su sitio
		
		ADD R5, R5, R3	;Incrementar en el indice
		SUB R2, R2, R4	;Decrementar el contador
		
		LW R6, X(R5)	;Carga el dato
		ADD R6, R1, R6	;Realiza la suma
		SW X(R5), R6	;Deja el dato en su sitio
		
		ADD R5, R5, R3	;Incrementar en el indice
		SUB R2, R2, R4	;Decrementar el contador
		
		LW R6, X(R5)	;Carga el dato
		ADD R6, R1, R6	;Realiza la suma
		SW X(R5), R6	;Deja el dato en su sitio
		
		ADD R5, R5, R3	;Incrementar en el indice
		SUB R2, R2, R4	;Decrementar el contador
		
		LW R6, X(R5)	;Carga el dato
		ADD R6, R1, R6	;Realiza la suma
		SW X(R5), R6	;Deja el dato en su sitio
		
		ADD R5, R5, R3	;Incrementar en el indice
		SUB R2, R2, R4	;Decrementar el contador
		
		NOP
trap #6