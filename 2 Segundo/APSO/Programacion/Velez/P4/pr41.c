#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <sys/types.h>

void continuar(){
}

void mensaje5(){
	printf("pr41 con pid %d. Mensaje 5\n", getpid());
}

int main(){
	int pid42, pid43;
	struct sigaction estructura;

	signal(10, mensaje5);
	estructura.sa_flags = 0;
	estructura.sa_handler =  continuar;
	sigaction(12, &estructura, NULL);

	pid42 = fork();
	if(pid42 == 0){
		execl("pr42", "pr42", (char*)0);
	}
	pid43 = fork();
	if(pid43 == 0){
		execl("pr43", "pr43", (char*)0);
	}

	sleep(1);

	printf("pr41 con pid %d. Mensaje 1\n", getpid());
	kill(pid42, 10);
	pause();
	kill(pid43, 10);
	pause();
	kill(pid42, 9);
	kill(pid43, 9);


	return 0;
}
