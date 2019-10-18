#include <iostream>
#include <fstream>

using namespace std;

#define INCREMENTO 2

struct TProveedor {
    int Dni;
    char Nombre[150];
    char Direccion[150];
    int PosProducto1; //-1 no tiene productos actualmente
};
struct TProductos {
    int Codigo;
    char Nombre[150];
    int Stock;
    float Descuento;
    int PosSiguiente; //-1 no existe el siguiente producto
};

struct TConsul {//Estructura para realizar consultas de productos
    int PosProveedor; //Posición del Proveedor que verifica la consulta
    int PosProducto; //Posición del Producto que verifica la consulta
};

class Consultas {
    TConsul *ResConsulta; //Tabla dinámica
    int NumConsulta; //Nº de elementos de la tabla dinámica
    int TamaConsulta; //Tamaño de la tabla dinámica.
public:
    Consultas();
    void CargarDatos (int PStockMin, int PStockMax, float PDescuentoMin, float PDescuentoMax);
    void MostrarDatos();
};

//Constructor de la clase
Consultas::Consultas() {
    ResConsulta=NULL;
    NumConsulta=0;
    TamaConsulta=0;
};

void Consultas::CargarDatos (int PStockMin, int PStockMax, float PDescuentoMin, float PDescuentoMax){
    ifstream proveedores("Proveedor.dat"), productos("Productos.dat");
    if(!(proveedores && productos)) return;

    TProveedor provTMP;
    TProductos prodTMP;

    if(ResConsulta != NULL){
        delete[] ResConsulta;
        NumConsulta = 0;
        TamaConsulta = 0;
    }

    ResConsulta = new TConsul[INCREMENTO];

    int curProdPos = 0, curProvPos = 0;
    proveedores.read((char *) &provTMP, sizeof(TProveedor));
    while(!proveedores.eof()){
        curProdPos = provTMP.PosProducto1;

        while(curProdPos != -1){
            productos.seekg(sizeof(TProductos)*curProdPos, ios::beg);
            productos.read((char *) &prodTMP, sizeof(TProductos));

            if(prodTMP.Stock > PStockMin && prodTMP.Stock < PStockMax && prodTMP.Descuento > PDescuentoMin && prodTMP.Descuento < PDescuentoMax){
                if(NumConsulta == TamaConsulta){
                    TamaConsulta += INCREMENTO;
                    TConsul *ResConsultaTMP = new TConsul[TamaConsulta];
                    for(int i = 0; i < NumConsulta; i++)
                        ResConsultaTMP[i] = ResConsulta[i];
                    delete[] ResConsulta;
                    ResConsulta = ResConsultaTMP;
                }
                ResConsulta[NumConsulta].PosProveedor = curProvPos;
                ResConsulta[NumConsulta].PosProducto = curProdPos;
                NumConsulta++;
            }

            curProdPos = prodTMP.PosSiguiente;
        }

        proveedores.read((char *) &provTMP, sizeof(TProveedor));
        curProvPos++;
    }

    proveedores.close();
    productos.close();
}
void Consultas::MostrarDatos(){
    if(ResConsulta == NULL) return;
    ifstream proveedores("Proveedor.dat"), productos("Productos.dat");
    if(!(proveedores && productos)) return;

    TProveedor provTMP;
    TProductos prodTMP;

    int curProvPos = 0;
    proveedores.read((char *) &provTMP, sizeof(TProveedor));
    while(!proveedores.eof()){
        cout << "Proveedor DNI: " << provTMP.Dni << " Nombre: " << provTMP.Nombre << "\n****** Lista de productos ******\n";

        for(int i = 0; i < NumConsulta; i++){
            if(curProvPos == ResConsulta[i].PosProveedor){
                productos.seekg(sizeof(TProductos)*ResConsulta[i].PosProducto, ios::beg);
                productos.read((char *) &prodTMP, sizeof(TProductos));
                cout << "Codigo Producto: " << prodTMP.Codigo << " Stock: " << prodTMP.Stock << " Descuento: " << prodTMP.Descuento << "%\n";
            }
        }
        cout << "\n";

        proveedores.read((char *) &provTMP, sizeof(TProveedor));
        curProvPos++;
    }

    proveedores.close();
    productos.close();
}

void PrepararFicheros(){
    ofstream proveedores("Proveedor.dat"), productos("Productos.dat");

    TProveedor provTMP;
    provTMP.PosProducto1 = 2;
    proveedores.write((char *) &provTMP, sizeof(TProveedor));
    provTMP.PosProducto1 = -1;
    proveedores.write((char *) &provTMP, sizeof(TProveedor));
    provTMP.PosProducto1 = 1;
    proveedores.write((char *) &provTMP, sizeof(TProveedor));
    provTMP.PosProducto1 = 67;
    proveedores.write((char *) &provTMP, sizeof(TProveedor));
    provTMP.PosProducto1 = 65;
    proveedores.write((char *) &provTMP, sizeof(TProveedor));

    TProductos prodTMP;
    prodTMP.PosSiguiente = -1;
    prodTMP.Codigo = 2;
    prodTMP.Stock = 5;
    prodTMP.Descuento = 0;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = -1;
    prodTMP.Codigo = 1;
    prodTMP.Stock = 6;
    prodTMP.Descuento = 10;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = 4;
    prodTMP.Codigo = 1;
    prodTMP.Stock = 7;
    prodTMP.Descuento = 20;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = -1;
    prodTMP.Codigo = 4;
    prodTMP.Stock = 8;
    prodTMP.Descuento = 30;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = 0;
    prodTMP.Codigo = 2;
    prodTMP.Stock = 9;
    prodTMP.Descuento = 40;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = 3;
    prodTMP.Codigo = 3;
    prodTMP.Stock = 11;
    prodTMP.Descuento = 50;
    productos.write((char *) &prodTMP, sizeof(TProductos));

    prodTMP.PosSiguiente = -1;
    prodTMP.Codigo = -1;
    prodTMP.Stock = 0;
    prodTMP.Descuento = 0;
    for(int i = 0; i < 59; i++)
        productos.write((char *) &prodTMP, sizeof(TProductos));

    prodTMP.PosSiguiente = 68;
    prodTMP.Codigo = 1;
    prodTMP.Stock = 11;
    prodTMP.Descuento = 60;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = 69;
    prodTMP.Codigo = 2;
    prodTMP.Stock = 12;
    prodTMP.Descuento = 70;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = 66;
    prodTMP.Codigo = 1;
    prodTMP.Stock = 13;
    prodTMP.Descuento = 80;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = 5;
    prodTMP.Codigo = 2;
    prodTMP.Stock = 14;
    prodTMP.Descuento = 90;
    productos.write((char *) &prodTMP, sizeof(TProductos));
    prodTMP.PosSiguiente = -1;
    prodTMP.Codigo = 3;
    prodTMP.Stock = 15;
    prodTMP.Descuento = 100;
    productos.write((char *) &prodTMP, sizeof(TProductos));

    proveedores.close();
    productos.close();
}
void MostrarFicheros(){
    ifstream proveedores("Proveedor.dat"), productos("Productos.dat");

    if(!(proveedores && productos)) return;

    TProveedor provTMP;
    TProductos prodTMP;

    int curProdPos = 0, curProvPos = 0;
    proveedores.read((char *) &provTMP, sizeof(TProveedor));
    while(!proveedores.eof()){
        curProdPos = provTMP.PosProducto1;

        cout << "Proveedor " << (curProvPos+1) << ":\n";

        while(curProdPos != -1){
            productos.seekg(sizeof(TProductos)*curProdPos, ios::beg);
            productos.read((char *) &prodTMP, sizeof(TProductos));

            cout << "Producto " << (curProvPos+1) << "." << (prodTMP.Codigo) << " stock: " << prodTMP.Stock << " descuento: " << prodTMP.Descuento << "\n";

            if(prodTMP.Descuento > 100 || prodTMP.Descuento < 0) return;
            curProdPos = prodTMP.PosSiguiente;
        }

        proveedores.read((char *) &provTMP, sizeof(TProveedor));
        curProvPos++;

        cout << "\n";
    }

    proveedores.close();
    productos.close();
}

int main(){
    PrepararFicheros();
    MostrarFicheros();

    cout << "\n\n\n"
             "Cosa";

    Consultas C;

    C.CargarDatos(0, 999, 0, 100);
    C.MostrarDatos();
}
