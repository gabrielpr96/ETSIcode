package practica2;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Size;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizV8Engine;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Factory.to;
import guru.nidi.graphviz.model.MutableGraph;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.Timer;

class CanvasImagen extends Canvas {

    private Image i;

    public void dibujar(Image i) {
        this.i = i;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (i != null) {
            g.drawImage(i, 0, 0, this);
        }
    }
}

public class AutomataDrawer {

    private static JFrame principal, secundaria;
    private static CanvasImagen canvas;
    private static MutableGraph graph;

    public static void setVentanaPrincipal(JFrame ventana) {
        principal = ventana;

        //Graphviz.useEngine(new GraphvizCmdLineEngine("dot.exe"));
        Graphviz.useEngine(new GraphvizV8Engine());

        secundaria = new JFrame();
        secundaria.setUndecorated(true);
        canvas = new CanvasImagen();

        secundaria.add(canvas);

        secundaria.setLocation((int) (principal.getLocation().getX() + principal.getSize().getWidth()), (int) principal.getLocation().getY());
        principal.addComponentListener(new ComponentResizeEndListener() {
            @Override
            public void componentMoved(ComponentEvent e) {
                secundaria.setLocation((int) (principal.getLocation().getX() + principal.getSize().getWidth()), (int) principal.getLocation().getY());
            }

            @Override
            public void resizeTimedOut() {
                setGraph(graph);
            }

            @Override
            public void resizeStarted() {
                secundaria.setLocation((int) (principal.getLocation().getX() + principal.getSize().getWidth()), (int) principal.getLocation().getY());
            }
        });
    }

    public static void showGraph() {
        secundaria.setVisible(true);
    }

    public static void hideGraph() {
        secundaria.setVisible(false);
    }

    public static void setGraph(MutableGraph g) {
        if(g == null) return;
        graph = g;
        g.graphAttrs().add(Color.rgb("E5D0F5").background());
        BufferedImage bi = Graphviz.fromGraph(g).height(principal.getHeight()).render(Format.PNG).toImage();
        secundaria.setVisible(false);
        canvas.setSize(bi.getWidth(secundaria), bi.getHeight(secundaria));
        canvas.dibujar(bi);
        secundaria.pack();
        secundaria.setVisible(true);
    }

    public static MutableGraph createGraphAFD(AutomataDeterminista automata, String[] transicionSeleccionada, String estadoActual) {
        MutableGraph g = mutGraph("").setDirected(true);
        for (String estado : automata.getEstados()) {
            if (estado.equals(estadoActual)) {
                if (automata.getEstadosFinales().contains(estado)) {
                    g.add(mutNode(estado).add(Shape.DOUBLE_CIRCLE).add(Color.BLUE));
                } else {
                    g.add(mutNode(estado).add(Shape.CIRCLE).add(Color.BLUE));
                }
            } else {
                if (automata.getEstadosFinales().contains(estado)) {
                    g.add(mutNode(estado).add(Shape.DOUBLE_CIRCLE));
                } else {
                    g.add(mutNode(estado).add(Shape.CIRCLE));
                }
            }

        }
        g.add(mutNode("").add(Shape.NONE, Size.std().size(0, 0), Label.of("")).addLink(to(mutNode(automata.getEstadoInicial()))));
        for (Map.Entry<String, String> transicion : automata.getTransiciones().entrySet()) {
            String[] key = transicion.getKey().split("-");
            String value = transicion.getValue();
            if (transicionSeleccionada != null && key[0].equals(transicionSeleccionada[0]) && key[1].equals(transicionSeleccionada[1]) && value.equals(transicionSeleccionada[2])) {
                g.add(mutNode(key[0]).addLink(to(mutNode(value)).add(Label.of(key[1])).add(Color.RED).add(Color.RED.font())));
            } else {
                g.add(mutNode(key[0]).addLink(to(mutNode(value)).add(Label.of(key[1]))));
            }
        }
        return g;
    }

    public static MutableGraph createGraphAFND(AutomataNoDeterminista automata, String[] transicionSeleccionada, Set<String> estadoActual, Set<String> antiguosEstados) {
        MutableGraph g = mutGraph("").setDirected(true);
        for (String estado : automata.getEstados()) {
            if (estadoActual.contains(estado)) {
                if (automata.getEstadosFinales().contains(estado)) {
                    g.add(mutNode(estado).add(Shape.DOUBLE_CIRCLE).add(Color.BLUE));
                } else {
                    g.add(mutNode(estado).add(Shape.CIRCLE).add(Color.BLUE));
                }
            } else if (antiguosEstados != null && antiguosEstados.contains(estado)) {
                if (automata.getEstadosFinales().contains(estado)) {
                    g.add(mutNode(estado).add(Shape.DOUBLE_CIRCLE).add(Color.GREEN));
                } else {
                    g.add(mutNode(estado).add(Shape.CIRCLE).add(Color.GREEN));
                }
            } else {
                if (automata.getEstadosFinales().contains(estado)) {
                    g.add(mutNode(estado).add(Shape.DOUBLE_CIRCLE));
                } else {
                    g.add(mutNode(estado).add(Shape.CIRCLE));
                }
            }
        }
        for (String estado : automata.getEstadosIniciales()) {
            g.add(mutNode(Double.toHexString(Math.random())).add(Shape.NONE, Size.std().size(0, 0), Label.of("")).addLink(to(mutNode(estado))));
        }
        for (Map.Entry<String, String[]> transicion : automata.getTransiciones().entrySet()) {
            String[] key = transicion.getKey().split("-");
            String[] values = transicion.getValue();
            for (String value : values) {
                if (transicionSeleccionada != null && key[0].equals(transicionSeleccionada[0]) && ((key.length < 2 && transicionSeleccionada[1] == null) || (key.length > 1 && key[1].equals(transicionSeleccionada[1]))) && value.equals(transicionSeleccionada[2])) {
                    g.add(mutNode(key[0]).addLink(to(mutNode(value)).add(Label.of(key.length > 1 ? key[1] : "λ")).add(Color.RED).add(Color.RED.font())));
                } else {
                    g.add(mutNode(key[0]).addLink(to(mutNode(value)).add(Label.of(key.length > 1 ? key[1] : "λ"))));
                }
            }

        }

        return g;
    }
}

abstract class ComponentResizeEndListener extends ComponentAdapter implements ActionListener {

    private final Timer timer;

    public ComponentResizeEndListener() {
        this(200);
    }

    public ComponentResizeEndListener(int delayMS) {
        timer = new Timer(delayMS, this);
        timer.setRepeats(false);
        timer.setCoalesce(false);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        timer.restart();
        resizeStarted();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resizeTimedOut();
    }

    public abstract void resizeTimedOut();

    public abstract void resizeStarted();
}
