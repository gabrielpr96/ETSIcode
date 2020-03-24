(defrule Minimo
	(vector $?x)
	=>
	(printout t "Minimo: " (min (expand$ ?x)) crlf)
)

(deffacts vectores
	(vector 6 8 4 6 9 1 4 6 10)
)