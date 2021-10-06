#include <iostream>
#include <cstring>

using namespace std;

typedef char Cadena[60];
#define NUM_PACIENTES 70

class Paciente{
    Cadena nombre;
    int edad;
    Cadena fechaIngreso;
    bool ingresado;
    float pruebas[4];
public:
    Paciente();
    void actualizarNombre(Cadena n);
    void actualizarEdad(int e);
    void actualizarFecha(Cadena f);
    void actualizarIngresado(bool p);
    void actualizarPrueba(int i, float p);
    void obtenerNombre(Cadena n);
    int obtenerEdad();
    void obtenerFecha(Cadena n);
    bool obtenerIngresado();
    float obtenerPrueba(int i);
};

Paciente::Paciente(){

}
void Paciente::actualizarNombre(Cadena n){
    strcpy(nombre, n);
}
void Paciente::actualizarEdad(int e){
    edad = e;
}
void Paciente::actualizarFecha(Cadena f){
    strcpy(fechaIngreso, f);
}
void Paciente::actualizarIngresado(bool p){
    ingresado = p;
}
void Paciente::actualizarPrueba(int i, float p){
    pruebas[i] = p;
}
void Paciente::obtenerNombre(Cadena n){
    strcpy(n, nombre);
}
int Paciente::obtenerEdad(){
    return edad;
}
void Paciente::obtenerFecha(Cadena n){
    strcpy(n, fechaIngreso);
}
bool Paciente::obtenerIngresado(){
    return ingresado;
}
float Paciente::obtenerPrueba(int i){
    return pruebas[i];
}

Paciente datos[NUM_PACIENTES];
int nPacientes = 0;

int buscarCliente(Cadena nombre){
    Cadena tmp;
    int pos = -1, i = 0;
    while(pos == -1 && i < nPacientes){
        datos[i].obtenerNombre(tmp);
        if(strcmp(tmp, nombre) == 0){
            pos = i;
        }
        i++;
    }
    return pos;
}

int main(){
    Cadena nombre, fecha;
    float prueba;
    int opt, edad, pos, contador;
    do{
        cout << "\n1 - Anadir los datos de un nuevo paciente\n"
            << "2 - Contabilizar pacientes ingresados por edad\n"
            << "3 - Anadir las pruebas de un paciente\n"
            << "4 - Mostrar la prueba con valor maximo de un paciente\n"
            << "5 - Salir.\n\n"
            << "\nIntroduce la opcion: ";
        cin >> opt;

        switch(opt){
            case 1:
                if(nPacientes < NUM_PACIENTES){
                    cout << "Introduzca el nombre: ";
                    cin >> nombre;
                    if(buscarCliente(nombre) == -1){
                        datos[nPacientes].actualizarNombre(nombre);
                        cout << "Introduzca la edad: ";
                        cin >> edad;
                        datos[nPacientes].actualizarEdad(edad);
                        cout << "Introduzca la fecha de ingreso: ";
                        cin >> fecha;
                        datos[nPacientes].actualizarFecha(fecha);

                        nPacientes++;
                    }else{
                        cout << "Ya existe un paciente con este nombre.\n";
                    }
                }else{
                }
            break;
            case 2:
                contador = 0;
                cout << "Introduzca una edad: ";
                cin >> edad;
                for(int i = 0; i < nPacientes; i++){
                    if(datos[i].obtenerEdad() == edad) contador++;
                }
                cout << "Hay " << contador << " pacientes con " << edad << " anos de edad\n";
            break;
            case 3:
                cout << "Introduzca el nombre: ";
                cin >> nombre;
                pos = buscarCliente(nombre);
                if(pos == -1){
                    cout << "Paciente no existe.\n";
                }else{
                    datos[pos].actualizarIngresado(true);
                    for(int i = 0; i < 4; i++){
                        cout << "Introduzca la la prueba " << (i+1) << ": ";
                        cin >> prueba;
                        datos[pos].actualizarPrueba(i, prueba);
                    }
                }
            break;
            case 4:
                cout << "Introduzca el nombre: ";
                cin >> nombre;
                pos = buscarCliente(nombre);
                if(pos == -1){
                    cout << "Paciente no existe.\n";
                }else{
                    if(datos[pos].obtenerIngresado()){
                        float maxVal = datos[pos].obtenerPrueba(0);
                        int maxPos = 0;
                        for(int i = 1; i < 4; i++){
                            if(datos[pos].obtenerPrueba(i) > maxVal){
                                maxPos = i;
                                maxVal = datos[pos].obtenerPrueba(i);
                            }
                        }
                        cout << "La prieba mas alta del paciente es la " << (maxPos+1) << " con un valor de " << maxVal << "\n";
                    }else{
                        cout << "Paciente no ingresado todavia\n";
                    }
                }
            break;
        }
    }while(opt != 5);
    return 0;
}
