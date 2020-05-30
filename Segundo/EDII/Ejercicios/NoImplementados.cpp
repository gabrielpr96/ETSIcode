///Arboles generales

//Tema 2 - 9. Contar el número de nodos
template <typename T>
int numeroNodos(const ArbolGeneral<T>& a) {
    return numeroNodos(a, a.getRaiz());
}
template <typename T>
int numeroNodos(const ArbolGeneral<T>& a, const typename ArbolGeneral<T>::Iterador& r) {
    if(r.arbolVacio()){
        return 0;
    }else{
		int num = 1;
		vector<const typename ArbolGeneral<T>::Iterador&> nodos = a.hijos(r);
		for(int i = 0; i < nodos.size(); i++)
			num += numeroNodos(a, nodos[i]);
        return num;
    }
}

//Tema 2 - 10. Grado de un arbol
template <typename T>
int grado(const ArbolGeneral<T>& a) {
    return grado(a, a.getRaiz());
}
template <typename T>
int grado(const ArbolGeneral<T>& a, const typename ArbolGeneral<T>::Iterador& r) {
    if(r.arbolVacio()){
        return 0;
    }else{
		vector<const typename ArbolGeneral<T>::Iterador&> nodos = a.hijos(r);
		int gradoMax = nodos.size();
		for(int i = 0; i < nodos.size(); i++){
			int gradoTmp = grado(a, nodos[i]);
			if(gradoTmp > gradoMax)
				gradoMax = gradoTmp;
		}
        return gradoMax;
    }
}

//Tema 2 - 11. Recorrido en anchura
template <typename T>
void anchura(const ArbolGeneral<T>& a) {
    if (!a.esVacio()) {
        queue<typename ArbolGeneral<T>::Iterador> c;
        typename ArbolGeneral<T>::Iterador ic = a.getRaiz();
        c.push(ic);
        while (!c.empty()) {
             ic = c.front();
             c.pop();
             cout << ic.observar() << " ";
			 
			 vector<const typename ArbolGeneral<T>::Iterador&> nodos = a.hijos(r);
             for(int i = 0; i < nodos.size(); i++)
				if(!nodos[i].arbolVacio())
					c.push(nodos[i]);
        }
    }
}

//Tema 2 - 12. Numero de ojas
template <typename T>
int numeroHojas(const ArbolGeneral<T>& a) {
    return numeroHojas(a, a.getRaiz());
}
template <typename T>
int numeroHojas(const ArbolGeneral<T>& a, const typename ArbolGeneral<T>::Iterador& r) {
    if(r.arbolVacio()){
        return 0;
    }else{
		vector<const typename ArbolGeneral<T>::Iterador&> nodos = a.hijos(r);
		if(nodos.size() == 0){
			return 1;
		}else{
			int num = 0;
			for(int i = 0; i < nodos.size(); i++)
				num += numeroHojas(a, nodos[i]);
			return num;
		}
    }
}

//Tema 2 - 13. Misma estructura
template <typename T>
bool mismaEstructura(const ArbolGeneral<T>& a, const ArbolGeneral<T>& b) {
    return mismaEstructura(a, a.getRaiz(), b, b.getRaiz());
}
template <typename T>
bool mismaEstructura(const ArbolGeneral<T>& a, const typename ArbolGeneral<T>::Iterador& ra, const ArbolGeneral<T>& b, const typename ArbolGeneral<T>::Iterador& rb) {
    if(ra.arbolVacio() && rb.arbolVacio()){
        return true;
    }else{
		vector<const typename ArbolGeneral<T>::Iterador&> nodosA = a.hijos(ra);
		vector<const typename ArbolGeneral<T>::Iterador&> nodosB = b.hijos(rb);
		if(nodosA.size() != nodosB.size()) return false;
		bool bueno = true;
		int i = 0;
		while(i < nodosA.size() && bueno){
			bueno = mismaEstructura(a, nodosA[i], b, nodosB[i]);
			i++;
		}
		return bueno;
	}
}


//Tema 5 - 2. Adyacentes implementados con una matriz de adyacencia
template <typename U>
Conjunto<int> adyacentesMatriz(const U[][] matriz, int nVertices, int vertice){
    Conjunto<int> adyacentes;
    for(int i = 0; i < nVertices; i++)
		if(matriz[i][vertice] > 0)
			adyacentes.anadir(i);
	return adyacentes;
}


//Tema 5 - 5. Grado de entrada y de salida de un vertice con listas de adyacencia
int gradoSalida(const Arista **lista, int vertice){
	int contador = 0;
    Arista tmp = lista[vertice];
	while(tmp->siguiente != NULL){
		contador++;
		tmp = tmp->siguiente;
	}
	return contador;
}
int gradoEntrada(const Arista **lista, int nVertices, int vertice){
	int contador = 0;
    Arista tmp;
	for(int i = 0; i < nVertices; i++){
		tmp = lista[i];
		while(tmp->siguiente != NULL){
			if(tmp->destino == vertice)
				contador++;
			tmp = tmp->siguiente;
		}
	}
	return contador;
}


//Tema 5 - 6. Recorrido en anchura
FIFO: 		D
Visitados:	

FIFO: 		B
Visitados:	D

FIFO: 		A	E	F
Visitados:	D	B

FIFO: 		E	F	C
Visitados:	D	B	A

FIFO: 		F	C	
Visitados:	D	B	A	E

FIFO: 		C	
Visitados:	D	B	A	E	F

FIFO: 		
Visitados:	D	B	A	E	F	C

//Tema 5 - 6. Recorrido en profundidad
LIFO: 		D
Visitados:	

LIFO: 		B
Visitados:	D

LIFO: 		F	E	A
Visitados:	D	B

LIFO: 		E	A
Visitados:	D	B	F

LIFO: 		A
Visitados:	D	B	F	E

LIFO: 		C
Visitados:	D	B	F	E	A

LIFO: 		
Visitados:	D	B	F	E	A	C

//Tema 5 - 7. Dijkstra partiendo de V1
Iteracion	| Tratados		| Por tratar	| D[2] | D[3] | D[4] | D[5] | D[6] | D[7] | D[8] | A[1] | A[2] | A[3] | A[4] | A[5] | A[6] | A[7] | A[8] |
Inicial		|				|1,2,3,4,5,6,7,8|   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |
1			|1				|2,3,4,5,6,7,8	|   2  |   0  |   0  |   0  |   0  |   3  |   0  |   0  |   1  |   0  |   0  |   0  |   0  |   1  |   0  |
2			|1,2			|3,4,5,6,7,8	|   2  |   7  |   0  |   0  |   0  |   3  |   5  |   0  |   1  |   2  |   0  |   0  |   0  |   1  |   2  |
3			|1,2,7			|3,4,5,6,8		|   2  |   7  |   0  |   6  |  10  |   3  |   4  |   0  |   1  |   2  |   0  |   7  |   7  |   1  |   7  |
4			|1,2,7,8		|3,4,5,6		|   2  |   5  |   0  |   6  |  10  |   3  |   4  |   0  |   1  |   8  |   0  |   7  |   7  |   1  |   7  |
5			|1,2,7,8,3		|4,5,6			|   2  |   5  |   9  |   6  |  10  |   3  |   4  |   0  |   1  |   8  |   3  |   7  |   7  |   1  |   7  |
6			|1,2,7,8,3,5	|4,6			|   2  |   5  |   7  |   6  |   8  |   3  |   4  |   0  |   1  |   8  |   5  |   7  |   5  |   1  |   7  |
7			|1,2,7,8,3,5	|4,6			|   2  |   5  |   7  |   6  |   8  |   3  |   4  |   0  |   1  |   8  |   5  |   7  |   5  |   1  |   7  |
8			|1,2,7,8,3,5,4	|6				|   2  |   5  |   7  |   6  |   8  |   3  |   4  |   0  |   1  |   8  |   5  |   7  |   5  |   1  |   7  |
9			|1,2,7,8,3,5,4,6|				|   2  |   5  |   7  |   6  |   8  |   3  |   4  |   0  |   1  |   8  |   5  |   7  |   5  |   1  |   7  |

V1 a V2 : V1 -> V2
V1 a V3 : V1 -> V7 -> V8 -> V3
V1 a V4 : V1 -> V7 -> V5 -> V4
V1 a V5 : V1 -> V7 -> V5
V1 a V6 : V1 -> V7 -> V5 -> V6
V1 a V7 : V1 -> V7
V1 a V8 : V1 -> V7 -> V8

//Tema 5 - 11. Dijkstra de V1 a V6
Iteracion	| Tratados	|Por tratar	| D[2] | D[3] | D[4] | D[5] | D[6] | A[1] | A[2] | A[3] | A[4] | A[5] | A[6]
Inicial		|			|1,2,3,4,5,6|   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0
1			|1			|2,3,4,5,6	|  20  |  75  |  60  |   0  |   0  |   0  |   1  |   1  |   1  |   0  |   0
2			|1,2		|3,4,5,6	|  20  |  22  |  50  |  45  |   0  |   0  |   1  |   2  |   2  |   2  |   0
3			|1,2,3		|4,5,6		|  20  |  22  |  50  |  45  |   0  |   0  |   1  |   2  |   2  |   2  |   0
4			|1,2,3,5	|4,6		|  20  |  22  |  48  |  45  |  70  |   0  |   1  |   2  |   5  |   2  |   5
5			|1,2,3,5,4	|6			|  20  |  22  |  48  |  45  |  68  |   0  |   1  |   2  |   5  |   2  |   4
6			|1,2,3,5,4,6|			|  20  |  22  |  48  |  45  |  65  |   0  |   1  |   2  |   5  |   2  |   4

V1 a V6: V1 -> V2 -> V5 -> V4 -> V6

Iteracion	| Tratados	|Por tratar	| D[1] | D[3] | D[4] | D[5] | D[6] | A[1] | A[2] | A[3] | A[4] | A[5] | A[6]
Inicial		|			|1,2,3,4,5,6|   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0
Inicial		|2			|1,3,4,5,6	|   0  |   2  |  30  |  25  |   0  |   0  |   0  |   2  |   2  |   2  |   0
Inicial		|2,3		|1,4,5,6	|   0  |   2  |  30  |  25  |   0  |   0  |   0  |   2  |   2  |   2  |   0
Inicial		|2,3,5		|1,4,6		|   0  |   2  |  28  |  25  |  25  |   0  |   0  |   2  |   3  |   2  |   5
Inicial		|2,3,5,6	|1,4		|   0  |   2  |  28  |  25  |  25  |   0  |   0  |   2  |   3  |   2  |   5
Inicial		|2,3,5,6,4	|1			|   0  |   2  |  28  |  25  |  25  |   0  |   0  |   2  |   3  |   2  |   5

V2 a V6: V2 -> V5 -> V6

Iteracion	| Tratados	|Por tratar	| D[1] | D[2] | D[4] | D[5] | D[6] | A[1] | A[2] | A[3] | A[4] | A[5] | A[6]
Inicial		|			|1,2,3,4,5,6|   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0
Inicial		|3			|1,2,4,5,6	|   0  | 100  |   0  | 200  |   0  |   0  |   3  |   0  |   0  |   3  |   0
Inicial		|3,2		|1,4,5,6	|   0  | 100  | 130  | 125  |   0  |   0  |   3  |   0  |   2  |   2  |   0
Inicial		|3,2,4		|1,5,6		|   0  | 100  | 130  | 125  | 150  |   0  |   3  |   0  |   2  |   2  |   4
Inicial		|3,2,4,6	|1,5		|   0  | 100  | 130  | 125  | 150  |   0  |   3  |   0  |   2  |   2  |   4
Inicial		|3,2,4,6,5	|1			|   0  | 100  | 130  | 125  | 150  |   0  |   3  |   0  |   2  |   2  |   4

V3 a V6: V3 -> V2 -> V4 -> V6

Iteracion	| Tratados	|Por tratar	| D[1] | D[2] | D[3] | D[5] | D[6] | A[1] | A[2] | A[3] | A[4] | A[5] | A[6]
Inicial		|			|1,2,3,4,5,6|   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0
Inicial		|4			|1,2,3,5,6	|   0  |   0  |   0  |   0  |  20  |   0  |   0  |   0  |   0  |   0  |   0
Inicial		|4,6		|1,2,3,5	|   0  |   0  |   0  |   0  |  20  |   0  |   0  |   0  |   0  |   0  |   0

V4 a V6: V4 -> V6

Iteracion	| Tratados	|Por tratar	| D[1] | D[2] | D[3] | D[4] | D[6] | A[1] | A[2] | A[3] | A[4] | A[5] | A[6]
Inicial		|			|1,2,3,4,5,6|   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0  |   0
Inicial		|5			|1,2,3,4,6	|   0  |   0  |  30  |   3  |  25  |   0  |   0  |   5  |   5  |   0  |   5
Inicial		|5,4		|1,2,3,6	|   0  |   0  |  30  |   3  |  23  |   0  |   0  |   5  |   5  |   0  |   4
Inicial		|5,4,6		|1,2,3		|   0  |   0  |  30  |   3  |  23  |   0  |   0  |   5  |   5  |   0  |   4
Inicial		|5,4,6,3	|1,2		|   0  | 100  |  30  |   3  |  23  |   0  |   3  |   5  |   5  |   0  |   4
Inicial		|5,4,6,3,2	|1			|   0  | 100  |  30  |   3  |  23  |   0  |   3  |   5  |   5  |   0  |   4

V5 a V6: V5 -> V4 -> V6