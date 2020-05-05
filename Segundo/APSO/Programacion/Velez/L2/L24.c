#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

void continuar(){
	printf("L24 con pid %d. Mensaje 4\n", getpid());
}

int main(){
	int pid22;

	signal(12, continuar);

	pause();

	read(2, (char*)&pid22, sizeof(int));

	kill(pid22, 12);

	close(2);

	return 0;
}
