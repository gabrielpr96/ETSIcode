#include <iostream>
#include <cstdlib>
#include <cstdio>

using namespace std;

struct nodoVoto {
    int idPartido;
    nodoVoto *sig;
};

struct nodoUrna {
    int idUrna;
    nodoVoto *votos;
    nodoVoto *ultimoVoto;
    nodoUrna *sig;
};

struct nodoColegio {
    int idColegio;
    nodoUrna *urnas;
    nodoColegio *sig;
};

void colegioMasVotado(nodoColegio *colegios){
    nodoColegio *cTMP = colegios;
    nodoUrna *uTMP;
    nodoVoto *vTMP;

    int votosMax = -1, votosTMP, cMax;

    int i = 0;
    cTMP = colegios;
    while(cTMP != NULL){
        votosTMP = 0;

        uTMP = cTMP->urnas;
        while(uTMP != NULL){
            vTMP = uTMP->votos;

            while(vTMP != NULL){
                votosTMP++;
                vTMP = vTMP->sig;
            }

            uTMP = uTMP->sig;
        }

        if(votosTMP > votosMax){
            votosMax = votosTMP;
            cMax = cTMP->idColegio;
        }

        i++;
        cTMP = cTMP->sig;
    }

    if(votosMax == -1)
        cout << "No hay colegios.\n";
    else
        cout << "El colegio mas botado es el " << cMax << " con " << votosMax << " votos.\n";
}
void eliminar(nodoColegio **colegios){
    nodoColegio *cTMP = *colegios, *cBOR;
    nodoUrna *uTMP, *uBOR;
    nodoVoto *vTMP, *vBOR;
    while(cTMP != NULL){
        cBOR = cTMP;
        cTMP = cTMP->sig;

        uTMP = cBOR->urnas;
        while(uTMP != NULL){
            uBOR =  uTMP;
            uTMP = uTMP->sig;

            vTMP = uBOR->votos;
            while(vTMP != NULL){
                vBOR = vTMP;
                vTMP = vTMP->sig;

                delete vBOR;
            }

            delete uBOR;
        }

        delete cBOR;
    }

    *colegios = NULL;
}
void escrutinio(nodoColegio *colegios){
    int recuento[10];
    for(int i = 0; i < 10; i++)
        recuento[i] = 0;

    nodoColegio *cTMP = colegios;
    nodoUrna *uTMP;
    nodoVoto *vTMP;
    while(cTMP != NULL){
        uTMP = cTMP->urnas;
        while(uTMP != NULL){
            vTMP = uTMP->votos;
            while(vTMP != NULL){
                cout << "Un voto para " << vTMP->idPartido << "\n";
                recuento[vTMP->idPartido]++;
                vTMP = vTMP->sig;
            }
            uTMP = uTMP->sig;
        }
        cTMP = cTMP->sig;
    }

    for(int i = 0; i < 10; i++)
        cout << "El partido " << i << " tiene " << recuento[i] << " votos.\n";
}
void mostrarVotos(nodoColegio *colegios, int urna){
    nodoColegio *cTMP = colegios;
    nodoUrna *uTMP;
    bool encontrado = false;
    while(!encontrado && cTMP != NULL){
        uTMP = cTMP->urnas;
        while(!encontrado && uTMP != NULL){
            if(uTMP->idUrna == urna)
                encontrado = true;
            else
                uTMP = uTMP->sig;
        }
        if(!encontrado)
            cTMP = cTMP->sig;
    }
    if(encontrado){
        nodoVoto *vTMP = uTMP->votos;
        while(vTMP != NULL){
            cout << "Un voto para " << vTMP->idPartido << "\n";
            vTMP = vTMP->sig;
        }
    }else{
        cout << "No se encontro la urna.\n";
    }
}
bool insertarVoto(nodoColegio *colegios, int colegio, int urna, int partido){
    nodoColegio *cTMP = colegios;
    while(cTMP != NULL && cTMP->idColegio != colegio)
        cTMP = cTMP->sig;
    if(cTMP == NULL)
        return false;
    else{
        nodoUrna *uTMP = cTMP->urnas;
        while(uTMP != NULL && uTMP->idUrna != urna)
            uTMP = uTMP->sig;
        if(uTMP == NULL)
            return false;
        else{
            nodoVoto *nodo = new nodoVoto;
            nodo->idPartido = partido;
            nodo->sig = NULL;
            if(uTMP->ultimoVoto != NULL)
                uTMP->ultimoVoto->sig = nodo;
            uTMP->ultimoVoto = nodo;
            if(uTMP->votos == NULL)
                uTMP->votos = nodo;
            return true;
        }
    }
}
bool agregarUrna(nodoColegio *colegios, int colegio, int id){
    nodoColegio *cTMP = colegios;
    while(cTMP != NULL && cTMP->idColegio != colegio)
        cTMP = cTMP->sig;
    if(cTMP == NULL)
        return false;
    else{
        nodoUrna *nodo = new nodoUrna;
        nodo->idUrna = id;
        nodo->sig = cTMP->urnas;
        nodo->votos = NULL;
        nodo->ultimoVoto = NULL;
        cTMP->urnas = nodo;
        return true;
    }
}
void crearColegio(nodoColegio **colegios, int id){
    nodoColegio *nodo = new nodoColegio;
    nodo->idColegio = id;
    nodo->urnas = NULL;
    nodo->sig = *colegios;
    *colegios = nodo;
}

char menu(char &c){
    system("cls");
    cout << "1. Crear un colegio.\n"
        <<"2. Agregar una urna.\n"
        <<"3. Insertar un voto.\n"
        <<"4. Mostrar votos.\n"
        <<"5. Escrutar.\n"
        <<"6. Colegio con mas votos.\n"
        <<"0. Salir.\n\n"
        <<"Opcion: ";
    cin >> c;
    system("cls");
}

int main(){
    nodoColegio *colegios = NULL;
    char opt;
    int colegio, urna, partido;

    do{
        menu(opt);
        switch(opt){
            case '1'://Crear un colegio
                cout << "Introduzca la ID del colegio: ";
                cin >> colegio;
                crearColegio(&colegios, colegio);
                cout << "\nColegio creado correctamente.";
            break;
            case '2'://Agregar una urna
                cout << "Introduzca la ID del colegio: ";
                cin >> colegio;
                cout << "Introduzca la ID de la urna: ";
                cin >> urna;
                if(agregarUrna(colegios, colegio, urna))
                    cout << "\nUrna agregada correctamente.";
                else
                    cout << "\nNo se encontro el colegio.";
            break;
            case '3'://Insertar un voto
                cout << "Introduzca la ID del colegio: ";
                cin >> colegio;
                cout << "Introduzca la ID de la urna: ";
                cin >> urna;
                cout << "Introduzca la el codigo del partido: ";
                cin >> partido;
                if(insertarVoto(colegios, colegio, urna, partido))
                    cout << "\nVoto insertado correctamente.";
                else
                    cout << "\nNo se encontro la urna.";
            break;
            case '4'://Mostrar votos
                cout << "Introduzca la ID de la urna: ";
                cin >> urna;
                mostrarVotos(colegios, urna);
            break;
            case '5'://Escrutar
                escrutinio(colegios);
            break;
            case '6'://Escrutar
                colegioMasVotado(colegios);
            break;
        }
        if(opt != '0'){
            cin.ignore();
            cin.ignore();
        }
    }while(opt != '0');

    eliminar(&colegios);

    return 0;
}
