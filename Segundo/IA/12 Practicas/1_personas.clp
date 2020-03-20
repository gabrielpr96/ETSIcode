(defrule LosPadresQuierenALosHijos
	(EsPadre ?x)
	=>
	(assert (QuiereASusHijos ?x))
)