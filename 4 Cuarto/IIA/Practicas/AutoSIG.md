# Fichero de configuración AutoSIG
AutoSIG es un programa para crear soluciones de integración, especificadas mediante un fichero de configuración XML. La base de AutoSIG es B0vE_SIG. Ambos se distribuyen con licencia MIT.

## Estructura del fichero
La raiz del fichero se llama process. En la etiqueta nombre se coloca un identificador para el proceso. El resto de etiquetas se detallan en los apartados siguientes.

```xml
<process>
    <name></name>
    <config></config>
    <tasks></tasks>
    <adapters></adapters>
</process>
```

## Config
Los dos posibles elementos dentro de config son debug y async, los dos ogligatorios.
```xml
<config>
    <debug>true</debug>
    <async>false</async>
</config>
``` 
Debug permite que salgan por consola mensajes de depuración. Async permite o no concurrencia dentro del proceso.

## Tasks
Aquí especificaremos las tareas de las que se compone la solución, su configuración y sus conexiones. El nombre de la tarea (name) es obligatorio y distingue mayusculas. El tipo (type) no distingue mayusculas y también es obligatorio. El campo config sólo es obligatorio en ciertos tipos de tareas. Las salidas (outputs) está formada por una lista de elementos output con el nombre de las tareas o adaptadores a los que se conecta. Los puertos se generan automaticamente para intervenir en las conexiones con los adaptadores, no deben tenerse en cuenta en este fichero.
```xml
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
```xml
<task>
    <type>correlator</type>
</task>
```
Para cambiar el comportamiento se especificará una expresión XPath en la configuración. El correlator unira los mensajes por el valor que devuelvan estas expresiones.
```xml
<task>
    <type>correlator</type>
    <config>/XPath/expresion</config>
</task>
```

### Merger
Esta tarea no se puede configurar.
```xml
<task>
    <type>merger</type>
</task>
```

### Filter
Esta tarea requiere configuración. El tipo de condición que acepta es una condición configurable, así que se deberá proporcionar: la expresión xpath que extrae los datos sobre los que se aplica la comprobación, el tipo de condición y un valor fijo con el que se compara. Este último no es necesario en todos los tipos de condiciones.
```xml
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
```xml
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
```xml
<task>
    <type>replicator</type>
</task>
```

### Slimmer
Esta tarea requiere que se le indique una lista de etiquetas selector con expresiones xpath en la configuración. Eliminará del cuerpo del mensaje los nodos devueltos por cada expresión.
```xml
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
```xml
<task>
    <type>context_slimmer</type>
</task>
```
El siguiente es el formato de mensaje que espera recibir cómo configuración dinámica.
```xml
<list>
    <item>/XPath/expression/1</item>
    <item>/XPath/expression/2</item>
    ...
</list>
```

### Enricher
Esta tarea requiere configuración, se deberá incluir un sólo elemento en la etiqueta configuración. Este elemento será la raiz del documento que mezclará con los mensajes recibidos.
```xml
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
```xml
<task>
    <type>context_enricher</type>
</task>
```

### Correlation ID Setter
Esta tarea no requiere configuración. Su comportamiento por defecto es establecer IDs de correlación mediante un contador autoincrementado.
```xml
<task>
    <type>correlation_id_setter</type>
</task>
```

### Translator
Esta tarea necesita un estilo XSLT en su configuración. Indique la versión de XSLT para asegurar la compatibilidad.
 ```xml
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
 ```xml
<task>
    <type>splitter</type>
    <config>/XPath/expresion</config>
</task>
```

### Aggregator
Esta tarea no requiere configuración, restraura completamente el mensaje original.
 ```xml
<task>
    <type>aggregator</type>
</task>
```

### Chopper
Esta tarea requiere una expresión XPath en su configuración, del mismo modo que la tarea Splitter.
 ```xml
<task>
    <type>chopper</type>
    <config>/XPath/expresion</config>
</task>
```

### Assembler
Esta tarea no requiere configuración, restraura completamente el mensaje original.
 ```xml
<task>
    <type>assembler</type>
</task>
```

## Adapters
Los adaptadores siguen una estructura similar a las tareas, el sistema de nombres es el mismo y las conexiones mediante la etiqueta outputs también.
 ```xml
<adapter>
    <name></name>
    <type></type>
    <config></config>
    <outputs></outputs>
</adapter>
```

### Dir Watcher
Es un adaptador de entrada. Introduce un mensaje por cada fichero nuevo que encuentra en la carpeta que vigila. El cuerpo del mensaje es el contenido del fichero. Requiere especificar la carpeta a vigilar en la configuración.
 ```xml
<adapter>
    <type>dir_watcher</type>
    <config>/path/to/folder</config>
</adapter>
```

### Dir Outputter
Es un adaptador de salida. Crea un fichero en una carpeta por cada mensaje que recibe. El contenido del fichero es el cuerpo del mensaje. Requiere especificar la carpeta a salida en la configuración.
 ```xml
<adapter>
    <type>dir_outputter</type>
    <config>/path/to/folder</config>
</adapter>
```

### MySQL
Es un adaptador de solicitud. Realiza una petición a un servidor MySQL. Contesta con la respuesta que haya dado el servidor. Se debe configurar con los parámetros para la conexión.
 ```xml
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
```xml
<sql> QUERY </sql>
```
Formato de la respuesta
```xml
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
```xml
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
```xml
<queries>
    <sql> QUERY 1 </sql>
    <sql> QUERY 2 </sql>
    ...
<queries>
```

### Web API
Es un adaptador de solicitud. Realiza peticiones post a una API Web. El cuerpo de la petición y la respuesta deben estar en formato json, pesea esto el adaptador se comunica en XML con la solución. Se debe configurar con el endpoint de la API.
```xml
<adapter>
    <type>web_api</type>
    <config>EndPoint</config>
</adapter>
```

### REST API
Es un adaptador de solicitud. Realiza peticiones a una API Restfull. Todos los datos relativos a la petición se establecen por mensaje. El cuerpo se envia en json.
```xml
<adapter>
    <type>rest_api</type>
</adapter>
```
Los mensajes que recibe tienen el sigueinte formato. La autorización por OAuth2 no está implementada.
```xml
<request>
    <url>http://example.org/path/to/api</url>
    <method>GET|POST|PUT|DELETE|HEAD|CONNECT|OPTIONS|TRACE|PATCH</method>
    <headers>
        <header>
            <key></key>
            <value></value>
        </header>
        <header> </header>
    </headers>
    <authorization>
        <type>Basic</type>
        <data>
            <!-- Basic -->
            <username> </username>
            <password> </password>
        </data>
    </authorization>
    <body> </body>
</request>
```
La respuesta recibida en json es devuelta en XML.

### SET
Es un adaptador de solicitud. Aporta memoria al proceso. No requiere configuración.
```xml
<adapter>
    <type>set</type>
</adapter>
```
Este es el formato de mensaje esperado.
```xml
<query>
    <action> {create | delete} </action>
    <value> ValorAAgregarOEliminar </value>
</query>
```
El adaptador responderá con verdadero si no ha sido necesaria ninguna acción en la memoria, es decir; si la operación es de creación pero el valor ya existía, o de eliminación y el valor no existía.
```xml
<response> {true | false} </response>
```

### Clock
Es un adaptador de entrada. Introduce mensajes periodicamente en intervalos de milisegundos configurables.
```xml
<adapter>
    <type>web_api</type>
    <config>1000</config>
</adapter>
```
Los mensajes que envia tienen el siguiente formato.
```xml
<clock>
    <epoch />
    <epoch-millis />
    <iso />
</clock>
```

### Console (Testing)
Es un adaptador de entrada. Introduce los mensajes que recibe por consola. No requiere configuración.
```xml
<adapter>
    <type>console</type>
</adapter>
```

### Screen (Testing)
Es un adaptador de salida. Muestra los mensajes que le llegan por pantalla. No requiere configuración.
```xml
<adapter>
    <type>screen</type>
</adapter>
```

### Stub Input (Debugging)
Es un adaptador de entrada. Introduce mensajes manualmente. No requiere configuración.
```xml
<adapter>
    <type>stub_input</type>
</adapter>
```

### Stub Output (Debugging)
Es un adaptador de salida. Muestra los mensajes que le llegan. No requiere configuración.
```xml
<adapter>
    <type>stub_output</type>
</adapter>
```

### Stub Request (Debugging) *No implementada*
Es un adaptador de solicitud. Contesta manualmente a los mensajes que le llegan. No requiere configuración.
```xml
<adapter>
    <type>stub_request</type>
</adapter>
```

### Wordpress Watcher
Es un adaptador de entrada. Vigila las publicaciones de un sitio Wordpress e inserta un mensaje por cada publicación nueva que detecta.
 ```xml
<adapter>
    <type>wordpress_watcher</type>
    <config>
        <url> </url>
        <username> </username>
        <password> </password>
    </config>
</adapter>
```
Envia un mensaje por cada post nuevo con el siguiente formato.
 ```xml
<post>
    <id />
    <title />
    <content />
    <media />
</post>
```

## Ejemplos
La siguiente es una lista de los ficheros de configuración existentes.

- Cafe
- Ejercicio 1
- Ejercicio 2
- Ejercicio 3
- Ejercicio 4










