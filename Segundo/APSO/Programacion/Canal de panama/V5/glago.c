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

int lago, colalago, pid;
int llega10 = 0;

void R10(){
	close(lago);
	close(colalago);

	exit(0);
}


int main(){
	char uno;

	signal(10, R10);

	lago = open("/home/apso/lago", O_RDWR);
	colalago = open("/home/apso/colalago", O_RDWR);

	while(llega10 == 0){
		read(colalago, (char*)&pid, sizeof(int));
		read(lago, &uno, 1);
		kill(pid, 16);
	}

	return 0;
}
