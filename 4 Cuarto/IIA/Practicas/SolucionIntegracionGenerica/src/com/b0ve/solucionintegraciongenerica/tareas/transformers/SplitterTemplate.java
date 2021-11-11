package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.FragmentInfo;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import org.w3c.dom.Document;

public abstract class SplitterTemplate extends Tarea {
    public SplitterTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        while (!entrada.empty()) {
            Mensaje mensaje = entrada.retrive();
            //if(mensaje.getSequenceSize() != 0) throw new ExecutionException("No se puede fragmentar un fragmento de mensaje");
            Document[] parts = split(mensaje);
            long fragmentID = FragmentInfo.uniqueID();
            for (int i = 0; i < parts.length; i++) {
                Mensaje parte = new Mensaje(parts[i]);
                parte.addFragmentInfo(mensaje.getFragmentInfoStack());
                parte.addFragmentInfo(new FragmentInfo(fragmentID, parts.length));
                salida.push(parte);
            }
        }
    }

    protected abstract Document[] split(Mensaje mensaje);

}
