#include <iostream>
#include <fstream>
#include <cstring>
#include <cstdio>

using namespace std;



class Lista{
    float elementos[20];
    int n;
public:
    Lista(){
        n = 0;
    }
    ~Lista(){}
    int insertar(float elto, int index){
        index--;
        if(index > n) index = n;
        for(int i = n; i >= index; i--)
            elementos[i+1] = elementos[i];
        elementos[index] = elto;
        n++;
    }
    int longitud(){
        return n;
    }
    int posicion(float value){
        int i = 0;
        while(elementos[i] != value) i++;
        return i==n?-1:i;
    }
    float observar(int index){
        return elementos[index-1];
    }
    bool esvacia(){
        return n==0;
    }
    int modificar(int index, float value){
        elementos[index] = value;
    }
    int eliminar(int index){
        for(int i = index-1; i < n-1; i++){
            elementos[i] = elementos[i+1];
        }
        n--;
    }
};
class Pila{
    float elementos[20];
    int n;
public:
    Pila(){
        n = 0;
    }
    ~Pila(){}
    int longitud(){
        return n;
    }
    float cima(){
        return elementos[n-1];
    }
    void desapilar(){
        n--;
    }
    void apilar(float value){
        elementos[n] = value;
        n++;
    }
    bool esvacia(){
        return n==0;
    }
};
class Cola{
    float elementos[20];
    int n;
public:
    Cola(){
        n = 0;
    }
    ~Cola(){}
    void encolar(float e){
        elementos[n] = e;
        n++;
    }
    void desencolar(){
        for(int i = 0; i < n-1; i++){
            elementos[i] = elementos[i+1];
        }
        n--;
    }
    bool esvacia(){
        return n == 0;
    }
    float primero(){
        return elementos[0];
    }
    int longitud(){
        return n;
    }
};

void crearArchivo(){
    float tmp;
    ofstream file("datos.dat", ios::binary);
    tmp = 3.5;  file.write((char *) &tmp, sizeof(float));
    tmp = 2.3;  file.write((char *) &tmp, sizeof(float));
    tmp = 4;    file.write((char *) &tmp, sizeof(float));
    tmp = 5;    file.write((char *) &tmp, sizeof(float));
    tmp = 6;    file.write((char *) &tmp, sizeof(float));
    tmp = 9.3;  file.write((char *) &tmp, sizeof(float));
    tmp = 3.4;  file.write((char *) &tmp, sizeof(float));
    tmp = 4.1;  file.write((char *) &tmp, sizeof(float));
    tmp = 7.8;  file.write((char *) &tmp, sizeof(float));
    tmp = 8.2;  file.write((char *) &tmp, sizeof(float));
    file.close();
}
void cargarTADs(Pila &p, Cola &c){
    p.apilar(7);
    p.apilar(2);
    p.apilar(3);
    p.apilar(1);
    c.encolar(4);
    c.encolar(3);
    c.encolar(3);
    c.encolar(2);
    c.encolar(4.1);
    c.encolar(5.3);
    c.encolar(1);
    c.encolar(5);
    c.encolar(8);
    c.encolar(14);
}

void mostrar(const char *texto, Lista &l){
    cout << "\n" << texto << ": ";
    for(int i = 1; i <= l.longitud(); i++)
        cout << l.observar(i) << "  ";
    cout << "\n";
}
void mostrar(const char *texto, Pila &p){
    cout << "\n" << texto << ": ";
    Pila pTMP;
    while(!p.esvacia()){
        cout << p.cima() << "  ";
        pTMP.apilar(p.cima());
        p.desapilar();
    }
    while(!pTMP.esvacia()){
        p.apilar(pTMP.cima());
        pTMP.desapilar();
    }
    cout << "\n";
}
void mostrar(const char *texto, Cola &c){
    cout << "\n" << texto << ": ";
    for(int i = 0; i < c.longitud(); i++){
        cout << c.primero() << "  ";
        c.encolar(c.primero());
        c.desencolar();
    }
    cout << "\n";
}

Lista listasumax(Pila &p,Cola &c){
    ///Poner aqui el codigo

    Lista l;

    ifstream file("datos.dat", ios::binary);
    if(file.fail()) return l;

    Pila pTMP;
    float SumaX = 0, tmp;

    while(!p.esvacia()){
        pTMP.apilar(p.cima());
        file.seekg((p.cima()-1)*sizeof(float));
        file.read((char *) &tmp, sizeof(float));
        SumaX += tmp;
        p.desapilar();
    }
    while(!pTMP.esvacia()){
        p.apilar(pTMP.cima());
        pTMP.desapilar();
    }
    cout << "SumaX: " << SumaX << "\n";

    file.close();

    int sumaTMP = 0, nAgregados = 0;
    bool mayor = false;
    for(int i = 0; i < c.longitud(); i++){
        if(!mayor){
            sumaTMP += c.primero();
            if(sumaTMP > SumaX)
                mayor = true;
        }
        if(mayor){
            l.insertar(c.primero(), ++nAgregados);
        }
        c.encolar(c.primero());
        c.desencolar();
    }

    return l;

    ///Poner aqui el codigo
}

int main(){
    Pila p;
    Cola c;

    cargarTADs(p, c);
    crearArchivo();

    mostrar("Pila inicial", p);
    mostrar("Cola inicial", c);

    cout << "\n\nListaSumaX\n";
    Lista l = listasumax(p, c);

    mostrar("\nPila final", p);
    mostrar("Cola inicial", c);
    cout << "\nDeberian ser iguales a las iniciales.\n";
    mostrar("\nLista resultante", l);


    return 0;
}
