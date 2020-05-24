#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <time.h>

struct Parametros{
	int tesclusa;
	int lagomin;
	int lagomax;
	int mevoymin;
	int mevoymax;
};
int llega10;

void R10(){llega10 = 1;}
void R12(){
	printf("No es posible arrancar el servidor grafico\n");
	exit(-1);
}

int creaproceso(const char nombre[]);
int creaprocesoTub(const char nombre[], int tub[], int canal);
void leeparametros(struct Parametros *param,int *maxbarcos,int *creamin,int *creamax, int *caplago);

int main(){
	srand(time(0));
	int pidServidor, tubo[2], maxbarcos, creamin, creamax, caplago, lago, esclusae, esclusao, pidGlago, pidGesclusae, pidGesclusao;
	char uno = 1;
	struct Parametros param;
	llega10 = 0;

	signal(10,R10);
    signal(12,R12);

	pipe(tubo);

	mkfifo("/home/apso/lago", 0600);
	mkfifo("/home/apso/esclusae", 0600);
	mkfifo("/home/apso/esclusao", 0600);
	mkfifo("/home/apso/colalago", 0600);
	mkfifo("/home/apso/colaesclusae", 0600);
	mkfifo("/home/apso/colaesclusao", 0600);

	lago = open("/home/apso/lago", O_RDWR);
	esclusae = open("/home/apso/esclusae", O_RDWR);
	esclusao = open("/home/apso/esclusao", O_RDWR);

	leeparametros(&param, &maxbarcos, &creamin, &creamax, &caplago);

	write(esclusao, &uno, 1);
	write(esclusae, &uno, 1);
	for(int i = 0; i < caplago; i++)
		write(lago, &uno, 1);

	pidGlago = creaproceso("glago");
	pidGesclusae = creaproceso("gesclusae");
	pidGesclusao = creaproceso("gesclusao");
	pidServidor = creaproceso("servidor_ncurses");
	if(!llega10) pause();
	llega10 = 0;

	for(int i = 0; i < maxbarcos; i++){
		if(rand()%2 == 0)
			creaprocesoTub("barcoeste", tubo, 2);
		else
			creaprocesoTub("barcooeste", tubo, 2);

		write(tubo[1], (char*)&param, sizeof(struct Parametros));

		sleep((rand()%(creamax-creamin+1))+creamin);
	}

	for(int i = 0; i < maxbarcos; i++)
		wait(NULL);

	kill(pidGlago, 10);
	kill(pidGesclusae, 10);
	kill(pidGesclusao, 10);
	kill(pidServidor, 10);

	close(lago);
	close(esclusae);
	close(esclusao);
	unlink("/home/apso/lago");
	unlink("/home/apso/esclusae");
	unlink("/home/apso/esclusao");
	unlink("/home/apso/colalago");
	unlink("/home/apso/colaesclusae");
	unlink("/home/apso/colaesclusao");

	system("reset");
	return 0;
}

int creaproceso(const char nombre[]){
	int vpid = fork();
	if(vpid == 0){
		execl(nombre, nombre,NULL);
		perror("error de execl");
		exit(-1);
	}else if(vpid == -1){
		perror("error de fork");
		exit(-1);
	}
	return vpid;
}
int creaprocesoTub(const char nombre[], int tub[], int canal){
	int vpid = fork();
	if(vpid == 0){
		close(canal);
		dup(tub[0]);

		execl(nombre, nombre,NULL);
		perror("error de execl");
		exit(-1);
	}else if(vpid == -1){
		perror("error de fork");
		exit(-1);
	}
	return vpid;
}

void leeparametros(struct Parametros *param,int *maxbarcos,int *creamin,int *creamax, int *caplago){
	int ok=9;

	*maxbarcos=10;
	*creamin=1;
	*creamax=5;
	*caplago=6;
	param->tesclusa=7;
	param->lagomin=5;
	param->lagomax=10;
	param->mevoymin=10;
	param->mevoymax=15;

	while(ok == 9) {
		system("clear");
		printf("Valores de los parámetros...\n\n");
		printf("Numero de barcos que se crearan: %d\n",*maxbarcos);
		printf("Intervalo de tiempo para crear nuevos barcos: [%d-%d] \n",*creamin,*creamax);
		printf("Tiempo de estancia en la esclusa: %d\n",param->tesclusa);
		printf("Capacidad del lago: %d\n",*caplago);
		printf("Intervalo de tiempo en cruzar el lago: [%d-%d]\n",param->lagomin,param->lagomax);
		printf("Intervalo de tiempo en esperar para irse: [%d-%d]\n",param->mevoymin,param->mevoymax);
		printf("Pulse 9 si desea introducir nuevos valores, cualquier otro valor si desea continuar.\n");
		scanf("%d",&ok);

		if(ok == 9){
			do{
				printf("\nNumero de barcos que se crearan [maximo 50]: ");
				scanf("%d",maxbarcos);
			}while(*maxbarcos <= 0 || *maxbarcos > 50);

			do{
				printf("Intervalo de tiempo para crear nuevos barcos MIN [entre 1 y 10]: ");
				scanf("%d",creamin);
			}while(*creamin< 1 ||*creamin > 10 );

			do{
				printf("Intervalo de tiempo para crear nuevos barcos MAX [entre 5 y 20]: ");
				scanf("%d",creamax);
			}while(*creamax < 5 || *creamax > 20 || *creamax<=*creamin);

			do{
				printf("Tiempo de estancia en la esclusa [maximo 20]: ");
				scanf("%d",&param->tesclusa);
			}while(param->tesclusa <= 0 || param->tesclusa > 20);

			do{
				printf("Capacidad del lago [maximo 8]: ");
				scanf("%d",caplago);
			}while(*caplago <= 1 || *caplago > 8);

			do{
				printf("Intervalo de tiempo en cruzar el lago MIN [entre 1 y 10]: ");
				scanf("%d",&param->lagomin);
			}while(param->lagomin< 1 ||param->lagomin > 10 );

			do{
				printf("Intervalo de tiempo en cruzar el lago MAX [entre 5 y 20]: ");
				scanf("%d",&param->lagomax);
			}while(param->lagomax < 5 || param->lagomax > 20 || param->lagomax <= param->lagomin );

			do{
				printf("Intervalo de tiempo en esperar para irse MIN [entre 1 y 10]:");
				scanf("%d",&param->mevoymin);
			}while(param->mevoymin< 1 ||param->mevoymin > 10 );

			do{
				printf("Intervalo de tiempo en esperar para irse MAX [entre 5 y 20]: ");
				scanf("%d",&param->mevoymax);
			}while(param->mevoymax < 5 || param->mevoymax > 20 || param->mevoymax <= param->mevoymin);
		}
	}
}
