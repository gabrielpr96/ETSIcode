#include <iostream>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <fstream>

#define MAX_ITERACIONES 1000
#define RESOLUCION 0.001
#define MIN_RANDOM_POSITION -10
#define MAX_RANDOM_POSITION 10
#define N_REINICIOS 10
#define TEMPERATURA_INICIAL 100
#define TEMPERATURA_DECREMENTO 1
#define N_INDIVIDUOS 100
#define N_GENERACIONES 10
#define ELITISMO 1.5

using namespace std;

struct posicion{
    double x, y;
};

bool VERBOSE = false, LOG = true;

posicion posicionAleatoria(){
    posicion tmp;
    tmp.x = (double)rand()/(RAND_MAX+1)*(MAX_RANDOM_POSITION-MIN_RANDOM_POSITION)+MIN_RANDOM_POSITION;
    tmp.y = (double)rand()/(RAND_MAX+1)*(MAX_RANDOM_POSITION-MIN_RANDOM_POSITION)+MIN_RANDOM_POSITION;
    return tmp;
}
vector<posicion> generarVecinos(posicion actual){
    vector<posicion> vecinos;
    posicion tmp;
    tmp.x = actual.x;
    tmp.y = actual.y;
    tmp.x-=RESOLUCION; vecinos.push_back(tmp);
    tmp.y+=RESOLUCION; vecinos.push_back(tmp);
    tmp.x+=RESOLUCION; vecinos.push_back(tmp);
    tmp.x+=RESOLUCION; vecinos.push_back(tmp);
    tmp.y-=RESOLUCION; vecinos.push_back(tmp);
    tmp.y-=RESOLUCION; vecinos.push_back(tmp);
    tmp.x-=RESOLUCION; vecinos.push_back(tmp);
    tmp.x-=RESOLUCION; vecinos.push_back(tmp);
    return vecinos;
}
double puntuacion(posicion p){
    return sin(p.x*p.x+p.y*p.y);
}
void log(posicion p){
    if(LOG){
        ofstream out("log", std::ios::app);
        out << p.x << "\t" << p.y << endl;
        out.close();
    }
    if(VERBOSE)
        cout << "Actual: (" << p.x << ", " << p.y << ") -> " << puntuacion(p) << endl;
}



posicion HillClimb(){
    unsigned long long int iteraciones = 0;
    posicion mejorVecino = posicionAleatoria();
    posicion actual;
    do{
        log(mejorVecino);
        actual  = mejorVecino;
        if(VERBOSE)
            cout << "Actual: (" << actual.x << ", " << actual.y << ") -> " << puntuacion(actual) << endl;
        vector<posicion> vecinos = generarVecinos(actual);
        for(posicion vecino : vecinos)
            if(puntuacion(vecino) > puntuacion(mejorVecino))
                mejorVecino = vecino;
        iteraciones++;
    }while(!(actual.x == mejorVecino.x && actual.y == mejorVecino.y) && iteraciones < MAX_ITERACIONES);
    return mejorVecino;
}
posicion HillClimbEstocastica(){
    unsigned long long int iteraciones = 0;
    posicion mejorVecino = posicionAleatoria();
    posicion actual;
    do{
        log(mejorVecino);
        actual  = mejorVecino;
        if(VERBOSE)
            cout << "Actual: (" << actual.x << ", " << actual.y << ") -> " << puntuacion(actual) << endl;
        vector<posicion> vecinos = generarVecinos(actual);

        double media = 0;
        for(posicion vecino : vecinos)
            media += puntuacion(vecino);
        media /= vecinos.size();

        vector<posicion> mejores;
        for(posicion vecino : vecinos)
            if(puntuacion(vecino) > media)
                mejores.push_back(vecino);

        posicion elegido = mejores[rand()%mejores.size()];
        if(puntuacion(elegido) > puntuacion(mejorVecino))
            mejorVecino = elegido;

        iteraciones++;
    }while(!(actual.x == mejorVecino.x && actual.y == mejorVecino.y) && iteraciones < MAX_ITERACIONES);
    return mejorVecino;
}
posicion HillClimbPrimeraOpcion(){
    unsigned long long int iteraciones = 0;
    posicion mejorVecino = posicionAleatoria();
    posicion actual;
    do{
        log(mejorVecino);
        actual  = mejorVecino;
        if(VERBOSE)
            cout << "Actual: (" << actual.x << ", " << actual.y << ") -> " << puntuacion(actual) << endl;
        vector<posicion> vecinos = generarVecinos(actual);

        std::vector<posicion>::const_iterator iterator = vecinos.begin();
        while(iterator != vecinos.end() && actual.x == mejorVecino.x && actual.y == mejorVecino.y){
            if(puntuacion(*iterator) > puntuacion(mejorVecino))
                mejorVecino = *iterator;
            ++iterator;
        }

        iteraciones++;
    }while(!(actual.x == mejorVecino.x && actual.y == mejorVecino.y) && iteraciones < MAX_ITERACIONES);
    return mejorVecino;
}
posicion HillClimbReinicioAleatorio(){
    posicion mejor = posicionAleatoria();
    for(int reinicio = 0; reinicio < N_REINICIOS; reinicio++){
        unsigned long long int iteraciones = 0;
        posicion mejorVecino = posicionAleatoria();
        posicion actual;
        do{
            log(mejorVecino);
            actual  = mejorVecino;
            if(VERBOSE)
                cout << "Actual: (" << actual.x << ", " << actual.y << ") -> " << puntuacion(actual) << endl;
            vector<posicion> vecinos = generarVecinos(actual);
            for(posicion vecino : vecinos)
                if(puntuacion(vecino) > puntuacion(mejorVecino))
                    mejorVecino = vecino;
            iteraciones++;
        }while(!(actual.x == mejorVecino.x && actual.y == mejorVecino.y) && iteraciones < MAX_ITERACIONES);
        if(puntuacion(mejorVecino) > puntuacion(mejor))
            mejor = mejorVecino;
    }
    return mejor;
}

posicion SimulatedAnnealing(){
    unsigned long long int iteraciones = 0;
    int temperatura = TEMPERATURA_INICIAL;
    posicion mejorVecino = posicionAleatoria();
    posicion actual;
    do{
        log(mejorVecino);
        actual  = mejorVecino;
        vector<posicion> vecinos = generarVecinos(actual);

        while(actual.x == mejorVecino.x && actual.y == mejorVecino.y && iteraciones < MAX_ITERACIONES){
            posicion elegido = vecinos[rand()%vecinos.size()];
            if((puntuacion(elegido) > puntuacion(mejorVecino)) || (rand()/(float)RAND_MAX) < exp((puntuacion(elegido)-puntuacion(mejorVecino))/temperatura))
                mejorVecino = elegido;
            iteraciones++;
        }

        if(temperatura-TEMPERATURA_DECREMENTO > 0) temperatura -= TEMPERATURA_DECREMENTO;
    }while(iteraciones < MAX_ITERACIONES);
    log(mejorVecino);
    return mejorVecino;
}

posicion GeneticAlgorithm(){
    posicion centro;
    centro.x = MAX_RANDOM_POSITION/2;
    centro.y = MAX_RANDOM_POSITION/2;
    double desviacionW = MAX_RANDOM_POSITION/2, desviacionH = MAX_RANDOM_POSITION/2;

    vector<posicion> poblacion;
    int generacion = 0;
    while(generacion < N_GENERACIONES && !(desviacionW==0 && desviacionH==0)){
        poblacion.clear();
        //cout << desviacionH << "\t" << desviacionW << endl;
        for(int i = 0; i < N_INDIVIDUOS; i++){
            posicion individuo;
            if(desviacionW == 0)
                individuo.x = centro.x;
            else
                individuo.x = centro.x + (double)rand()/(RAND_MAX+1)*(2*desviacionW)-desviacionW;
            if(desviacionH == 0)
                individuo.y = centro.y;
            else
                individuo.y = centro.y + (double)rand()/(RAND_MAX+1)*(2*desviacionH)-desviacionH;
            poblacion.push_back(individuo);
            log(individuo);
        }

        vector<posicion> mejores;
        double media = 0;
        for(int i = 0; i < poblacion.size(); i++)
            media += puntuacion(poblacion[i]);
        media /= poblacion.size();
        for(int i = 0; i < poblacion.size(); i++)
            if(puntuacion(poblacion[i]) > media*ELITISMO)
                mejores.push_back(poblacion[i]);

        if(mejores.size() == 0)
            break;

        centro.x = 0;
        for(int i = 0; i < mejores.size(); i++)
            centro.x += mejores[i].x;
        centro.x /= mejores.size();
        centro.y = 0;
        for(int i = 0; i < mejores.size(); i++)
            centro.y += mejores[i].y;
        centro.y /= mejores.size();

        desviacionW = 0;
        for(int i = 0; i < mejores.size(); i++)
            desviacionW += pow(mejores[i].x-centro.x, 2);
        desviacionW /= mejores.size();
        desviacionW = sqrt(desviacionW);
        desviacionH = 0;
        for(int i = 0; i < mejores.size(); i++)
            desviacionH += pow(mejores[i].y-centro.y, 2);
        desviacionH /= mejores.size();
        desviacionH = sqrt(desviacionH);

        generacion++;
    }

    posicion mejor = posicionAleatoria();
    for(int i = 0; i < poblacion.size(); i++)
        if(puntuacion(poblacion[i]) > puntuacion(mejor))
            mejor = poblacion[i];
    return mejor;
}


int main(){
    srand(time(NULL));
    //posicion mejor = HillClimb();
    //posicion mejor = HillClimbEstocastica();
    //posicion mejor = HillClimbPrimeraOpcion();
    //posicion mejor = HillClimbReinicioAleatorio();
    posicion mejor = SimulatedAnnealing();
    //posicion mejor = GeneticAlgorithm();
    cout << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
    return 0;
}
