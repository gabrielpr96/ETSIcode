(deftemplate Bandera
	(field Pais)
	(multifield Colores)
)

(defrule BuscarColores
	(Bandera (Pais ?P))
	(not
		(and
			(ColoresABuscar $? ?C $?)
			(not(Bandera (Pais ?P) (Colores $? ?C $?)))
		)
	)
	=>
	(printout t "El pais " ?P " tiene una bandera con esos colores" crlf)
)