#include <iostream>
#include <sstream>
#include <cstring>
#include <cstdlib>

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

    ~Profesor(){
        delete [] nombre;
    }

    const char *getNombre() const { return nombre; }
    float nomina() const { return sueldo_base; }
    static float getSueldoBase() { return sueldo_base; }
    static void setSueldoBase(float su) { sueldo_base = su; }
    void ver() const { cout << nombre << ", sueldo base: " << sueldo_base; }
};

float Profesor::sueldo_base = -1;

class Departamento {
private:
    Profesor **lista;
    int nmax; //capacidad
    int n; //num elementos
    int busca(char *nombre) const; //devuelve la posición donde esta o -1 si no existe
public:
    Departamento(); // constructor
    ~Departamento(); // destructor
    void agregar();
    void ver() const;
};
