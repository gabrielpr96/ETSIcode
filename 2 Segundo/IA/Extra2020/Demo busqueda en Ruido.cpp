#include <iostream>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <fstream>

#define MAX_ITERACIONES 1000
#define RESOLUCION 1
#define MAX_RANDOM_POSITION 500
#define N_REINICIOS 100
#define TEMPERATURA_INICIAL 100
#define TEMPERATURA_DECREMENTO 1
#define N_INDIVIDUOS 100
#define N_GENERACIONES 100
#define ELITISMO 1.5


using namespace std;

struct posicion{
    int x, y;
};

bool VERBOSE = false, LOG = false;
int mapaW, mapaH;
int **mapa;

posicion posicionAleatoria(){
    posicion tmp;
    tmp.x = rand()%MAX_RANDOM_POSITION;
    tmp.y = rand()%MAX_RANDOM_POSITION;
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
int puntuacion(posicion p){
    if(p.x < 0 || p.x >= mapaW || p.y < 0 || p.y >= mapaH)
        return 0;
    else
        return mapa[p.x][p.y];
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


posicion BusquedaCompleta(){
    int mejor = 0, x, y;

    for(int i = 0; i < mapaH; i++)
        for(int j = 0; j < mapaW; j++)
            if(mapa[j][i] > mejor){x=j;y=i;mejor=mapa[j][i];}

    posicion tmp;
    tmp.x = x;
    tmp.y = y;
    return tmp;
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
    centro.x = mapaW/2;
    centro.y = mapaH/2;
    int desviacionW = mapaW/2, desviacionH = mapaH/2;

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
                individuo.x = centro.x + 2*(rand()%desviacionW) - desviacionW;
            if(desviacionH == 0)
                individuo.y = centro.y;
            else
                individuo.y = centro.y + 2*(rand()%desviacionH) - desviacionH;
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

void cargarMapa(char* filename){
    int i;
    FILE* f = fopen(filename, "rb");

    unsigned char info[54];
    fread(info, sizeof(unsigned char), 54, f);

    mapaW = *(int*)&info[18];
    mapaH = *(int*)&info[22];
    mapa = new int*[mapaW];
    for(int i = 0; i < mapaW; i++)
        mapa[i] = new int[mapaH];

    int row_padded = (mapaW*3 + 3) & (~3);
    unsigned char* data = new unsigned char[row_padded];
    unsigned char tmp;

    for(int i = 0; i < mapaH; i++){
        fread(data, sizeof(unsigned char), row_padded, f);
        for(int j = 0; j < mapaW*3; j += 3){
            tmp = data[j];
            data[j] = data[j+2];
            data[j+2] = tmp;
            //imagen[(i*mapaW)+(j/3)] = RGB(data[j],data[j+1],data[j+2]);
            mapa[j/3][i] = data[j]+data[j+1]+data[j+2];
        }
    }
    fclose(f);
}

char menu(){
    system("CLS");
    cout << "Algoritmos de busqueda        IA 19/20 Borja Lopez Pineda" << endl << endl
         << "     1. " << (VERBOSE?"Desactivar":"Activar") << " modo verbose." << endl
         << "     2. " << (LOG?"Desactivar":"Activar") << " el log." << endl << endl
         << "     3. Probar HillClimb" << endl
         << "     4. Probar HillClimb con estocastica" << endl
         << "     5. Probar HillClimb con primera opcion" << endl
         << "     6. Probar HillClimb con reinicio aleatorio" << endl
         << "     7. Probar Simulated Annealing" << endl
         << "     8. Probar Genetic Algorithm" << endl << endl
         << "     9. Realizar busqueda completa" << endl
         << "     0. Salir" << endl << endl
         << "Opcion: ";
    char c = cin.get();
    cin.ignore();
    system("CLS");
    return c;
}

int main(){
    cargarMapa("mapa.bmp");
    srand(time(NULL));

    char c;
    do{
        c = menu();
        posicion mejor;
        switch(c){
            case '1':
                VERBOSE = !VERBOSE;
                cout << "Modo verbose " << (VERBOSE?"activado":"desactivado") << "." << endl;
            break;
            case '2':
                LOG = !LOG;
                cout << "Log " << (LOG?"activado":"desactivado") << "." << endl;
            break;
            case '3':
                mejor = HillClimb();
                cout << "Resultado de la busqueda por HillClimb" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
            case '4':
                mejor = HillClimbEstocastica();
                cout << "Resultado de la busqueda por HillClimb con estocastica" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
            case '5':
                mejor = HillClimbPrimeraOpcion();
                cout << "Resultado de la busqueda por HillClimb con primera opcion" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
            case '6':
                mejor = HillClimbReinicioAleatorio();
                cout << "Resultado de la busqueda por HillClimb con reinicio aleatorio" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
            case '7':
                mejor = SimulatedAnnealing();
                cout << "Resultado de la busqueda por Simulated Annealing" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
            case '8':
                mejor = GeneticAlgorithm();
                cout << "Resultado de la busqueda por Genetic Algorithm" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
            case '9':
                mejor = BusquedaCompleta();
                cout << "Resultado de la busqueda completa" << endl
                     << "Mejor: (" << mejor.x << ", " << mejor.y << ") -> " << puntuacion(mejor) << endl;
            break;
        }
        cout << endl << endl;
        system("PAUSE");
    }while(c != '0');

    for(int i = 0; i < mapaW; i++)
        delete [] mapa[i];
    delete [] mapa;
    return 0;
}
