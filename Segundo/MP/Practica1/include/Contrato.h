#ifndef CONTRATO_H
#define CONTRATO_H
#include "fecha.h"

class Contrato{
    static unsigned int contador;

protected:
    int idContrato;
    long int dniContrato;
    Fecha fechaContrato;

    int minutosHablados;

public:
    Contrato(long int dni, Fecha f);
    ~Contrato();

    long int getDniContrato() const {return this->dniContrato;};
    Fecha getFechaContrato() const {return this->fechaContrato;};
    int getIdContrato() const {return this->idContrato;};
    int getMinutosHablados() const {return minutosHablados;};
    void setDniContrato(long int dni){this->dniContrato = dni;};
    void setFechaContrato(Fecha f){this->fechaContrato = f;};
    void setMinutosHablados(int minutosHablados){this->minutosHablados = minutosHablados;};

    void ver() const;
};

std::ostream& operator<<(std::ostream& s, const Contrato &o);

#endif // CONTRATO_H
