#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>

void mensaje3(){
	printf("Mensaje 3\n");
}
void mensaje4(){
	printf("Mensaje 4\n");
}

int main(){
	struct sigaction estructura;

	signal(10, mensaje3);
	estructura.sa_flags = 0;
	estructura.sa_handler =  mensaje4;
	sigaction(14, &estructura, NULL);

	pause();
	alarm(3);
	pause();
	kill(getppid(), 12);
	pause();

	return 0;
}
