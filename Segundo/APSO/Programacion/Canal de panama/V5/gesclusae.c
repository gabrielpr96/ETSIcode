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

int esclusae, colaesclusae, pid;
int llega10 = 0;

void R10(){
	close(esclusae);
	close(colaesclusae);

	exit(0);
}


int main(){
	int lago, esclusae, esclusao, colalago, colaesclusae, colaesclusao, pid;
	char uno;

	signal(10, R10);

	esclusae = open("/home/apso/esclusae", O_RDWR);
	colaesclusae = open("/home/apso/colaesclusae", O_RDWR);

	while(llega10 == 0){
		read(colaesclusae, (char*)&pid, sizeof(int));
		read(esclusae, &uno, 1);
		kill(pid, 16);
	}

	return 0;
}
