.MODEL SMALL ;El modelo pequeño tiene un segmento de codigo y otro de datos, pero como lo vamos a montar en un .COM solo podemos usar el segmento de codigo, 64KB.
.CODE ;Comienzo del segmento de codigo
	org 100h ;Le decimos al ensamblador que comience las instrucciones en 100h, porque esos 256 bytes se reservan para que el OS ponga su PSP, necesario en .COM
Programa_Int: ;Aquí comienza nuestro programa residente
	JMP Reside ;Tenemos que saltar, porque lo que sigue aquí no queremos que se ejecute, no es codigo, son datos, y luego esta la rutina de servicio, que tampoco queremos que se ejecute justo ahora. Vamos al codigo que hace residente nuestro programa.
	
	CONT DB 0		;Las variables las tenemos que declarar en este mismo segmento porque no tenemos segmento de datos
	MSJ DB '00:00' ;Esto es un array caracteres ASCII
	
Rutina_Servicio PROC ;Esta estas son las instrucciones que pondremos en memoria
	CLI	;Desactivamos las interrupciones para que nadie interrumpa una interrupción
	         
	     
	INC CONT		;Incrementamos el contador
	CMP CONT, 18	;Si llegamos a 18 interrupciones significa que ha pasado un segundo
	JB SALIR		;Si no hemos llegado, terminamos ya la rutina
	MOV CONT, 0 	;Reseteamos el contador
	
	MOV AX, 0B800h	;Copiamos a ES (segmento extra) la direccion del segmento de la memoria de video
	MOV ES, AX		;No podemos escribir en ES en modo de direccionamiento inmediato
	MOV AH, 00001111b	;Ponemos en AH el estilo que queremos que tenga el texto, este corresponde a letras blancos sobre fondo negro
	;Vamos a iterar, copiando datos de un sitio a otro
	MOV SI, 0	;Contador para MSJ, empozamos en el elemento 0
	MOV DI, 148	;Posicionarnos en pantalla justo al final de la primera linea
	BSTR:	;Inicio del bucle
		MOV AL, MSJ[SI]		;Copiamos a la parte baja de AX el codigo ASCII
		MOV ES:[DI], AX		;Movemos el caracter y el formato a la posicion correspondiente de la memoria de video
		ADD DI, 2 	;Incrementamos en 2 el contador, porque cada caracter en memoria de video ocupa dos bytes
		INC SI 		;Incrementamos el contador del array
	CMP SI, 4	;Compara el contador del array con 4, deja el resultado en los registros de estado aritmetico
	JBE BSTR	;Salta si es menor o igual, ya que el array tiene de la posicion 0 a la 4
	
	;Controlar el reloj
	
	CMP MSJ[4], '9' ;Si las unidades del segundero llegan a 9
	JNE TIMEINC	;En caso contrario incrementa las unidades del segundero
	MOV MSJ[4], '0'	;Pon las unidades del segundero a 0
	INC MSJ[3]		;Incrementa las decenas del segundero
	
	CMP MSJ[3], '6'	;Si las decenas del segundero llegan a 6
	JNE SALIR	;En caso contrario no hagas nada
	MOV MSJ[3], '0'	;Pon a 0 las decenas del segundero
	INC MSJ[1]	;Incrementa las unidades del minutero
	
	CMP MSJ[1], '9'	;Si las unidades del minutero llegan a 9
	JNE SALIR	;No hagas nada
	MOV MSJ[1], '0'	;Pon a 0 las unidades del minutero
	INC MSJ[0]	;Incrementa las decenas del minutero
	
	CMP MSJ[0], '6'	;Si las decenas del minutero llegan a 6
	JNE SALIR	;No hagas nada
	MOV MSJ[0], '0'	;Pon a 0 las decenas del minutero
	JMP SALIR	;No hagas nada más, no estamos contando las horas
	
	
	TIMEINC:	;Toca incrementar en 1 las unidades del segundero
	INC MSJ[4]
	SALIR:


	STI		;Activa las interrupciones
	IRET	;Retorno de la interrupcion. Si pusieramos solo RET seria un retorno de funcion, ni te imaginas la que podriamos liar cambiando un RET o por un IRET y vice versa.
ENDP	;Acaba la rutina de atencion a la interrupcion
Reside:	;Codigo que hace el programa residente, esta parte no, la anterior es la que carga en memoria

	MOV DX, offset Rutina_Servicio 	;Desplazamiento, donde comienza la rutina de servicio
	MOV AX, 0	;Escribimos en el registro del segmento extra 0, porque queremos escribir justo en el principio de la memoria, que es donde esta el vector de interrupcion.
	MOV ES, AX	;No podemos escribir con direccionamiento inmediato en ES, por eso usamos AX como intermediario
	MOV SI, 1Ch*4	;En el registro indice ponemos la posicion del vector de interrupciones donde queremos escribir la entrada. 1Ch porque es el codigo de la interrupcion de reloj. El *4 es debido a que cada entrada de esta tabla ocupa 4 bytes.
	CLI	;Desasctiva las interrupciones, porque estamos modificando el vector de interrupcion. Como tenemos que escribir dos datos, si viene una interrupcion mientras estamos a medias las consecuencias son inpredecibles.
	MOV ES:[SI], DX	;Copiamos el desplazamiento de la rutina de servicio
	MOV ES:[SI+2], CS	;Dos bytes mas acabajo copiamos el segmento de nuestro codigo
	STI	;Habilita las interrupciones
	MOV DX, offset Reside ;Ponemos en DX el desplazamiento del final de la rutina de atencion a la interrupcion, que acaba justo donde empieza esta seccion de codigo.
	INT 27h	;Esta interrupcion significa, termina y sigue residente
END Programa_Int ;Acaba Todo el programa