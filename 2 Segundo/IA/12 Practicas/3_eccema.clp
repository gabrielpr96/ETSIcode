(deftemplate FichaPaciente
	(field Nombre)
	(field Casado)
	(field Direccion)
)
(deftemplate DatosExploracion
	(field Nombre)
	(multifield Sintomas)
	(field GravedadAfeccion)
)

(defrule DiagnosticoEccema
	(or (DatosExploracion
		(Nombre ?N)
		(Sintomas $? picor $? vesiculas $?))
		(DatosExploracion
		(Nombre ?N)
		(Sintomas $? vesiculas $? picor $?)))
	(FichaPaciente
		(Nombre ?N))
	=>
	(printout t "Posible diagnostico para el paciente " ?N ": Eccema" crlf)
)