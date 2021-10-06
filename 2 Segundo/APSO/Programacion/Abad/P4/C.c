#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>

void mensaje5(){
	printf("Mensaje 5\n");
}

int main(){
	signal(10, mensaje5);

	sleep(1);
	printf("Mensaje 1\n");

	kill(getppid(), 10);
	pause();
	kill(getppid(), 11);
	pause();

	return 0;
}
