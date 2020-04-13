(defrule regla
	?h1 <- (resultado $?r)
	?h2 <- (datos ?x $?d)
	(not (datos ?y&:(< ?y ?x) $?))
	=>
	(retract ?h1 ?h2)
	(assert (resultado $?r ?x)
			(datos ?d))
)

(defrule regla1
	?h1 <- (dato1 $?i1 ?x $?f1)
	?h2 <- (dato2 $?i2 ?x $?f2)
	=>
	(retract ?h1 ?h2)
	(assert (dato1 $?i1 $?f1)
			(dato2 $?i2 $?f2))
)

(defrule regla2
	?h1 <- (dato1 $? ?x $?)
	(not (dato2 $? ?x $?))
	?h2 <- (dato2 $?)
	=>
	(retract ?h1 ?h2)
	(assert (respuesta NO))
)

(defrule regla3
	?h1 <- (dato1)
	?h2 <- (dato2)
	=>
	(retract ?h1 ?h2)
	(assert (respuesta SI))
)


(deffacts ej1a	(dato1 1 2 3 1)	(dato2 2 1 1 3))
;(deffacts ej1a	(dato1 1 2 3 1)	(dato2 2 1 2 3))