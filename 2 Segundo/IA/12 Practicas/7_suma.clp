(defrule inicio
	(not (suma ?))
	=>
	(assert (suma 0))
)

(defrule SumarVector
	?v <- (vector ?x $?y)
	?r <- (suma ?s)
	=>
	(retract ?v ?r)
	(assert (suma (+ ?s ?x)))
	(assert (vector $?y))
)

(defrule final
	(vector)
	(suma ?s)
	=>
	(printout t "Suma: " ?s crlf)
)

(deffacts vectores
	(vector 6 8 4 6 9 1 4 6 10)
)