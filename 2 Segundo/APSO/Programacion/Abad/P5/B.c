#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <errno.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>

void continuar(){}

int main(){
	signal(11, continuar);
	int n, fifo, pidC;

	fifo = open("fifo", O_RDWR);

	pause();

	read(2, (char*) &n, sizeof(int));
	read(fifo, (char*) &pidC, sizeof(int));

	printf("Tercer mensaje\n");

	sleep(n);

	printf("Cuarto mensaje\n");

	kill(pidC, 11);

	exit(0);

	return 0;
}
