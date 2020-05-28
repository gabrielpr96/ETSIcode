;*****************************************
;***Suma los elementos del vecto A en B***
;*****************************************
.data
	A: 		.word 0,1,2,3,4,5,6,7,8,9
	B: 		.word 0
	Xtop: 	.word 10
	Xinc: 	.word 4
	k: 		.word 1

.text
start:
	
	LW R1, k			;R1: Decremento contador
	LW R2, Xtop			;R2: Indice contador
	LW R3, Xinc			;R3: Incremento posicion
	
	ADD R5, R0, R0		;R5: Indice
	ADD R6, R0, R0		;R6: Resultado
	
	loop:
		LW R7, A(R5)	;Carga el dato en el registro temporal R7
		ADD R6, R6, R7	;Realiza la suma
		
		ADD R5, R5, R3	;Incrementar la posicion
		SUB R2, R2, R1	;Decrementar el contador
		BNEZ R2, loop	;Repetir hasta que el contador llegue a 0
		NOP
		
	SW B, R6			;Deja el resultado en B
trap #6
