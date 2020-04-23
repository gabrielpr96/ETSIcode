#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <sys/types.h>

void mensaje2(){
	printf("pr42 con pid %d. Mensaje 2\n", getpid());
}

int main(){
	signal(10, mensaje2);
	pause();
	kill(getppid(), 12);
	pause();

	return 0;
}
