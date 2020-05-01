#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>

void continuar(){}

int main(){
	int tubo[2], n = 2, pidB, pidC;

	signal(11, continuar);
	mkfifo("fifo", 0600);
	pipe(tubo);

	write(tubo[1], (char*) &n, sizeof(int));

	pidB = fork();
	if(pidB == 0){
		close(2);
		dup(tubo[0]);
		execl("B", "B", NULL);
	}

	pidC = fork();
	if(pidC == 0)
		execl("C", "C", NULL);

	pause();
	printf("Segundo mensaje\n");

	kill(pidB, 11);

	wait(NULL);
	wait(NULL);

	printf("Ultimo mensaje\n");

	unlink("fifo");
	return 0;
}
