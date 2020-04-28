#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>

void continuar(){}

int main(){
	int pid51, pid52, pid53, tubo[2], fifo;
	struct sigaction estructura;

	estructura.sa_flags = 0;
	estructura.sa_handler =  continuar;
	sigaction(12, &estructura, NULL);

	pid51 = getpid();
	mkfifo("fifo", 0600);
	fifo = open("fifo", O_RDWR);
	write(fifo, (char*)&pid51, sizeof(int));

	pipe(tubo);
	//close(tubo[0]);
	//close(tubo[1]);

	pid52 = fork();
	if(pid52 == 0){
		close(2);
		dup(tubo[1]);
		execl("pr52", "pr52", NULL);
	}

	pid53 = fork();
	if(pid53 == 0){
		close(2);
		dup(tubo[0]);
		execl("pr53", "pr53", NULL);
	}

	sleep(1);

	printf("pr51 con pid %d. Mensaje 1\n", getpid());
	kill(pid52, 12);
	pause();
	close(fifo);
	unlink("fifo");
	wait(NULL);
	wait(NULL);

	return 0;
}
