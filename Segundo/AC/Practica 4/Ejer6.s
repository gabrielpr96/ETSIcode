;*****************************************
;***Suma los elementos del vecto A en B***
;*****************************************
.data
	A: 		.word 0,1,0,0,0,0,0,0,0,0
	B: 		.word 0
	Xtop: 	.word 10
	Xinc: 	.word 4
	k: 		.word 1

.text
start:

	ADDI R2, R0, #8		;R2: Indice contador
	
	ADD  R5, R0, R0		;R5: Indice -2
	ADDI R6, R0, #4		;R6: Indice -1
	ADDI R7, R0, #8		;R7: Indice  0
	
	loop:
		LW R8, A(R5)	;R7: Elemento -2
		LW R9, A(R6)	;R8: Elemento -1
		
		ADDI R6, R6, #4	;Incrementar Indice -1
		ADDI R7, R7, #4	;Incrementar Indice  0
		
		ADD R10, R8, R9	;R10:Elemento  0
		
		SUBI R2, R2, #1	;Decrementar el contador
		ADDI R5, R5, #4	;Incrementar Indice -2
		
		SW A(R7), R10	;Almacenar el nuevo elemento
	
		BNEZ R2, loop	;Repetir hasta que el contador llegue a 0
		NOP
trap #6
