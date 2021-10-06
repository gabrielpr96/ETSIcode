(defrule Diferencia
	(or
		(and 
			(cadena1 $? ?x $?)
			(not (cadena2 $? ?x $?))
		)
		(and 
			(cadena2 $? ?x $?)
			(not (cadena1 $? ?x $?))
		)
	)
	(not (diferencia $? ?x $?))
	?f <- (diferencia $?y)
	=>
	(retract ?f)
	(assert (diferencia ?y ?x))
)

(deffacts vectores
	(cadena1 A B C D E)
	(cadena2 B C D F)
	(diferencia)
)