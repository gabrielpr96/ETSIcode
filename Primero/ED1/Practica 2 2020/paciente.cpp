#include "paciente.h"

void copiarPaciente(Paciente &destino,Paciente &origen){
    ///TODO: modificar este copiar paciente
    destino.n = origen.n;
}

bool compararPaciente(Paciente &p1,Paciente &p2){
    ///TODO: Comparar paciente
    return p1.n == p2.n;
}
