(deftemplate Articulo
	(field Nombre)
	(field Tipo)
	(field Forrado)
	(field Empaquetado)
	(field Dimension)
)
(deftemplate Caja
	(field idCaja)
	(field Abierta)
	(field Empezada)
	(field TipoContenido)
	(field EspacioLibre)
)

(defrule ForrarArticulos
	?c <-(Articulo
			(Nombre ?N)
			(Tipo ?T)
			(Forrado No)
			(Empaquetado No)
			(Dimension ?D))
	=>
	(retract ?c)
	(assert (Articulo
				(Nombre ?N)
				(Tipo ?T)
				(Forrado Si)
				(Empaquetado No)
				(Dimension ?D)))
)

(defrule AbrirCaja
	?c <-(Caja
			(idCaja ?ID)
			(Abierta No)
			(Empezada No)
			(TipoContenido ?T)
			(EspacioLibre ?E))
	(not (Caja (Abierta Si)))
	=>
	(retract ?c)
	(assert (Caja
				(idCaja ?ID)
				(Abierta Si)
				(Empezada No)
				(TipoContenido ?T)
				(EspacioLibre ?E)))
)


(defrule IniciarCaja
	?a <- (Articulo
			(Nombre ?N)
			(Tipo ?T)
			(Forrado Si)
			(Empaquetado No)
			(Dimension ?D))
	?c <- (Caja
			(idCaja ?ID)
			(Abierta Si)
			(Empezada No)
			(EspacioLibre ?E))
	(test (<= ?D ?E))
	=>
	(retract ?c)
	(assert (Caja
				(idCaja ?ID)
				(Abierta Si)
				(Empezada Si)
				(TipoContenido ?T)
				(EspacioLibre (- ?E ?D))))
	(retract ?a)
	(assert (Articulo
				(Nombre ?N)
				(Tipo ?T)
				(Forrado Si)
				(Empaquetado Si)
				(Dimension ?D)))
)


(defrule MeterEnCaja
	?a <- (Articulo
			(Nombre ?N)
			(Tipo ?T)
			(Forrado Si)
			(Empaquetado No)
			(Dimension ?D))
	?c <- (Caja
			(idCaja ?ID)
			(Abierta Si)
			(Empezada Si)
			(TipoContenido ?T)
			(EspacioLibre ?E))
	(test (<= ?D ?E))
	=>
	(retract ?c)
	(assert (Caja
				(idCaja ?ID)
				(Abierta Si)
				(Empezada Si)
				(TipoContenido ?T)
				(EspacioLibre (- ?E ?D))))
	(retract ?a)
	(assert (Articulo
				(Nombre ?N)
				(Tipo ?T)
				(Forrado Si)
				(Empaquetado Si)
				(Dimension ?D)))
)

(defrule CerrarCaja
	?c <- (Caja
			(idCaja ?ID)
			(Abierta Si)
			(Empezada Si)
			(TipoContenido ?T)
			(EspacioLibre ?E))
	(not (and
		(Articulo
			(Tipo ?T)
			(Empaquetado No)
			(Dimension ?D))
		(test (<= ?D ?E))
	))
	=>
	(retract ?c)
	(assert (Caja
				(idCaja ?ID)
				(Abierta No)
				(Empezada Si)
				(TipoContenido ?T)
				(EspacioLibre ?E)))
)

