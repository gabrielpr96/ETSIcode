#include <iostream>
#include <cstdlib>
#include <cmath>
#include <chrono>
#include <unistd.h>

#define FILAS 6
#define COLUMNAS 7
#define DRAW_JUGADOR "><"
#define DRAW_OPONENTE "()"
#define DRAW_VACIO "  "
#define WIN_WEIGHT 100000
#define N_EN_RAYA 4


using namespace std;

struct Posicion{
    int columna, fila;
};

enum Ficha {
    jugador,
    oponente,
    vacio
};
enum Direccion {
  derecha,
  izquierda,
  arriba,
  abajo,
  derechaArriba,
  derechaAbajo,
  izquierdaArriba,
  izquierdaAbajo
};

typedef Ficha Tablero[COLUMNAS][FILAS];


void pintar(ostream &s, const Tablero t);

void jugarMax(Tablero t, int rec, int &columna, int &puntuacion);
void jugarMin(Tablero t, int rec, int &columna, int &puntuacion);
void jugarPodaMax(Tablero t, int rec, int &columna, int &puntuacion, int alfa = -1000000, int beta = 1000000);
void jugarPodaMin(Tablero t, int rec, int &columna, int &puntuacion, int alfa = -1000000, int beta = 1000000);


void limpiar(Tablero t){
    for(int fila = 0; fila < FILAS; fila++)
        for(int columna = 0; columna < COLUMNAS; columna++)
            t[columna][fila] = vacio;
}
bool meter(Tablero t, int columna, Ficha ficha){
    int fila = FILAS-1;
    while(t[columna][fila] != vacio && fila >= 0) fila--;
    if(fila >= 0){
        t[columna][fila] = ficha;
        return true;
    }else return false;
}
bool sacar(Tablero t, int columna){
    int fila = 0;
    while(t[columna][fila] == vacio && fila < FILAS) fila++;
    if(fila < FILAS){
        t[columna][fila] = vacio;
        return true;
    }else return false;
}
void copiar(Tablero destino, const Tablero fuente){
    for(int fila = 0; fila < FILAS; fila++)
        for(int columna = 0; columna < COLUMNAS; columna++)
            destino[columna][fila] = fuente[columna][fila];
}
void copiar(Posicion destino, const Posicion fuente){
    destino.fila = fuente.fila;
    destino.columna = fuente.columna;
}
void inline put(Tablero t, Posicion p, Ficha f){
    t[p.columna][p.fila] = f;
}
Ficha inline get(const Tablero t, Posicion p){
    return t[p.columna][p.fila];
}
bool desplazar(Posicion &p, Direccion d){
    int fila = p.fila, columna = p.columna;

    switch(d){
    case derecha:
        columna++;
        break;
    case izquierda:
        columna--;
        break;
    case arriba:
        fila++;
        break;
    case abajo:
        fila--;
        break;
    case derechaArriba:
        columna++;
        fila++;
        break;
    case derechaAbajo:
        columna++;
        fila--;
        break;
    case izquierdaArriba:
        columna--;
        fila++;
        break;
    case izquierdaAbajo:
        columna--;
        fila--;
        break;
    }
    if(fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS){
        return false;
    }else{
        p.fila = fila;
        p.columna = columna;
        return true;
    }
}
Ficha cambiar(Ficha f){
    switch(f){
        case jugador: return oponente;
        case oponente: return jugador;
        case vacio: return vacio;
    }
}
Ficha char2Ficha(char c){
    switch(c){
        case 'O': return oponente;
        case 'X': return jugador;
        default: return vacio;
    }
}
char Ficha2Char(Ficha f){
    switch(f){
        case oponente: return 'O';
        case jugador: return 'X';
        default: return '-';
    }
}


int puntuar(const Tablero t, int f, int c, int df, int dc){
    int ptoJ = 0, ptoO = 0, ptoV = 0;

    for(int i = 0; i < 4; i++){
        if(t[c][f] == jugador) ptoJ++;
        if(t[c][f] == oponente) ptoO++;
        if(t[c][f] == vacio) ptoV++;

        f += df;
        c += dc;
    }

    if(ptoO == N_EN_RAYA){
        return -WIN_WEIGHT;
    }else if(ptoJ == N_EN_RAYA)
        return WIN_WEIGHT;
    else{
        if(ptoV + ptoJ == 4)
            return ptoJ;
        else
           return 0;

    }
}
int puntuar(const Tablero t){
    int ptoV = 0, ptoH = 0, ptoD1 = 0, ptoD2 = 0, f, c, d, s;

    //Verticales
    for (f = 0; f < FILAS-3; f++) {
        for (c = 0; c < COLUMNAS; c++) {
            s = puntuar(t, f, c, 1, 0);
            if (s == WIN_WEIGHT) return WIN_WEIGHT;
            if (s == -WIN_WEIGHT) return -WIN_WEIGHT;
            ptoV += s;
        }
    }

    //Horizontales
    for (f = 0; f < FILAS; f++) {
        for (c = 0; c < COLUMNAS - 3; c++) {
            s = puntuar(t, f, c, 0, 1);
            if (s == WIN_WEIGHT) return WIN_WEIGHT;
            if (s == -WIN_WEIGHT) return -WIN_WEIGHT;
            ptoH += s;
        }
    }

    //Diagonal 1 (Arriba izquierda a abajo derecha)
    for (f = 0; f < FILAS - 3; f++) {
        for (c = 0; c < COLUMNAS - 3; c++) {
            s = puntuar(t, f, c, 1, 1);
            if (s == WIN_WEIGHT) return WIN_WEIGHT;
            if (s == -WIN_WEIGHT) return -WIN_WEIGHT;
            ptoD1 += s;
        }
    }

    //Diagonal 2 (Abajo izquierda a arriba derecha)
    for (f = 3; f < FILAS; f++) {
        for (c = 0; c <= COLUMNAS - 4; c++) {
            s = puntuar(t, f, c, -1, 1);
            if (s == WIN_WEIGHT) return WIN_WEIGHT;
            if (s == -WIN_WEIGHT) return -WIN_WEIGHT;
            ptoD2 += s;
        }

    }

    //pintar(cout, t);
    //cout << (ptoV + ptoH + ptoD1 + ptoD2) << " H: " << ptoH << " V: " << ptoV << " D1: " << ptoD1 << " D2: " << ptoD2 << endl;
    return ptoV + ptoH + ptoD1 + ptoD2;
}

bool ganado(const Tablero t, Ficha ficha){
    int pto = puntuar(t);
    return (pto == WIN_WEIGHT && ficha == jugador) || (pto == -WIN_WEIGHT && ficha == oponente);


    int tmp = 0, f, c, d;
    //Horizontal
    for(f = 0; f < FILAS; f++){
        tmp = 0;
        for(c = 0; c < COLUMNAS;  c++){
            if (t[c][f] == ficha){
                tmp++;
                if(tmp == N_EN_RAYA) return true;
            }else tmp = 0;
        }
    }

    //Vertical
    for(c = 0; c < COLUMNAS;  c++){
        tmp = 0;
        for(f = 0; f < FILAS; f++){
            if (t[c][f] == ficha){
                tmp++;
                if(tmp == N_EN_RAYA) return true;
            }else tmp = 0;
        }
    }

    int DIAGONALES = COLUMNAS>FILAS?COLUMNAS:FILAS;

    //Diagonal Izquierda Derecha
    for(d = 0; d < DIAGONALES; d++){
        tmp = 0;
        if(d > FILAS){
            f = FILAS-1;
            c = d-f;
        }else{
            f = d;
            c = 0;
        }
        while(f >= 0 && c < COLUMNAS){
            if (t[c][f] == ficha){
                tmp++;
                if(tmp == N_EN_RAYA) return true;
            }else tmp = 0;
            f++;
            c++;
        }
    }

    //Diagonal Derecha Izquierda
    for(d = 0; d < DIAGONALES; d++){
        tmp = 0;
        if(d > FILAS){
            f = FILAS-1;
            c = COLUMNAS-(d-f)-1;
        }else{
            f = d;
            c = COLUMNAS-1;
        }
        while(f >= 0 && c >= 0){
            if (t[c][f] == ficha){
                tmp++;
                if(tmp == N_EN_RAYA) return true;
            }else tmp = 0;
            f++;
            c--;
        }
    }

    return false;
}

void pintar(ostream &s, const Tablero t){
    s << char(201);
    for(int columna = 0; columna < COLUMNAS; columna++)
        s << char(205) << char(205);
    s << char(187) << endl;
    for(int fila = 0; fila < FILAS; fila++){
        s << char(186);
        for(int columna = 0; columna < COLUMNAS; columna++){
            switch(t[columna][fila]){
            case jugador:
                s << DRAW_JUGADOR;
                break;
            case oponente:
                s << DRAW_OPONENTE;
                break;
            case vacio:
                s << DRAW_VACIO;
                break;
            }
        }
        s << char(186) << endl;
    }
    s << char(200);
    for(int columna = 0; columna < COLUMNAS; columna++)
        s << char(205) << char(205);
    s << char(188) << endl;
}
void pintarSimple(ostream &s, const Tablero t){
    for(int fila = 0; fila < FILAS; fila++){
        for(int columna = 0; columna < COLUMNAS; columna++)
            switch(t[columna][fila]){
            case jugador:
                s << 'x';
                break;
            case oponente:
                s << 'o';
                break;
            case vacio:
                s << ' ';
                break;
            }
        s << endl;
    }
}
void encode(ostream &s, const Tablero t){
    for(int c = 0; c < COLUMNAS; c++)
        for(int f = 0; f < FILAS; f++)
            s << Ficha2Char(t[c][f]);
}
void parse(char *s, Tablero t){
    for(int c = 0; c < COLUMNAS; c++)
        for(int f = 0; f < FILAS; f++)
            t[c][f] = char2Ficha(s[f+c*FILAS]);
}

void jugarMax(Tablero t, int rec, int &columna, int &puntuacion){
    int tmpC, tmpP;
    puntuacion = -1000000;
    columna = COLUMNAS-1;

    if(rec == 0){
        puntuacion = puntuar(t);
    }else{
        for(int c = 0; c < COLUMNAS; c++){
            if(meter(t, c, jugador)){
                jugarMin(t, rec-1, tmpC, tmpP);
                if(tmpP > puntuacion){
                    puntuacion = tmpP;
                    columna = c;
                }
                sacar(t, c);
            }
        }
    }
}
void jugarMin(Tablero t, int rec, int &columna, int &puntuacion){
    int tmpC, tmpP;
    puntuacion = 1000000;
    columna = COLUMNAS-1;

    if(rec == 0){
        puntuacion = puntuar(t);
    }else{
        for(int c = 0; c < COLUMNAS; c++){
            if(meter(t, c, oponente)){
                jugarMax(t, rec-1, tmpC, tmpP);
                if(tmpP < puntuacion){
                    puntuacion = tmpP;
                    columna = c;
                }
                sacar(t, c);
            }
        }
    }
}

void jugarPodaMax(Tablero t, int rec, int &columna, int &puntuacion, int alfa, int beta){
    int tmpC, tmpP;
    puntuacion = -1000000;
    columna = COLUMNAS-1;

    if(rec == 0 || ganado(t, jugador) || ganado(t, oponente)){
        puntuacion = puntuar(t);
    }else{
        for(int c = 0; c < COLUMNAS; c++){
            if(meter(t, c, jugador)){
                jugarPodaMin(t, rec-1, tmpC, tmpP, alfa, beta);
                if(tmpP > puntuacion){
                    puntuacion = tmpP;
                    alfa = tmpP;
                    columna = c;
                }
                sacar(t, c);
                if(alfa >= beta) return;
            }
        }
    }
}
void jugarPodaMin(Tablero t, int rec, int &columna, int &puntuacion, int alfa, int beta){
    int tmpC, tmpP;
    puntuacion = 1000000;
    columna = COLUMNAS-1;

    if(rec == 0 || ganado(t, jugador) || ganado(t, oponente)){
        puntuacion = puntuar(t);
    }else{
        for(int c = 0; c < COLUMNAS; c++){
            if(meter(t, c, oponente)){
                jugarPodaMax(t, rec-1, tmpC, tmpP, alfa, beta);
                if(tmpP < puntuacion){
                    puntuacion = tmpP;
                    beta = tmpP;
                    columna = c;
                }
                sacar(t, c);
                if(alfa >= beta) return;
            }
        }
    }
}

long maquina(Tablero t, int rec, Ficha f){
    int puntuacion, columna;
    auto start = chrono::steady_clock::now();
    if(f == jugador){
        jugarPodaMax(t, rec, columna, puntuacion);
        meter(t, columna, jugador);
    }else{
        jugarPodaMin(t, rec, columna, puntuacion);
        meter(t, columna, oponente);
    }
    auto stop = chrono::steady_clock::now();
    return chrono::duration_cast<chrono::milliseconds>(stop - start).count();
}

void humano(Tablero t, Ficha f){
    int c;
    do{
        pintar(cout, t);
        cout << " ";
        for(int i = 0; i < COLUMNAS; i++)
            cout << (i<10?" ":"") << i;

        cout << endl << endl << "Columna: ";
        cin >> c;
    }while(c < 0 || c >= COLUMNAS);
    meter(t, c, f);
}


bool checkGanador(const Tablero t){
    if(ganado(t, jugador)){
        cout << "Gana la maquina." << endl;
        pintar(cout, t);
        system("PAUSE");
        system("CLS");
        return true;
    }else if(ganado(t, oponente)){
        cout << "Gana el humano." << endl;
        pintar(cout, t);
        system("PAUSE");
        system("CLS");
        return true;
    }
    return false;
}
int main(int argc, char *argv[]){
    if(argc == 4){
        int n, c;
		sscanf(argv[2], "%d", &n);
		sscanf(argv[3], "%d", &c);
        Tablero t;
        parse(argv[1], t);
        meter(t, c, oponente);
        // tablero | ganador : 1 nadie, 2 oponente, 3 jugador
        if(ganado(t, oponente)){
            encode(cout, t);
            cout << "|2";
        }else{
            maquina(t, n, jugador);
            encode(cout, t);
            if(ganado(t, jugador))
                cout << "|3";
            else
                cout << "|1";
        }

        return 0;
    }

    Tablero t;

    limpiar(t);

    while(true){
        limpiar(t);
        long tiempo = -1;
        int rec = 4;
        cout << "Profundidad: ";
        cin >> rec;
        if(rec == 0)
            break;

        while(true){
            system("CLS");
            if(tiempo != -1)
                cout << "Jugada de MAX: " << tiempo << "ms" << endl;

            humano(t, oponente);

            if(checkGanador(t))
                break;

            tiempo = maquina(t, rec, jugador);

            if(checkGanador(t))
                break;
        }
    }

    return 0;
}
