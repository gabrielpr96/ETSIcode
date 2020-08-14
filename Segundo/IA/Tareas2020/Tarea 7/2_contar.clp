(defrule Iniciar
	(not (resultado ?))
	=>
	(assert (resultado 0))
)
(defrule Contar
	?fv <- (vector ? $?x)
	?fr <- (resultado ?c)
	=>
	(retract ?fv)
	(retract ?fr)
	(assert (vector ?x))
	(assert (resultado (+ ?c 1)))
)
(defrule Final
	(vector)
	(resultado ?c)
	=>
	(printout t "Numero de elementos: " ?c crlf)
)


(deffacts vectores
	(vector 144 6 15 4 55)
)