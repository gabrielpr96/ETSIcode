#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <fcntl.h>

void continuar(){}

int main(){
	int pid33;

	signal(14, continuar);

	sleep(3);

	read(2, (char*)&pid33, sizeof(char));

	printf("L34 con pid %d. Mensaje 4\n", getpid());

	alarm((rand()%6)+5);

	kill(pid33, 10);

	return 0;
}
