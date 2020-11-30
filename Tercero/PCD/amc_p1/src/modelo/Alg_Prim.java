package modelo;

public class Alg_Prim {
	
	public static String[] Prim(Grafo grafo) {
        int i, j, k, x, y;
        boolean[] Reached = new boolean[grafo.num_nodes];
        String[] predNode = new String[grafo.num_nodes];
        Reached[0] = true;
        for ( k = 1; k < grafo.num_nodes; k++ )
        {
            Reached[k] = false;
        }
        predNode[0] = "0";
        Double mincost = 0d;
        for (k = 1; k < grafo.num_nodes; k++)
        {
            x = y = 0;
            for ( i = 0; i < grafo.num_nodes; i++ )
                for ( j = 0; j < grafo.num_nodes; j++ )
                {
                    if ( Reached[i] && !Reached[j] &&
                    		grafo.LinkCost[i][j] < grafo.LinkCost[x][y] )
                    {
                        x = i;
                        y = j;
                    }
                }
        	mincost+=grafo.LinkCost[x][y];
            predNode[y] = ""+x;
            Reached[y] = true;
        }
        predNode[0] += "#"+mincost;
        return predNode;
    }
	
}
