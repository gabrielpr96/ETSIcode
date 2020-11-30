package modelo;

public class Alg_Kruskal {
	
	public static String[] Kruskal(Grafo grafo) {
		int[] parent = new int[grafo.num_nodes];
		double mincost = 0d;
		String[] predNode = new String[grafo.num_nodes];
	    for (int i = 0; i < grafo.num_nodes; i++) 
	        parent[i] = i;
	    int edge_count = 0; 
	    while (edge_count < grafo.num_nodes - 1) 
	    { 
	        double min = Double.POSITIVE_INFINITY;
	        int i = 0, j = 0,a = -1, b = -1; 
	        for (i = 0; i < grafo.num_nodes; i++) 
	        { 
	            for (j = 0; j < grafo.num_nodes; j++)
	            { 
	                if (find(i, parent) != find(j, parent) && grafo.LinkCost[i][j] < min)  
	                { 
	                    min = grafo.LinkCost[i][j]; 
	                    a = i;
	                    b = j;
	                } 
	            }
	        }
	        union1(a, b, parent);
	        predNode[edge_count] = a+"#"+b;
        	edge_count++;
        	mincost += min;
	    }
	    predNode[0] += "#"+mincost;
		return predNode;
	}
	static int find(int i, int[] parent){ 
	    while (parent[i] != i) 
	        i = parent[i]; 
	    return i; 
	}
	static void union1(int i, int j, int[] parent) { 
		int a = find(i, parent); 
		int b = find(j, parent); 
	    parent[a] = b;
	} 
	
}
