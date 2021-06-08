//Devuelve verdadero si existe un camino en G con exactamente la longitud proporcionada partiendo del vértice u
template <typename T, typename U>
bool caminoLongitud(const Grafo<T, U>& G, const T& u, int longitud){
    Conjunto<T> recorrido;
    
	return caminosAcotados(G, tmp.getDestino(), longitud, recorrido);
}
template <typename T>
bool caminoLongitud(const Grafo<T, U>& G, const T& u, int longitud, Conjunto<T> recorrido){
	if(longitud == 0) return true;
	
    recorrido.anadir(u);
	longitud--;

	bool encontrado = false;
    Conjunto<Arista<T, float> > adyacentes = G.aristas();
	while(!adyacentes.esVacio() || !encontrado){
        encontrado = caminosAcotados(G, adyacentes.quitar().getDestino(), longitud, recorrido);
    }

	return encontrado;
}

//Muestra cuantas aristas con un coste superior al dado salen de cada vértice 
template <typename T, typename U>
void caminoLongitud(const Grafo<T, U>& G, float coste){
	Conjunto<Vertice<T> > vertices = G.vertices();
    while(!vertices.esVacio()){
		T vertice = vertices.quitar().getObj();
		int n = 0;
		Conjunto<Arista<T, U> > aristas = G.aristas();
		while(!aristas.esVacio()){
			Arista<T, U> arista = aristas.quitar();
			if(arista.getDestino() == vertice && arista.getEtiqueta() > coste)
				n++;
		}
		cout << T << ": " << n;
	}
}
