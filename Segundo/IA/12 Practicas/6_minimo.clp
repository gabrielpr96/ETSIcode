(defrule Minimo
	(vector $?x)
	=>
	(printout t "Minimo: " (min (expand$ ?x)) crlf)
)