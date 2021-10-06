(defrule Minimo
	(vector $? ?x $?)
	(not (vector $? ?y&:(< ?y ?x) $?))
	=>
	(printout t "Minimo: " ?x crlf)
)

(deffacts vectores
	(vector 6 8 4 6 9 1 4 6 10)
)