(defrule Unir
	(cadena $? ?x $?)
	(not (union $? ?x $?))
	?f <- (union $?y)
	=>
	(retract ?f)
	(assert (union ?y ?x))
)

(deffacts vectores
	(cadena B C A D E E B C E)
	(cadena E E B F D E)
	(union )
)