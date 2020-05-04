#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <fcntl.h>

int main(){
	int fifo, pid33, pid34;

	pid34 = fork();
	if(pid34 == 0)
		execl("L34", "L34", NULL);

	sleep(1);

	fifo = open("f3", O_RDWR);
	read(fifo, (char*)&pid33, sizeof(int));

	printf("L32 con pid %d. Mensaje 2\n", getpid());

	kill(pid33, 12);

	wait(NULL);

	close(fifo);

	return 0;
}
