#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>

const int MAXBARCOS = 10;
int llega10;

void R10(){llega10 = 1;}
void R12(){
	printf("No es posible arrancar el servidor grafico\n");
	exit(-1);
}

int creaproceso(const char nombre[]);

int main(){
	srand(time(0));
	llega10 = 0;
	int pidServidor;

	signal(10,R10);
    signal(12,R12);

	pidServidor = creaproceso("servidor_ncurses");
	if(!llega10) pause();
	llega10 = 0;

	for(int i = 0; i < MAXBARCOS; i++){
		if(rand()%2 == 0)
			creaproceso("barcoeste");
		else
			creaproceso("barcooeste");
		sleep(1);
	}

	for(int i = 0; i < MAXBARCOS; i++)
		wait(NULL);

	kill(pidServidor, 10);

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
