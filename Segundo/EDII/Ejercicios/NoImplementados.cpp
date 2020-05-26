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
