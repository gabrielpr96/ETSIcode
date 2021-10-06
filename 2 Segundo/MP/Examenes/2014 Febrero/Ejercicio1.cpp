#include <iostream>
#include <cstring>
#include <sstream>
#include <typeinfo>
#include <cstdlib>

using namespace std;

class Socio {
    const int num_socio;
    char *nombre;
    int edad;
    float cuota_anual;
    static float cuota[3];
    static int contador;
public:
    Socio(char *nom, int edad) : num_socio(contador++){
        nombre = new char[strlen(nom)+1];
        strcpy(nombre, nom);
        this->edad = edad;
        if(edad < 18){
            if(cuota[0] < 0){
                cout << "Cuota para socios menores de edad: ";
                cin >> cuota[0];
            }
            cuota_anual = cuota[0];
        }else if(edad < 67){
            if(cuota[1] < 0){
                cout << "Cuota para socios mayores de edad: ";
                cin >> cuota[1];
            }
            cuota_anual = cuota[1];
        }else{
            if(cuota[2] < 0){
                cout << "Cuota para socios jubilados: ";
                cin >> cuota[2];
            }
            cuota_anual = cuota[2];
        }
    }
    Socio(const Socio& s) : num_socio(contador++){
        nombre = new char[strlen(s.nombre)+1];
        strcpy(nombre, s.nombre);
        edad = s.edad;
        cuota_anual = s.cuota_anual;
    }
    virtual ~Socio(){
        delete [] nombre;
    }
    void setCuotaAnual(float cuota_anual){this->cuota_anual = cuota_anual;}
    float getCuotaAnual() const {return cuota_anual;}
    int getEdad() const {return edad;}
    int numSocio() const { return num_socio; }
    static float getCuota(int i) { return cuota[i]; }
    static void setCuotas(float c1, float c2, float c3){
        cuota[0] = c1;
        cuota[1] = c2;
        cuota[2] = c3;
    }

    Socio& operator=(const Socio& s){
        if(&s != this){
            delete [] nombre;
            nombre = new char[strlen(s.nombre)+1];
            strcpy(nombre, s.nombre);
            edad = s.edad;
            cuota_anual = s.cuota_anual;
        }
        return *this;
    }

    virtual string ver() const{
        stringstream s;
        s << nombre << " edad: " << edad << " cuota: " << cuota_anual;
        return s.str();
    }

    bool operator==(const Socio& s){
        return edad==s.edad && strcmp(nombre, s.nombre)==0;
    }

    static bool joven(const Socio& a, const Socio& b){
        return a.edad > b.edad;
    }
};
int Socio::contador = 1;
float Socio::cuota[3] = {-1,-1,-1};

ostream& operator<<(ostream& s, const Socio& o){
    s << o.ver();
    return s;
}

int restar(const Socio& a, const Socio& b){
    return a.getEdad()-b.getEdad();
}

class SocioDispacitado : public Socio{
    int discapacidad;
public:
    SocioDispacitado(char *nom, int edad, int discapacidad) : Socio(nom, edad){
        this->discapacidad = discapacidad;
        setCuotaAnual(getCuotaAnual()*((100-discapacidad)/100.0));
    }
    SocioDispacitado(const SocioDispacitado& s) : Socio(s){
        this->discapacidad = s.discapacidad;
        setCuotaAnual(getCuotaAnual()*((100-discapacidad)/100));
    }
    string ver() const{
        stringstream s;
        s << Socio::ver() << " discapacidad: " << discapacidad;
        return s.str();
    }
    //Este es adicional
    int getDiscapacidad() const {return discapacidad;}
};

class Club {
    Socio **listaSocios; //inicialmente el nº máximo de socios será igual al indicado en el constructor
    int nls; //nº elementos en listaSocios
    int nmaxls; //nmaxls: capacidad de listaSocios
    int buscarSocio(int num_socio); //devuelve la pos donde está en listaSocio, -1 si no está
public:
    Club(int cupoSocio){
        listaSocios = new Socio*[cupoSocio];
        nmaxls = cupoSocio;
        nls = 0;
    }
    ~Club(){
        for(int i = 0; i < nls; i++)
            delete listaSocios[i];
        delete [] listaSocios;
    }
    void alta(){
        if(nls < nmaxls){
            char nombre[50];
            int edad;
            cout << "Nombre: ";
            cin >> nombre;
            cout << "Edad: ";
            cin >> edad;
            char d;
            cout << "Discapacitado (s, n): ";
            cin >> d;
            if(d == 's'){
                int discapacidad;
                cout << "Discapacidad: ";
                cin >> discapacidad;
                listaSocios[nls] = new SocioDispacitado(nombre, edad, discapacidad);
            }else
                listaSocios[nls] = new Socio(nombre, edad);
            nls++;
        }else{
            cout << "Club lleno\n";
        }
    }
    void baja(int num_socio){
        int i = 0;
        bool encontrado = false;
        while(i < nls && !encontrado){
            if(listaSocios[i]->numSocio() == num_socio){
                for(int j = i; j < nls-1; j++)
                    listaSocios[j] = listaSocios[j+1];
                nls--;
                encontrado = true;
            }else i++;
        }
    }
    void ampliarCapacidad(int n){
        if(n > nmaxls){
            nmaxls = n;
            Socio** aux = new Socio*[nmaxls];
            for(int i = 0; i < nls; i++)
                aux[i] = listaSocios[i];
            delete [] listaSocios;
            listaSocios = aux;
        }
    }
    void verSocios(){
        for(int i = 0; i < nls; i++)
            cout << listaSocios[i]->ver() << "\n";
    }
    int nDiscapacitados(int a, int b){
        int n = 0;
        for(int i = 0; i < nls; i++)
            if(typeid(*listaSocios[i]) == typeid(SocioDispacitado) && listaSocios[i]->getEdad() > a && listaSocios[i]->getEdad() < b)
                n++;
        return n;
    }
    //Este lo pongo por practicar el dynamiccast;
    int nDiscapacidad(int a, int b){
        int n = 0;
        for(int i = 0; i < nls; i++){
            if(listaSocios[i]->getEdad() > a && listaSocios[i]->getEdad() < b){
                SocioDispacitado *s = dynamic_cast<SocioDispacitado*>(listaSocios[i]);
                if(s != NULL){
                    n += s->getDiscapacidad();
                }
            }
        }

        return n;
    }
};

int main() {
    Socio::setCuotas(10,20,15);
    Socio a("luis", 25), b("juan", 87), *c=new Socio("eva", 27);
    const Socio d("ana", 15);
    b=a; //copia los datos de a en b (excepto el nº socio)
    if (a==*c) cout << "a y c son iguales\n";
    cout << "d: " << d << "\nc: " << c->ver() << ", " << d.numSocio() << endl;
    if (Socio::joven(a, b)) cout << "a es mas joven que b\n";
    int diferencia_edad=restar(a,d);
    delete c;
    Club betis(2);
    betis.alta();
    betis.verSocios();
    betis.baja(1);
    betis.ampliarCapacidad(2);
    cout << "En el betis hay " << betis.nDiscapacitados(10,20) << " discapacitados entre 10 y 20 años\n";
    cout << "En el betis hay " << betis.nDiscapacidad(10,20) << " puntos de discapacidad total entre 10 y 20 años\n";
    system("pause"); return EXIT_SUCCESS;
}
