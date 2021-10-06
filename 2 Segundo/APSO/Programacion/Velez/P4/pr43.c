#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <sys/types.h>

void mensaje3(){
	printf("pr43 con pid %d. Mensaje 3\n", getpid());
}

void mensaje4(){
	printf("pr43 con pid %d. Ha saltado el alarm. Mensaje 4\n", getpid());
}

int main(){
	struct sigaction estructura;
	signal(10, mensaje3);
	estructura.sa_flags = 0;
	estructura.sa_handler =  mensaje4;
	sigaction(14, &estructura, NULL);

	pause();
	alarm(10);
	pause();
	kill(getppid(), 10);
	pause();

	return 0;
}
