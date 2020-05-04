#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <fcntl.h>

void continuar12(){
	printf("L33 con pid %d. Mensaje 3\n", getpid());
}
void continuar10(){
	printf("L33 con pid %d. Mensaje 5\n", getpid());
}

int main(){
	int fifo, pid33 = getpid();

	signal(12, continuar12);
	signal(10, continuar10);

	fifo = open("f3", O_RDWR);
	write(fifo, (char*)&pid33, sizeof(int));

	pause();

	write(2, (char*)&pid33, sizeof(int));

	pause();

	kill(getppid(), 12);

	return 0;
}
