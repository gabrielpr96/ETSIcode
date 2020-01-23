#include <iostream>

using namespace std;

class Numeros {
    int *array;
    int n, nmax;
public:
    Numeros(int capacidad, int ini, int incr) {
        n=nmax=capacidad;
        array = new int[n];
        for(int i=0; i<n; i++)
        array[i] = ini+incr*i;
    }
    ~Numeros() {
        delete [] array;
    }

    void ver() {
        for(int i=0;i<n;i++)
        cout << array[i] << " ";
        cout << endl;
    }

    void eliminarPrimero() {
        for(int i=1; i<n; i++)
            array[i-1]=array[i];
        if (n>0) n--;
    }
    int getN() { return n; }


    Numeros(const Numeros& o){
        nmax = o.nmax;
        n = o.n;
        array = new int[nmax];
        for(int i = 0; i < n; i++)
            array[i] = o.array[i];
    }

    Numeros& operator=(const Numeros& o){
        if(&o != this){
            delete [] array;
            nmax = o.nmax;
            n = o.n;
            array = new int[nmax];
            for(int i = 0; i < n; i++)
                array[i] = o.array[i];
        }
        return *this;
    }
};
