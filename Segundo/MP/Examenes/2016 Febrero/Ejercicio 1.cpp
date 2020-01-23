#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

using namespace std;

class Profesor {
    char *nombre;
    static float sueldo_base;
public:
    Profesor(const char* nom){
        nombre = new char[strlen(nom)+1];
        strcpy(nombre, nom);
        if(sueldo_base < 0){
            cout << "Introduzca el sueldo base: ";
            cin >> sueldo_base;
        }
    }
    Profesor(const Profesor& p) : Profesor(p.nombre){
    }

    virtual ~Profesor(){
        delete [] nombre;
    }

    const char *getNombre() const { return nombre; }
    virtual float nomina() const { return sueldo_base; }
    static float getSueldoBase() { return sueldo_base; }
    static void setSueldoBase(float su) { sueldo_base = su; }
    virtual void ver() const { cout << nombre << ", sueldo base: " << sueldo_base; }

    Profesor& operator=(const Profesor& p){
        if(this != &p){
            delete [] nombre;
            nombre = new char[strlen(p.nombre)+1];
            strcpy(nombre, p.nombre);
        }
        return *this;
    }
};

float Profesor::sueldo_base = -1;

class ProfesorTC : public Profesor {
    static int contador;
    int trienios;
    const int codigo;
public:
    ProfesorTC(const char* nom, int trienios) : Profesor(nom), codigo(contador++){
        this->trienios = trienios;
    }
    ProfesorTC(const ProfesorTC& p) : Profesor(p), codigo(contador++){
        this->trienios = p.trienios;

    }

    float nomina() const { return Profesor::nomina() + (trienios*40); }
    void ver() const {
        Profesor::ver();
        cout << ", trienios: " << trienios << ", codigo: " << codigo << ", sueldo total: " << nomina();
    }

    ProfesorTC& operator=(const ProfesorTC& p){
        if(this != &p){
            Profesor::operator=(p);
            this->trienios = p.trienios;
        }
    }
};

int ProfesorTC::contador = 1;

class Departamento {
private:
    Profesor **lista;
    int nmax; //capacidad
    int n; //num elementos
    int busca(char *nombre) const{//devuelve la posición donde esta o -1 si no existe
        int i = 0, pos = -1;
        while(i < n && pos == -1)
            if(strcmp(nombre, lista[i]->getNombre())==0)
                pos = i;
            else i++;
        return pos;
    }
public:
    Departamento(){
        n = 0;
        nmax = 1;
        lista = new Profesor*[nmax];
    }
    ~Departamento(){
        for(int i = 0; i < n; i++)
            delete lista[i];
        delete [] lista;
    }
    void ver() const{
        for(int i = 0; i < n; i++){
            lista[i]->ver();
            cout << "\n";
        }
    }

    void agregar(){
        char nom[50];
        cout << "Nombre del profesor: ";
        cin >> nom;
        if(busca(nom) == -1){
            char c;
            cout << "Es fijo? (s,n): ";
            cin >> c;
            if(c == 's'){
                int trienios;
                cout << "Trienios: ";
                cin >> trienios;
                lista[n++] = new ProfesorTC(nom, trienios);
            }else
                lista[n++] = new Profesor(nom);
            if(n == nmax){
                nmax *= 2;
                Profesor** aux = new Profesor*[nmax];
                for(int i = 0; i < n; i++)
                    aux[i] = lista[i];
                delete [] lista;
                lista = aux;
            }
        }else{
            cout << "El profesor ya existe. No se puede agregar.\n";
        }

    }
};


int main(){
    Profesor p1("Juanito");
    ProfesorTC ptc1("Robocop", 4);
    Profesor p2 = p1;
    ProfesorTC ptc2 = ptc1;
    Profesor p3 = ptc1;

    p1.ver(); cout << "\n";
    ptc1.ver(); cout << "\n";
    p2.ver(); cout << "\n";
    ptc2.ver(); cout << "\n";
    p3.ver(); cout << "\n\n";

    Departamento d;
    d.agregar();
    d.agregar();
    cout << "\nLista de profesores: \n";
    d.ver();

    return 0;
}
