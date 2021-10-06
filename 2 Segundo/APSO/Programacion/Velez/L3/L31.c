#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/msg.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <fcntl.h>

void continuar(){
	printf("L31 con pid %d. Mensaje 6\n", getpid());
}

int main(){
	int pid32, pid33, tub3[2];

	pipe(tub3);
	mkfifo("f3", 0600);

	signal(12, continuar);

	printf("L31 con pid %d. Mensaje 1\n", getpid());

	pid32 = fork();
	if(pid32 == 0){
		close(2);
		dup(tub3[0]);
		execl("L32", "L32", NULL);
	}

	pid33 = fork();
	if(pid33 == 0){
		close(2);
		dup(tub3[1]);
		execl("L33", "L33", NULL);
	}
	close(tub3[0]);
	close(tub3[1]);

	pause();
	wait(NULL);
	wait(NULL);

	unlink("f3");

	return 0;
}
