(deftemplate FichaPaciente
	(field Nombre)
	(field Edad)
	(field Casado)
	(field Sexo)
	(field Peso)
)
(deftemplate DatosExploracion
	(field Nombre)
	(multifield Sintomas)
	(field GravedadAfeccion)
)
(deftemplate Diagnostico
	(field Nombre)
	(field Resultado)
	(field ProximaRevision)
)
(deftemplate Terapia
	(field Nombre)
	(field PrincipioActivo)
	(field Posologia)
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
	(assert (Diagnostico (Nombre ?N)(Resultado Eccema)))
)
(defrule MostrarDiagnosticos
	(Diagnostico
		(Nombre ?N)
		(Resultado ?R))
	=>
	(printout t "Posible diagnostico para el paciente " ?N ": " ?R crlf)
)
(defrule IdentificarBebes
	(FichaPaciente
		(Nombre ?N)
		(Edad ?E & :(< ?E 2)))
		=>
		(assert (Paciente ?N Es_un_bebe))
)
(defrule SugerirTerapiaCorticoides
	(or (and (DatosExploracion (Nombre ?N)) (not (Paciente ?N Es_un_bebe)))
		(DatosExploracion (Nombre ?N) (GravedadAfeccion alta))
	)
	(FichaPaciente
		(Nombre ?N))
	=>
	(assert (Terapia (Nombre ?N)(PrincipioActivo Corticoidos)))
)
(defrule SugerirTerapiaCremaHidratante
	(DatosExploracion
		(Nombre ?N)
		(GravedadAfeccion baja))
	(Paciente ?N Es_un_bebe)
	(FichaPaciente
		(Nombre ?N))
	=>
	(assert (Terapia (Nombre ?N)(PrincipioActivo CremaHidratante)))
)
(defrule MostrarTerapia
	(Terapia
		(Nombre ?N)
		(PrincipioActivo ?R))
	=>
	(printout t "Terapia para el paciente " ?N ": " ?R crlf)
)