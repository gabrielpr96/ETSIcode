(defrule RemplazoC_DL
	?f <- (palabra $?x C $?y)
	=>
	(assert (palabra ?x D L ?y))
	(retract ?f)
)
(defrule RemplazoC_BM
	?f <- (palabra $?x C $?y)
	=>
	(assert (palabra ?x B M ?y))
	(retract ?f)
)
(defrule RemplazoB_MM
	?f <- (palabra $?x B $?y)
	=>
	(assert (palabra ?x M M ?y))
	(retract ?f)
)
(defrule RemplazoZ_BBM
	?f <- (palabra $?x Z $?y)
	=>
	(assert (palabra ?x B B M ?y))
	(retract ?f)
)

(deffacts vectores
	(palabra B C D)
)