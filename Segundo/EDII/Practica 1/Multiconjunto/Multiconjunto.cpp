#include "Multiconjunto.h"

template<typename T> Multiconjunto<T>::Multiconjunto(){
    num = 0;
}

template<typename T> bool Multiconjunto<T>::esVacio() const{
    return num == 0;
}

template<typename T> int Multiconjunto<T>::cardinalidad() const{
    return num;
}
template<typename T> void Multiconjunto<T>::anade(const T& objeto){
    if(num < 100)
        c[num++] = objeto;
}
template<typename T> void Multiconjunto<T>::elimina(const T& objeto){
    /*
    //Solucion de orden 2n (que es igual que n)
    bool coincidencias[num];
    for(int i = 0; i < num; i++)
        coincidencias = c[i] == objeto;

    int j = 0;
    for(int i = 0; i < num; i++)
        if(!coincidencias[i])
            c[j++] = c[i];

    num = j;
    */

    //Solucion orden n
    int i = 0;
    while(i < num){
        if(c[i] == objeto){
            c[i] = c[num-1];
            num--;
        }else i++;
    }
}
template<typename T> bool Multiconjunto<T>::pertenece(const T& objeto) const{
    bool encontrado = false;

    int i = 0;
    while(i < num && !encontrado)
        if(c[i] == objeto)
            encontrado = true;
        else i++;

    return encontrado;
}

template class Multiconjunto<int>;
template class Multiconjunto<char>;
template class Multiconjunto<Persona>;
