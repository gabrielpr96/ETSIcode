#ifndef PERSONA_H_INCLUDED
#define PERSONA_H_INCLUDED

#include <string>
using namespace std;

class Persona{
    string nombre;
    int edad;
public:
    Persona(const string& n = "", int e = 0);
    const string& getNombre() const;
    int getEdad() const;
    void setNombre(const string& n);
    void setEdad(int e);
    bool operator==(const Persona& p) const;
};

#endif // PERSONA_H_INCLUDED
