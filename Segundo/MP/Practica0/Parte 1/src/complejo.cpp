#include <iostream>
#include <cmath>
#include "complejo.h"

using namespace std;

complejo::complejo(int r, int i){
    real = r;
    imaginario = i;
}

complejo::~complejo(){
    //dtor
}

void complejo::set(int r, int i){
    real = r;
    imaginario = i;
}
void complejo::set(){
    cin >> real >> imaginario;
}
void complejo::ver() const {
    cout << real << (imaginario<0?"-":"+") << abs(imaginario) << "i";
}

complejo complejo::operator+(const complejo &b) const {
    return complejo(real+b.real, imaginario+b.imaginario);
}
complejo complejo::operator-() const {
    return complejo(-real, -imaginario);
}



complejo operator+(int a, const complejo &b){
    return complejo(b.getr()+a, b.geti());
}

ostream& operator<<(ostream& s, const complejo &o) {
    s << o.getr() << (o.geti()<0?"-":"+") << abs(o.geti()) << "i";
    return s;
}
