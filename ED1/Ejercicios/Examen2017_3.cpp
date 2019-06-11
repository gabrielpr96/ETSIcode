#include <iostream>
#include <fstream>
#include <cstring>
#include <cstdio>

using namespace std;

void generarDatos(){
    ofstream file("BIBLIO.dat");
    file << "10 4 3\n5 2 8\n12 2 2\n0 0 0";
    file.close();
}

struct TNodo_Lista{
     int n;
     TNodo_Lista *sig;
     TNodo_Lista *ant;
};

void itoa(int num, char *cad){
    int n = 1, w = 0;
    while(n <= num)
        n *= 10;
    n /= 10;
    while(n > 0){
        cad[w] = num/n+'0';
        w++;
        num = num%n;
        n /= 10;
    }
    cad[w] = 0;
}

void seleccion(int N, int K, int M, char *sel){
    int n = N;
    char num[5];
    sel[0] = 0;

    TNodo_Lista *i, *j, *TMP;

    i = new TNodo_Lista;
    i->sig = NULL;
    i->n = 1;
    TMP = i;

    for(int n = 2; n <= N; n++){
        j = new TNodo_Lista;
        j->n = n;
        j->ant = i;
        j->sig = NULL;
        i->sig = j;
        i = j;
    }
    i = TMP;
    j->sig = i;
    i->ant = j;

    TNodo_Lista *iBOR, *jBOR;
    while(n > 0){
        for(int n = 1; n < K; n++)
            i = i->sig;
        for(int n = 1; n < M; n++)
            j = j->ant;

            cout << "i: " << i->n << "  j: " << j->n << "\n";

        if(i == j){
            itoa(i->n, num);
            strcat(sel, num);
            cout << "WRITE " << num << "\n";
        }else{
            itoa(i->n, num);
            strcat(sel, num);
            cout << "WRITE " << num << ", ";
            strcat(sel, " ");
            itoa(j->n, num);
            strcat(sel, num);
            cout << num << "\n";
        }
        strcat(sel, ", ");

        if(n > 2){
            iBOR = i;
            jBOR = j;
            while(i == iBOR || i == jBOR)
                i = i->sig;
            while(j == iBOR || j == jBOR)
                j = j->ant;

            cout << "MOV i: " << i->n << "  j: " << j->n << "\n";

            iBOR->ant->sig = iBOR->sig;
            iBOR->sig->ant = iBOR->ant;
            jBOR->ant->sig = jBOR->sig;
            jBOR->sig->ant = jBOR->ant;


            if(iBOR != jBOR)
                delete jBOR;
            delete iBOR;

            n -= iBOR==jBOR?1:2;

            cout << "Cantidad: " << n << "\n";
        }else if(n == 2){
            if(i == j){
                itoa(i->sig->n, num);
                strcat(sel, num);
                cout << "WRITE " << num << "\n";
            }

            delete i->sig;
            delete i;
            n = 0;
        }else if(n == 1){
            itoa(i->n, num);
            strcat(sel, num);
            cout << "WRITE " << num << "\n";

            delete i;
            n = 0;
        }
        cout << "\n";
    }

    if(sel[strlen(sel)-1] == ' ')
        sel[strlen(sel)-2] = 0;
}

void seleccion(const char *origen, const char *destino){
    ifstream in(origen);
    ofstream out(destino);

    char tmp[100];

    int N, K, M;
    do{
        in >> N >> K >> M;
        cout << N << " " << K << " " << M << "\n";
        if(!(N == 0 && K == 0 && M == 0)){
            seleccion(N, K, M, tmp);
            out << tmp << "\n";
        }
    }while(!(N == 0 && K == 0 && M == 0));

    in.close();
    out.close();
}


int main(){
    generarDatos();
    seleccion("BIBLIO.dat", "BIBLIO.out");
    return 0;
}
