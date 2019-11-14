#include "ContratoTP.h"

int ContratoTP::limiteMinutos = 300;
float ContratoTP::precio = 10;

ContratoTP::ContratoTP(long int dni, Fecha f, int minutosHablados) : Contrato(dni, f){
    this->minutosHablados = minutosHablados;
}
ContratoTP::ContratoTP(const ContratoTP &c) : Contrato(c.getDniContrato(), c.getFechaContrato()){
    this->minutosHablados = c.minutosHablados;
}

ContratoTP::~ContratoTP(){
}

void ContratoTP::ver() const{
    std::cout << dniContrato << " (" << idContrato << " - " << fechaContrato << ") " << minutosHablados << "m, " << limiteMinutos << "(" << precio << ")";
}
float ContratoTP::factura() const{
    return precio + (minutosHablados>limiteMinutos?(minutosHablados-limiteMinutos)*PRECIO_EXCESO:0);
}



int ContratoTP::getLimiteMinutos(){
    return limiteMinutos;
}
float ContratoTP::getPrecio(){
    return precio;
}

void ContratoTP::setLimiteMinutos(int l){
    limiteMinutos = l;
}
void ContratoTP::setPrecio(float p){
    precio = p;
}

void ContratoTP::setTarifaPlana(int limiteMinutos, float precio){
    ContratoTP::limiteMinutos = limiteMinutos;
    ContratoTP::precio = precio;
}


std::ostream& operator<<(std::ostream& s, const ContratoTP &o) {
    s << o.getDniContrato() << " (" << o.getIdContrato() << " - ";
    o.getFechaContrato().verBonita(s);
    s  << ")" << o.getMinutosHablados() << "m, " << o.getLimiteMinutos() << "(" << o.getPrecio() << ") " << " - " << o.factura() << "e"; //TODO: Intentar imprimir el euro
    return s;
}
