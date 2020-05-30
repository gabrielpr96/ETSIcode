#ifndef PACIENTE_H_INCLUDED
#define PACIENTE_H_INCLUDED

struct Paciente{
    int n;
};

void copiarPaciente(Paciente &destino, Paciente &origen);
bool compararPaciente(Paciente &p1, Paciente &p2);

#endif // PACIENTE_H_INCLUDED
