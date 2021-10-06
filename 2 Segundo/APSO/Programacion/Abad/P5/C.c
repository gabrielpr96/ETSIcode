#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <errno.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>

void continuar(){}

int main(){
	int fifo, pidC = getpid();
	signal(11, continuar);

	fifo = open("fifo", O_RDWR);
	write(fifo, (char*) &pidC, sizeof(int));

	printf("Primer mensaje\n");

	kill(getppid(), 11);

	pause();

	printf("Quinto mensaje\n");

	exit(0);

	return 0;
}
