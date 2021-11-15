# Fichero de configuración AutoSIG
AutoSIG es un programa para crear soluciones de integración, especificadas mediante un fichero de configuración XML. La base de AutoSIG es B0vE_SIG. Ambos se distribuyen con licencia MIT.

## Estructura del fichero
La raiz del fichero se llama process. En la etiqueta nombre se coloca un identificador para el proceso. El resto de etiquetas se detallan en los apartados siguientes.

```
<process>
    <name></name>
    <config></config>
    <tasks></tasks>
    <adapters></adapters>
</process>
```

## Config
Los dos posibles elementos dentro de config son debug y async, los dos ogligatorios.
```
<config>
    <debug>true</debug>
    <async>false</async>
</config>
``` 
Debug permite que salgan por consola mensajes de depuración. Async permite o no concurrencia dentro del proceso.

## Tasks
Aquí especificaremos las tareas de las que se compone la solución, su configuración y sus conexiones. El nombre de la tarea (name) es obligatorio y distingue mayusculas. El tipo (type) no distingue mayusculas y también es obligatorio. El campo config sólo es obligatorio en ciertos tipos de tareas. Las salidas (outputs) está formada por una lista de elementos output con el nombre de las tareas o adaptadores a los que se conecta. Los puertos se generan automaticamente para intervenir en las conexiones con los adaptadores, no deben tenerse en cuenta en este fichero.
```
<task>
    <name></name>
    <type></type>
    <config></config>
    <outputs></outputs>
</task>
```
En los siguientes ejemplos se omiten las etiquetas name y outputs. Consulta el final de este documento para ver ejemplos completos.

### Correlator
Puede operar con configuración o sin ella. Si no se especifica, el correlator usará la ID de correlación.
```
<task>
    <type>correlator</type>
</task>
```
Para cambiar el comportamiento se especificará una expresión XPath en la configuración. El correlator unira los mensajes por el valor que devuelvan estas expresiones.
```
<task>
    <type>correlator</type>
    <config>/XPath/expresion</config>
</task>
```

### Merger
Esta tarea no se puede configurar.
```
<task>
    <type>merger</type>
</task>
```

### Filter
Esta tarea requiere configuración. El tipo de condición que acepta es una condición configurable, así que se deberá proporcionar: la expresión xpath que extrae los datos sobre los que se aplica la comprobación, el tipo de condición y un valor fijo con el que se compara. Este último no es necesario en todos los tipos de condiciones.
```
<task>
    <type>filter</type>
    <config>
        <selector>/XPath/expression</selector>
        <condition>str_equals</condition>
        <value>FixedValue</value>
    </config>
</task>
```
Estas son las operaciones soportadas por la condición de filtrado configurable. El nombre no distingue mayusculas.
| Tipo | Requiere valor |
|---|---|
| str_equals | Sí |
| str_distinct | Sí |
| int_equals | Sí |
| int_distinct | Sí |
| int_less_than | Sí |
| int_grater_than | Sí |
| decimal_less_than | Sí |
| decimal_greater_than | Sí |
| empty | No |
| not_empty | No |
| exists | No |
| not_exists | No |


### Distributor
Esta tarea requiere configuración. Se deben especificar tantas etiquetas case cómo salidas tenga la tarea menos uno. La última salida es la salida por defecto. Dentro de las etiquetas case debe especificarse los ajustes para una condición configurable, vease la tarea Filter para más información.
```
<task>
    <type>distributor</type>
    <config>
        <case>
            <selector>/XPath/expression</selector>
            <condition>str_equals</condition>
            <value>FixedValue</value>
        </case>
        ...
    </config>
</task>
```

### Replicator
Esta tarea no requiere configuración.
```
<task>
    <type>replicator</type>
</task>
```

### Slimmer
Esta tarea requiere que se le indique una lista de etiquetas selector con expresiones xpath en la configuración. Eliminará del cuerpo del mensaje los nodos devueltos por cada expresión.
```
<task>
    <type>slimmer</type>
    <config>
        <selector>/XPath/expression/1</selector>
        <selector>/XPath/expression/2</selector>
        ...
    </config>
</task>
```

### Context Slimmer
Esta tarea no requiere configuración. La lista de selectores la recibe por su segunda entrada.
```
<task>
    <type>context_slimmer</type>
</task>
```
El siguiente es el formato de mensaje que espera recibir cómo configuración dinámica.
```
<list>
    <item>/XPath/expression/1</item>
    <item>/XPath/expression/2</item>
    ...
</list>
```

### Enricher
Esta tarea requiere configuración, se deberá incluir un sólo elemento en la etiqueta configuración. Este elemento será la raiz del documento que mezclará con los mensajes recibidos.
```
<task>
    <type>enricher</type>
    <config>
        <document>
            ...
        </document>
    </config>
</task>
```
Nota: document es la raiz del documento, remplazela con la suya e incluya el contenido en ella.

### Context Enricher
Esta tarea no requiere configuración. Combinará los mensajes que lleguen por sus dos entradas.
```
<task>
    <type>context_enricher</type>
</task>
```

### Correlation ID Setter
Esta tarea no requiere configuración. Su comportamiento por defecto es establecer IDs de correlación mediante un contador autoincrementado.
```
<task>
    <type>correlation_id_setter</type>
</task>
```

### Translator
Esta tarea necesita un estilo XSLT en su configuración. Indique la versión de XSLT para asegurar la compatibilidad.
 ```
<task>
    <type>translator</type>
    <config>
        <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
            ...
        </xsl:stylesheet>
    </config>
</task>
```

### Splitter
Esta tarea requiere una expresión XPath en su configuración. Los fragmentos se formarán con los nodos que devuelva esta expresión. El resto de contenido del mensaje que no esté cubierto por esta expresión se perderá.
 ```
<task>
    <type>splitter</type>
    <config>/XPath/expresion</config>
</task>
```

### Aggregator
Esta tarea requiere un nombre en la configuración, será usado cómo etiqueta raiz en el mensaje recompuesto. Se puede especificar más de un nombre, en tal caso se incluiran uno dentro del otro por orden; la raiz será el primer nombre.
 ```
<task>
    <type>aggregator</type>
    <config>
        <root>RootElement</root>
        <root>ChildElement1</root>
        ...
    </config>
</task>
```

### Chopper
Esta tarea requiere una expresión XPath en su configuración, del mismo modo que la tarea Splitter.
 ```
<task>
    <type>chopper</type>
    <config>/XPath/expresion</config>
</task>
```

### Assembler
Esta tarea requiere uno o más nombres de raiz en la configuración, del mismo modo que la tarea Aggregator.
 ```
<task>
    <type>assembler</type>
    <config>
        <root>RootElement</root>
        <root>ChildElement1</root>
        ...
    </config>
</task>
```

## Adapters
Los adaptadores siguen una estructura similar a las tareas, el sistema de nombres es el mismo y las conexiones mediante la etiqueta outputs también.
 ```
<adapter>
    <name></name>
    <type></type>
    <config></config>
    <outputs></outputs>
</adapter>
```

### Dir Watcher
Es un adaptador de entrada. Introduce un mensaje por cada fichero nuevo que encuentra en la carpeta que vigila. El cuerpo del mensaje es el contenido del fichero. Requiere especificar la carpeta a vigilar en la configuración.
 ```
<adapter>
    <type>dir_watcher</type>
    <config>/path/to/folder</config>
</adapter>
```

### Dir Outputter
Es un adaptador de salida. Crea un fichero en una carpeta por cada mensaje que recibe. El contenido del fichero es el cuerpo del mensaje. Requiere especificar la carpeta a salida en la configuración.
 ```
<adapter>
    <type>dir_outputter</type>
    <config>/path/to/folder</config>
</adapter>
```

### MySQL
Es un adaptador de solicitud. Realiza una petición a un servidor MySQL. Contesta con la respuesta que haya dado el servidor. Se debe configurar con los parámetros para la conexión.
 ```
<adapter>
    <type>mysql</type>
    <config>
        <host></host>
        <port></port>
        <db></db>
        <user></user>
        <pass></pass>
    </config>
</adapter>
```
Formato de mensaje esperado
 ```
<sql> QUERY </sql>
```
Formato de la respuesta
 ```
<Results>
    <Row>
        <ColumnaDeEjemplo></ColumnaDeEjemplo>
        ...
    </Row>
    ...
</Results>
```

### MySQL Multi Query
Es un adaptador de salida. Realiza multiples consultas por mensaje a un servidor MySQL. No devuelve el resultado. Requiere la misma configuración que el adatador MySQL.
 ```
<adapter>
    <type>mysql_multi_query</type>
    <config>
        <host></host>
        <port></port>
        <db></db>
        <user></user>
        <pass></pass>
    </config>
</adapter>
```
Formato de mensaje esperado
 ```
<queries>
    <sql> QUERY 1 </sql>
    <sql> QUERY 2 </sql>
    ...
<queries>
```

### Web API
Es un adaptador de solicitud. Realiza peticiones post a una API Web. El cuerpo de la petición y la respuesta deben estar en formato json, pesea esto el adaptador se comunica en XML con la solución. Se debe configurar con el endpoint de la API.
 ```
<adapter>
    <type>web_api</type>
    <config>EndPoint</config>
</adapter>
```

### SET
Es un adaptador de solicitud. Aporta memoria al proceso. No requiere configuración.
 ```
<adapter>
    <type>set</type>
</adapter>
```
Este es el formato de mensaje esperado.
 ```
<query>
    <action> {create | delete} </action>
    <value> ValorAAgregarOEliminar </value>
</query>
```
El adaptador responderá con verdadero si no ha sido necesaria ninguna acción en la memoria, es decir; si la operación es de creación pero el valor ya existía, o de eliminación y el valor no existía.
 ```
<response> {true | false} </response>
```

### Console (Testing)
Es un adaptador de entrada. Introduce los mensajes que recibe por consola. No requiere configuración.
 ```
<adapter>
    <type>console</type>
</adapter>
```

### Screen (Testing)
Es un adaptador de salida. Muestra los mensajes que le llegan por pantalla. No requiere configuración.
 ```
<adapter>
    <type>screen</type>
</adapter>
```

### Stub Input (Debugging)
Es un adaptador de entrada. Introduce mensajes manualmente. No requiere configuración.
 ```
<adapter>
    <type>stub_input</type>
</adapter>
```

### Stub Output (Debugging)
Es un adaptador de salida. Muestra los mensajes que le llegan. No requiere configuración.
 ```
<adapter>
    <type>stub_output</type>
</adapter>
```


### Stub Request (Debugging) *deprecated*
Es un adaptador de solicitud. Contesta manualmente a los mensajes que le llegan. No requiere configuración.
 ```
<adapter>
    <type>stub_request</type>
</adapter>
```

## Ejemplos
La siguiente es una lista de los ficheros de configuración existentes.

- Cafe
- Ejercicio 1
- Ejercicio 2
- Ejercicio 3
- Ejercicio 4










