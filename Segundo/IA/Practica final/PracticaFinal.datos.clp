(deffacts AlgunasEnfermedades
	(Enfermedad
		(Nombre Gripe)
		(Sintomas Tos Cansancio Fiebre Dolor))
	(Enfermedad
		(Nombre Rubeola)
		(Sintomas Fiebre Escalofrios Jaqueca Secredon))
	(Enfermedad
		(Nombre Malaria)
		(Sintomas Diarrea Fiebre Ictericia Escalofrios))
	(Enfermedad
		(Nombre Hepatitis)
		(Sintomas Diarrea Nauseas Ictericia))
	(Enfermedad
		(Nombre Tuberculosis)
		(Sintomas Tos Cansancio Fiebre Escalofrios))
	(Enfermedad
		(Nombre Anemia)
		(Sintomas Cansancio Nauseas Apatia))
	(Tratamiento
		(Nombre Jarabe)
		(Enfermedades Gripe))
	(Tratamiento
		(Nombre Contrex)
		(Enfermedades Gripe))
	(Tratamiento
		(Nombre Vitamina)
		(Enfermedades Anemia))
	(Tratamiento
		(Nombre Vacuna)
		(Enfermedades Gripe Rubeola Malaria Hepatitis))
	(Tratamiento
		(Nombre Pastilla)
		(Enfermedades Rubeola Hepatitis Tuberculosis))
	(Doctor
		(Nombre Otorrino)
		(Tratamientos Jarabe Contrex))
	(Doctor
		(Nombre Endocrinologo)
		(Tratamientos Vacuna))
	(Doctor
		(Nombre Nutricionista)
		(Tratamientos Vitamina))
	(Doctor
		(Nombre MedicoGeneral)
		(Tratamientos Vacuna Pastilla))
)

(deffacts Diagnostico
	(Enfermedades )
	(Tratamientos )
)

;(assert (Sintomas Tos))