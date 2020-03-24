(deftemplate Enfermedad
	(field Nombre)
	(multifield Sintomas)
)
(deftemplate Tratamiento
	(field Nombre)
	(multifield Enfermedades)
)
(deftemplate Doctor
	(field Nombre)
	(multifield Tratamientos)
)

(defrule DetectarEnfermedades
	(Sintomas $? ?S $?)
	(Enfermedad
		(Nombre ?N)
		(Sintomas $? ?S $?))
	(not (Enfermedades $? ?N $?))
	?f <- (Enfermedades $?E)
	=>
	(retract ?f)
	(assert (Enfermedades ?E ?N))
	(printout t "Basado en el sintoma " ?S " puedes tener " ?N crlf)
)
(defrule BuscarTratamientos
	(Enfermedades $? ?E $?)
	(Tratamiento
		(Nombre ?N)
		(Enfermedades $? ?E $?))
	(Doctor
		(Nombre ?D)
		(Tratamientos $? ?N $?))
	(not (Tratamientos $? ?N $?))
	?f <- (Tratamientos $?T)
	=>
	(retract ?f)
	(assert (Tratamientos ?T ?N))
	(printout t "Para la enfermedad " ?E " se aconseja el tratamiento " ?N " administrado por un " ?D crlf)
)