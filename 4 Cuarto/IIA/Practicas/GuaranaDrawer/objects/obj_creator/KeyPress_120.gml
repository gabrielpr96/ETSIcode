/// @description Generar codigo base XML

DerpXmlWrite_New();
DerpXmlWrite_Comment("Documento de configuración AutoSIG. Generado mediante GuaranaDrawer.");
DerpXmlWrite_OpenTag("process");

//Nombre
DerpXmlWrite_LeafElement("name", "Autogenerado");

//Configuracion global
DerpXmlWrite_OpenTag("config");
DerpXmlWrite_LeafElement("debug", "false");
DerpXmlWrite_LeafElement("async", "true");
DerpXmlWrite_CloseTag();

//Ordenar la lista de tareas por eje x
queue = ds_priority_create();
with(obj_task) ds_priority_add(other.queue, self, x);

//Tareas
DerpXmlWrite_OpenTag("tasks");
while(!ds_priority_empty(queue)){
	var task = ds_priority_delete_min(queue);
	with(task){
		DerpXmlWrite_OpenTag("task");
	
		//Nombre
		DerpXmlWrite_LeafElement("name", ds_list_find_value(text.lines, 0));
		//Tipo y preview de configuracion
		switch(image_index){
			case 0: // Correlator
				DerpXmlWrite_LeafElement("type", "correlator");
				DerpXmlWrite_Comment("TODO: Agregar expresion por la que relacionar. OPCIONAL.");
				DerpXmlWrite_LeafElement("config", "/XPath/expresion");
			break;
			case 1: // Merger
				DerpXmlWrite_LeafElement("type", "merger");
			break;
			case 2: // Filter
				DerpXmlWrite_LeafElement("type", "filter");
				DerpXmlWrite_Comment("TODO: Ajustar la condición configurable.");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_LeafElement("selector", "/XPath/expresion");
				DerpXmlWrite_LeafElement("condition", "condition_type");
				DerpXmlWrite_LeafElement("value", "FixedValue");
				DerpXmlWrite_CloseTag();
			break;
			case 3: // Distributor
				DerpXmlWrite_LeafElement("type", "distributor");
				DerpXmlWrite_Comment("TODO: Crear una condición configurable por cada caso.");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_OpenTag("case");
				DerpXmlWrite_LeafElement("selector", "/XPath/expresion");
				DerpXmlWrite_LeafElement("condition", "condition_type");
				DerpXmlWrite_LeafElement("value", "FixedValue");
				DerpXmlWrite_CloseTag();
				DerpXmlWrite_OpenTag("case");
				DerpXmlWrite_CloseTag();
				DerpXmlWrite_CloseTag();
			break;
			case 4: // Replicator
				DerpXmlWrite_LeafElement("type", "replicator");
			break;
			case 5: // Slimmer
				DerpXmlWrite_LeafElement("type", "slimmer");
				DerpXmlWrite_Comment("TODO: Agregar selectores por cada grupo de etiquetas a eliminar.");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_LeafElement("selector", "/XPath/expresion");
				DerpXmlWrite_LeafElement("selector", " ");
				DerpXmlWrite_CloseTag();
			break;
			case 6: // Context Slimmer
				DerpXmlWrite_LeafElement("type", "context_slimmer");
			break;
			case 7: // Enricher
				DerpXmlWrite_LeafElement("type", "enricher");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_Comment("TODO: Agregar documento que se combina.");
				DerpXmlWrite_CloseTag();
			break;
			case 8: // Context Enricher
				DerpXmlWrite_LeafElement("type", "context_enricher");
			break;
			case 9: // Correlation ID Setter
				DerpXmlWrite_LeafElement("type", "correlation_id_setter");
			break;
			case 10: // Translator
				DerpXmlWrite_LeafElement("type", "translator");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_Comment("TODO: Agregar XSLT para la transformación.");
				DerpXmlWrite_CloseTag();
			break;
			case 11: // Splitter
				DerpXmlWrite_LeafElement("type", "splitter");
				DerpXmlWrite_Comment("TODO: Configurar la expresión que divide los fragmentos.");
				DerpXmlWrite_LeafElement("config", "/XPath/expresion");
			break;
			case 12: // Aggregator
				DerpXmlWrite_LeafElement("type", "aggregator");
			break
			case 13: // Chopper
				DerpXmlWrite_LeafElement("type", "chopper");
				DerpXmlWrite_Comment("TODO: Configurar la expresión que divide los fragmentos.");
				DerpXmlWrite_LeafElement("config", "/XPath/expresion");
			break;
			case 14: // Assembler
				DerpXmlWrite_LeafElement("type", "assembler");
			break;
		}
	
		//Salidas
		DerpXmlWrite_OpenTag("outputs");
		for(var i = 0; i < ds_list_size(conectados); i++){
			with(ds_list_find_value(conectados, i)){
				DerpXmlWrite_LeafElement("output", ds_list_find_value(text.lines, 0));
			}
		}
		DerpXmlWrite_CloseTag();
	
		DerpXmlWrite_CloseTag();
	}
}
DerpXmlWrite_CloseTag();

//Ordenar la lista de adaptadores por eje x
ds_priority_destroy(queue);
queue = ds_priority_create();
with(obj_adapter) ds_priority_add(other.queue, self, x);

//Adaptadores
DerpXmlWrite_OpenTag("adapters");
while(!ds_priority_empty(queue)){
	var adapter = ds_priority_delete_min(queue);
	with(adapter){
		DerpXmlWrite_OpenTag("adapter");
	
		//Nombre
		DerpXmlWrite_LeafElement("name", ds_list_find_value(text.lines, 0));
		//Tipo y preview de configuracion
		switch(image_index){
			case 0: // Dir Watcher
				DerpXmlWrite_LeafElement("type", "dir_watcher");
				DerpXmlWrite_Comment("TODO: Configurar el directorio en el que vigilará el adaptador.");
				DerpXmlWrite_LeafElement("config", "/path/to/folder");
			break;
			case 1: // Dir Outputter
				DerpXmlWrite_LeafElement("type", "dir_outputter");
				DerpXmlWrite_Comment("TODO: Configurar el directorio en el que se crearan los ficheros.");
				DerpXmlWrite_LeafElement("config", "/path/to/folder");
			break;
			case 2: // MySQL
				DerpXmlWrite_LeafElement("type", "mysql");
				DerpXmlWrite_Comment("TODO: Configurar los ajustes de la conexión.");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_LeafElement("host", " ");
				DerpXmlWrite_LeafElement("port", " ");
				DerpXmlWrite_LeafElement("db", " ");
				DerpXmlWrite_LeafElement("user", " ");
				DerpXmlWrite_LeafElement("pass", " ");
				DerpXmlWrite_CloseTag();
			break;
			case 3: // MySQL Multi Query
				DerpXmlWrite_LeafElement("type", "mysql_multi_query");
				DerpXmlWrite_Comment("TODO: Configurar los ajustes de la conexión.");
				DerpXmlWrite_OpenTag("config");
				DerpXmlWrite_LeafElement("host", " ");
				DerpXmlWrite_LeafElement("port", " ");
				DerpXmlWrite_LeafElement("db", " ");
				DerpXmlWrite_LeafElement("user", " ");
				DerpXmlWrite_LeafElement("pass", " ");
				DerpXmlWrite_CloseTag();
			break;
			case 4: // Web API
				DerpXmlWrite_LeafElement("type", "web_api");
				DerpXmlWrite_Comment("TODO: Definir el endpoint (gateway) de la API.");
				DerpXmlWrite_LeafElement("config", "https://example.com/endpoint");
			break;
			case 5: // REST API
				DerpXmlWrite_LeafElement("type", "rest_api");
			break;
			case 6: // Set
				DerpXmlWrite_LeafElement("type", "set");
			break;
			case 7: // Clock
				DerpXmlWrite_LeafElement("type", "clock");
				DerpXmlWrite_Comment("TODO: Definir el intervalo entre mensajes.");
				DerpXmlWrite_LeafElement("config", "1000");
			break;
			case 8: // Console
				DerpXmlWrite_LeafElement("type", "console");
			break;
			case 9: // Screen
				DerpXmlWrite_LeafElement("type", "screen");
			break;
			case 10: // Stub Input
				DerpXmlWrite_LeafElement("type", "stub_input");
			break;
			case 11: // Stub Output
				DerpXmlWrite_LeafElement("type", "stub_output");
			break;
		}
	
		//Salidas
		DerpXmlWrite_OpenTag("outputs");
		for(var i = 0; i < ds_list_size(conectados); i++){
			with(ds_list_find_value(conectados, i)){
				DerpXmlWrite_LeafElement("output", ds_list_find_value(text.lines, 0));
			}
		}
		DerpXmlWrite_CloseTag();
	
		DerpXmlWrite_CloseTag();
	}
}
DerpXmlWrite_CloseTag();

	
DerpXmlWrite_CloseTag()
var xmlString = DerpXmlWrite_GetString()
DerpXmlWrite_UnloadString()
var f = file_text_open_write(get_save_filename("XML|*.xml", ""))
file_text_write_string(f, xmlString)
file_text_close(f)
