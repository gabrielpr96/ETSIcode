;*******************************
;***Suma de dos números C=A+B***
;*******************************
.data
	A: .word 2
	B: .word 8
	C: .word 0

.text
main:
	LW R1, A
	LW R2, B
	ADD R3, R2, R1
	SW C, R3
trap 6