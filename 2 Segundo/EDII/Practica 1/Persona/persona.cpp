#include "Persona.h"

Persona::Persona(const string& n, int e){
    nombre = n;
    edad = e;
}

void Persona::setEdad(int e){
    edad = e;
}
void Persona::setNombre(const string& n){
    nombre = n;
}

int Persona::getEdad() const{
    return edad;
}
const string& Persona::getNombre() const{
    return nombre;
}

bool Persona::operator==(const Persona& p) const{
    return p.nombre == nombre && p.edad == edad;
}
