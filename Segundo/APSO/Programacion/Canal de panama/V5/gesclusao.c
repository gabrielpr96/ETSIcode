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

int esclusao, colaesclusao;

void R10(){
	close(esclusao);
	close(colaesclusao);

	exit(0);
}


int main(){
	int pid;
	char uno;

	signal(10, R10);

	esclusao = open("/home/apso/esclusao", O_RDWR);
	colaesclusao = open("/home/apso/colaesclusao", O_RDWR);

	while(1){
		read(colaesclusao, (char*)&pid, sizeof(int));
		read(esclusao, &uno, 1);
		kill(pid, 16);
	}

	return 0;
}
