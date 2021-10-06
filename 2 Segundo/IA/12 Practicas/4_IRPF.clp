(deftemplate Persona
	(field Nombre)
	(field Edad)
	(field NombreConyuge)
	(field PosicionEconomica)
	(field Salario)
)

(defrule ReglaA
	(Persona
		(Nombre ?N)
		(Edad 60))
	=>
	(printout t "Personas con edad 60: " ?N crlf)
)
(defrule ReglaB
	(Persona
		(Nombre ?N)
		(Edad 40)
		(Salario ?S))
	=>
	(printout t "Personas con edad 40: " ?N " salario: " ?S crlf)
)
(defrule ReglaC
	(Persona
		(Nombre ?N)
		(Edad ?E)
		(NombreConyuge ?C)
		(PosicionEconomica ?P)
		(Salario ?S))
	=>
	(printout t ?N " tiene " ?E " edad, posicion economica " ?P ", salario " ?S ", conyuge " ?C crlf)
)
(defrule ReglaD
	(Persona
		(Nombre ?N)
		(NombreConyuge ?C))
	(Persona
		(Nombre ?C)
		(PosicionEconomica desahogada))
	=>
	(printout t ?N " es conyuge de una persona con posicion economica desahogada" crlf)
)
(defrule ReglaE
	(Persona
		(Nombre ?N)
		(NombreConyuge ?C))
	(Persona
		(Nombre ?C)
		(PosicionEconomica desahogada))
	=>
	(assert (DatosFiscales ?N ConyugeDesahogado))
)
(defrule ReglaF
	(DatosFiscales ?N ConyugeDesahogado)
	?P <- (Persona (Nombre ?N))
	=>
	(retract ?P)
)
(defrule ReglaG
	?P <- (Persona (PosicionEconomica desahogada))
	=>
	(retract ?P)
)


(deffacts AlgunasPersonas
	(Persona
		(Nombre Pepe)
		(Edad 40)
		(NombreConyuge Trinidad)
		(PosicionEconomica desahogada)
		(Salario 2500))
	(Persona
		(Nombre Juan)
		(Edad 40)
		(NombreConyuge Lepotero)
		(PosicionEconomica precaria)
		(Salario 250))
	(Persona
		(Nombre Alberto)
		(Edad 40)
		(NombreConyuge Muzi)
		(PosicionEconomica buena)
		(Salario 1000))
	(Persona
		(Nombre Trinidad)
		(Edad 60)
		(NombreConyuge Pepe)
		(PosicionEconomica enApuros)
		(Salario 0))
	(Persona
		(Nombre Lepotero)
		(Edad 60)
		(NombreConyuge Juan)
		(PosicionEconomica desahogada)
		(Salario 5000))
	(Persona
		(Nombre Muzi)
		(Edad 60)
		(NombreConyuge Alberto)
		(PosicionEconomica normal)
		(Salario 1000))
)