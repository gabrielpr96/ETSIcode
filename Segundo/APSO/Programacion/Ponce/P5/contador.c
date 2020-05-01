#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/types.h>
#include <fcntl.h>

unsigned long n;

void alarma(){
	int fifo = open("fifo", O_RDWR);
	write(fifo, (char*) &n, sizeof(n));
	close(fifo);

	exit(0);
}


int main(){
	signal(14, alarma);
	n = 0;

	alarm(2);

	while(n < 200000000) n++;

	alarma();

	return 0;
}
