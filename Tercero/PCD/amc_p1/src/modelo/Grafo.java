package modelo;

public class Grafo {
	double[][]	LinkCost; // graph matrix
    int	num_nodes; // number of nodes
    
    public Grafo(Punto[] puntos) {
    	int i, j;
    	 
        num_nodes = puntos.length;
 
        LinkCost = new double[num_nodes][num_nodes];
        
        // copying the weights to LinkCost matrix
        for ( i=0; i < num_nodes; i++)
        {
            for ( j=0; j < num_nodes; j++)
            {
                LinkCost[i][j] = Utils.Distancia(puntos[i], puntos[j]);
 
                if ( LinkCost[i][j] == 0 )
                    LinkCost[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }
}
