package ejercicio3;

import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Aggregator;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import static com.b0ve.solucionintegraciongenerica.utils.Process.TASKS.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;
import com.b0ve.solucionintegraciongenerica.utils.ProcessAsync;

public class Ejercicio3 {
//<cambios><cambio><fuente>CRM1</fuente><tipo>eliminar</tipo><datos><dni>40144663C</dni></datos></cambio></cambios>
    public static void main(String[] args) throws Exception {
        Process p = new ProcessAsync();

        AdaptadorCRM1Entrada crm1In = new AdaptadorCRM1Entrada("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\cambios");
        AdaptadorCRM2Entrada crm2In = new AdaptadorCRM2Entrada();
        AdaptadorCRM3Entrada crm3In = new AdaptadorCRM3Entrada();
        AdaptadorCRM1Salida crm1Out = new AdaptadorCRM1Salida("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\replicados");
        AdaptadorCRM2Salida crm2Out = new AdaptadorCRM2Salida();
        AdaptadorCRM3Salida crm3Out = new AdaptadorCRM3Salida(crm3In);

        Port pCrm1In = p.createPort(crm1In);
        Port pCrm2In = p.createPort(crm2In);
        Port pCrm3In = p.createPort(crm3In);
        Port pCrm1Out = p.createPort(crm1Out);
        Port pCrm2Out = p.createPort(crm2Out);
        Port pCrm3Out = p.createPort(crm3Out);

        Task splitterCrm1 = p.addTask(new SplitterParticionado("/cambios/cambio", "/cambio/datos/dni"));
        Task aggregatorCrm1 = p.createTask(AGGREGATOR, "cambios");
        Task translatorCrm1 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
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
        Task splitterCrm2 = p.createTask(SPLITTER, "/cambios/cambio");
        Task splitterCrm3 = p.createTask(SPLITTER, "/cambios/cambio");
        Task merger = p.createTask(MERGER);
        Task replicator = p.createTask(REPLICATOR);
        Task filterCrm1 = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM1"));
        Task filterCrm2 = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM2"));
        Task filterCrm3 = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM3"));

        p.connect(pCrm1In, splitterCrm1);
        p.connect(splitterCrm1, aggregatorCrm1);
        p.connect(aggregatorCrm1, translatorCrm1);
        p.connect(pCrm2In, splitterCrm2);
        p.connect(pCrm3In, splitterCrm3);
        p.connect(translatorCrm1, merger);
        p.connect(splitterCrm2, merger);
        p.connect(splitterCrm3, merger);
        p.connect(merger, replicator);
        p.connect(replicator, filterCrm1);
        p.connect(replicator, filterCrm2);
        p.connect(replicator, filterCrm3);
        p.connect(filterCrm1, pCrm1Out);
        p.connect(filterCrm2, pCrm2Out);
        p.connect(filterCrm3, pCrm3Out);

        p.validate();
        p.execute();
        p.waitToEnd();
    }

    private static void test1() throws Exception {
        Message m1 = newMessage(0, 0, "<cambios>\n"
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
