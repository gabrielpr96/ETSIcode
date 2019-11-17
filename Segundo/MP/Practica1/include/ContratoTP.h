#ifndef CONTRATOTP_H
#define CONTRATOTP_H
#include "contrato.h"

#define PRECIO_EXCESO 0.15

class ContratoTP : public Contrato{
private:
    static int limiteMinutos;
    static float precio;

public:
    ContratoTP(long int dni, Fecha f, int minutosHablados);
    ContratoTP(const ContratoTP &c);
    ~ContratoTP();

    static int getLimiteMinutos();
    static float getPrecio();
    static void setLimiteMinutos(int l);
    static void setPrecio(float p);
    static void setTarifaPlana(int limiteMinutos, float precio);

    void ver() const;
    float factura() const;

};

std::ostream& operator<<(std::ostream& s, const ContratoTP &o);

#endif // CONTRATOTP_H
