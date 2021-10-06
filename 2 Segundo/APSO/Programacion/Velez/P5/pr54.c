#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>

void continuar(){
	printf("pr54 con pid %d. Mensaje 4\n", getpid());
}

int main(){
	int pid51, fifo;

	signal(12, continuar);

	pause();
	fifo = open("fifo", O_RDWR);
	read(fifo, (char*)&pid51, sizeof(int));
	close(fifo);
	kill(pid51, 12);

	return 0;
}
