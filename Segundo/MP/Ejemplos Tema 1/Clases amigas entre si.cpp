#include <iostream>
#include <cstdlib>

using namespace std;

class Julieta;

class Romeo{
    friend class Julieta;
private:
    int atributoPrivado;
    void metodoPrivado(){atributoPrivado++;}
public:
    void amarAJulieta(Julieta& j);
    void verAmor(){cout << "Amor Romeo: " << atributoPrivado << endl;}
};

class Julieta{
    friend class Romeo;
private:
    int atributoPrivado;
    void metodoPrivado(){atributoPrivado--;}
public:
    void amarARomeo(Romeo& r);
    void verAmor(){cout << "Amor Julieta: " << atributoPrivado << endl;}
};

void Romeo::amarAJulieta(Julieta& j){
    j.atributoPrivado = 5;
    j.metodoPrivado();
}

void Julieta::amarARomeo(Romeo& r){
    r.atributoPrivado = 2;
    r.metodoPrivado();
}

int main(){
    Romeo R;
    Julieta J;

    R.amarAJulieta(J);
    J.amarARomeo(R);

    R.verAmor();
    J.verAmor();

    system("PAUSE");
    return 0;
}
