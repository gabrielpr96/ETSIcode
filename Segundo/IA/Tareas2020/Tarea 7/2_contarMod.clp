(defrule Iniciar
	(not (resultado ?))
	=>
	(assert (resultado 0))
	(assert (aux))
)
(defrule Contar
	(vector $? ?e $?)
	(not (aux $? ?e $?))
	?fa <- (aux $?x)
	?fr <- (resultado ?c)
	=>
	(retract ?fa)
	(retract ?fr)
	(assert (aux ?x ?e))
	(assert (resultado (+ ?c 1)))
)
(defrule Final
	(not
		(and
			(vector $? ?e $?)
			(not (aux $? ?e $?))
		)
	)
	(resultado ?c)
	=>
	(printout t "Numero de elementos: " ?c crlf)
	
)


(deffacts vectores
	(vector 144 6 15 4 55)
)