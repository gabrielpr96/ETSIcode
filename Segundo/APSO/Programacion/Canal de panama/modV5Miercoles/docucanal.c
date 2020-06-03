#include <sys/wait.h>
#include <sys/msg.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "comun.h"

int coladocu;

void R10(){
	close(coladocu);
	unlink("coladocu");
	exit(0);
}

int main(){
	int pid;
	signal(10, R10);

	srand(getpid());

	mkfifo("coladocu",0600);
    coladocu = open("coladocu",O_RDWR);


	while(1){
		 read(coladocu, (char*)&pid, sizeof(int));
		 sleep((rand()%5)+8);
		 //sleep(1);
		 kill(pid, 18);
	}

	return 0;
}
