package ejercicio3;

import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.tareas.transformers.Aggregator;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;

public class Ejercicio3 {
//<cambios><cambio><fuente>CRM1</fuente><tipo>eliminar</tipo><datos><dni>40144663C</dni></datos></cambio></cambios>
    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso();

        AdaptadorCRM1Entrada crm1In = new AdaptadorCRM1Entrada("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\cambios");
        AdaptadorCRM2Entrada crm2In = new AdaptadorCRM2Entrada();
        AdaptadorCRM3Entrada crm3In = new AdaptadorCRM3Entrada();
        AdaptadorCRM1Salida crm1Out = new AdaptadorCRM1Salida("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\replicados");
        AdaptadorCRM2Salida crm2Out = new AdaptadorCRM2Salida();
        AdaptadorCRM3Salida crm3Out = new AdaptadorCRM3Salida(crm3In);

        Puerto pCrm1In = p.crearPuerto(crm1In);
        Puerto pCrm2In = p.crearPuerto(crm2In);
        Puerto pCrm3In = p.crearPuerto(crm3In);
        Puerto pCrm1Out = p.crearPuerto(crm1Out);
        Puerto pCrm2Out = p.crearPuerto(crm2Out);
        Puerto pCrm3Out = p.crearPuerto(crm3Out);

        Tarea splitterCrm1 = p.addTarea(new SplitterParticionado("/cambios/cambio", "/cambio/datos/dni"));
        Tarea aggregatorCrm1 = p.crearTarea(AGGREGATOR, "cambios");
        Tarea translatorCrm1 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "	<xsl:template match=\"/cambios\">\n"
                + "		<cambio>\n"
                + "			<tipo><xsl:value-of select=\"cambio[1]/tipo\"/></tipo>\n"
                + "			<fuente>CRM1</fuente>\n"
                + "			<datos>\n"
                + "				<nombre><xsl:value-of select=\"cambio[1]/datos/nombre\"/></nombre>\n"
                + "				<dni><xsl:value-of select=\"cambio[1]/datos/dni\"/></dni>\n"
                + "				<xsl:for-each select=\"cambio/datos/direccion\">\n"
                + "					<direccion><xsl:value-of select=\".\"/></direccion>\n"
                + "				</xsl:for-each>\n"
                + "			</datos>\n"
                + "		</cambio>\n"
                + "	</xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea splitterCrm2 = p.crearTarea(SPLITTER, "/cambios/cambio");
        Tarea splitterCrm3 = p.crearTarea(SPLITTER, "/cambios/cambio");
        Tarea merger = p.crearTarea(MERGER);
        Tarea replicator = p.crearTarea(REPLICATOR);
        Tarea filterCrm1 = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM1"));
        Tarea filterCrm2 = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM2"));
        Tarea filterCrm3 = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM3"));

        p.encadenar(pCrm1In, splitterCrm1);
        p.encadenar(splitterCrm1, aggregatorCrm1);
        p.encadenar(aggregatorCrm1, translatorCrm1);
        p.encadenar(pCrm2In, splitterCrm2);
        p.encadenar(pCrm3In, splitterCrm3);
        p.encadenar(translatorCrm1, merger);
        p.encadenar(splitterCrm2, merger);
        p.encadenar(splitterCrm3, merger);
        p.encadenar(merger, replicator);
        p.encadenar(replicator, filterCrm1);
        p.encadenar(replicator, filterCrm2);
        p.encadenar(replicator, filterCrm3);
        p.encadenar(filterCrm1, pCrm1Out);
        p.encadenar(filterCrm2, pCrm2Out);
        p.encadenar(filterCrm3, pCrm3Out);

        p.validar();
        p.ejecutar();
        p.esperar();
    }

    private static void test1() throws Exception {
        Mensaje m1 = newMensaje(0, 0, "<cambios>\n"
                + "	<cambio>\n"
                + "		<tipo>agregar</tipo>\n"
                + "		<fuente>CRM1</fuente>\n"
                + "		<datos>\n"
                + "			<nombre>Pepe</nombre>\n"
                + "			<dni>40144661E</dni>\n"
                + "			<direccion>Avenida Guatemala 40</direccion>\n"
                + "		</datos>\n"
                + "	</cambio>\n"
                + "	<cambio>\n"
                + "		<tipo>agregar</tipo>\n"
                + "		<fuente>CRM1</fuente>\n"
                + "		<datos>\n"
                + "			<nombre>Juan</nombre>\n"
                + "			<dni>40144662V</dni>\n"
                + "			<direccion>Raul Cimas 5</direccion>\n"
                + "			<direccion>Calle Venavente 45</direccion>\n"
                + "		</datos>\n"
                + "	</cambio>\n"
                + "	<cambio>\n"
                + "		<tipo>agregar</tipo>\n"
                + "		<fuente>CRM1</fuente>\n"
                + "		<datos>\n"
                + "			<nombre>Pepe</nombre>\n"
                + "			<dni>40144661E</dni>\n"
                + "			<direccion>Calle Plus Ultra 9</direccion>\n"
                + "		</datos>\n"
                + "	</cambio>\n"
                + "</cambios>");
        SplitterParticionado splitter = new SplitterParticionado("/cambios/cambio", "/cambio/datos/dni");
        Buffer in = new Buffer(null);
        splitter.addEntrada(in);
        Buffer mid = new Buffer(null);
        splitter.addSalida(mid);

        Aggregator aggregator = new Aggregator("lista");
        aggregator.addEntrada(mid);
        Buffer out = new Buffer(null);
        aggregator.addSalida(out);

        in.push(m1);
        //in.push(new Mensaje(m1));

        splitter.procesar();
        aggregator.procesar();

        System.out.println(out.retrive());
        System.out.println(out.retrive());
        System.out.println(out.retrive());
        System.out.println(out.retrive());
    }
}
