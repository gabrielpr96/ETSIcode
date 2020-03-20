(defrule SumarVector
	(vector $?x)
	=>
	(printout t "Suma: " (+ (expand$ ?x)) crlf)
)