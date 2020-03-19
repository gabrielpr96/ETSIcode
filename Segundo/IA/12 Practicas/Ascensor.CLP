(defmodule MAIN)
(defmodule PETICIONES)

(deffacts MAIN::ciclo
	(ciclo 0)
)
(deffacts MAIN::sentido
	(sentido SUBIENDO)
)
(deffacts MAIN::planta
	(planta 0)
)

(defrule MAIN::cambioCiclo
	=> (+ ciclo 1)
)
(defrule MAIN::hola
	=> (printout t "Hola" crlf)
)

(deffacts PETICIONES::datos
	(datos 2 3 c c 1 c 2 c 4 c 0 1 c 1 c c c c 0 4 c 2 c 3 c 1 c 1 3 c 2 c 0 c 4 c 1 c 2 c 3 c 4 c c 1 c)
)

