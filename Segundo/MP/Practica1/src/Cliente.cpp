#include <string.h>
#include "Cliente.h"

Cliente::Cliente(long int dni, char *nombre, Fecha f):fechaAlta(f){
    this->dni = dni;
    this->nombre = new char[strlen(nombre)+1];
    strcpy(this->nombre, nombre);
}

Cliente::~Cliente(){
    delete [] this->nombre;
}

void Cliente::setNombre(char *nombre){
    delete this->nombre;
    this->nombre = new char[strlen(nombre)+1];
    strcpy(this->nombre, nombre);
}
void Cliente::setFecha(Fecha f){
    fechaAlta = f;
}

Cliente& Cliente::operator=(const Cliente& c) {
  if (this != &c) { //No se permite copiar a si mismo
    this->dni=c.dni;
    delete [] this->nombre;
    this->nombre=new char[strlen(c.nombre)+1];
    strcpy(this->nombre, c.nombre);
    this->fechaAlta=c.fechaAlta;
  }
  return *this;
}

bool Cliente::operator==(const Cliente &c) const{
    return this->dni == c.dni;
}

std::ostream& operator<<(std::ostream& s, const Cliente &o) {
    Fecha f = o.getFecha();
    s << o.getNombre() << " (" <<  o.getDni() << " - " << (f.getDia()<10?"0":"") << f.getDia() << " ";
    switch(f.getMes()){
        case  1: s << "ene"; break;
        case  2: s << "feb"; break;
        case  3: s << "mar"; break;
        case  4: s << "abr"; break;
        case  5: s << "may"; break;
        case  6: s << "jun"; break;
        case  7: s << "jul"; break;
        case  8: s << "ago"; break;
        case  9: s << "sep"; break;
        case 10: s << "oct"; break;
        case 11: s << "nob"; break;
        case 12: s << "dic"; break;
    }
    s << " " << f.getAnio() << ")";
    return s;
}
