(deftemplate Producto
	(field CodigoVendedor)
	(field CodigoProducto)
	(field PVPProducto)
)

(defrule BuscarFalloDependenciaFuncional
	(Producto
		(CodigoProducto ?C)
		(PVPProducto ?P1))
	(Producto
		(CodigoProducto ?C)
		(PVPProducto ?P2))
	(test (<> ?P1 ?P2))
	=>
	(assert (noExisteDependenciaFuncional))
)
(defrule ExisteDependenciaFuncional
	(not (noExisteDependenciaFuncional))
	=>
	(printout t "Existe dependencia funcional" crlf)
)
(defrule NoExisteDependenciaFuncional
	(noExisteDependenciaFuncional)
	=>
	(printout t "No existe dependencia funcional" crlf)
)