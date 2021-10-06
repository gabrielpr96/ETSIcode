#include "cartelera.h"
#include <iostream>

using namespace std;

Cartelera::Cartelera(): espectaculos()
{

}

void Cartelera::insertaEspectaculo(const string& e)
{
    espectaculos.insert(PEspectaculos(e, DSalas()));
}

void Cartelera::insertaSala(const string& e, const string& s, const string& c)
{
    espectaculos[e].insert(PSalas(s, c));
}

void Cartelera::eliminaEspectaculo(const string& e)
{
    espectaculos.erase(e);
}

void Cartelera::eliminaSala(const string& e, const string& s)
{
    DEspectaculos::iterator it = espectaculos.find(e);
    if (it != espectaculos.end())
        it->second.erase(s);

}

void Cartelera::listaEspectaculos()
{
    if (espectaculos.empty())
        cout << "No hay ningun espectaculo." << endl;
    else {
        cout << "Lista de espectaculos:" << endl;
        for (DEspectaculos::iterator it = espectaculos.begin(); it != espectaculos.end(); it++)
            cout << it->first << endl;
    }
}

void Cartelera::listaSalas(const string& e)
{
    DEspectaculos::iterator ite = espectaculos.find(e);
    if (ite == espectaculos.end())
        cout << "No existe el espectaculo " << e << "." << endl;
    else
        if (ite->second.empty())
            cout << "No hay ninguna sala para espectaculo " << e << "." << endl;
        else{
            cout << "Lista de salas para espectaculo " << e << ":" << endl;
            for (DSalas::iterator its = ite->second.begin(); its != ite->second.end(); its++)
            cout << its->first << " - " << its->second << endl;
        }
}

