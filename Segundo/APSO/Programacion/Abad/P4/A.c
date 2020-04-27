#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>

void mensaje2(){
	printf("Mensaje 2\n");
}
void ultimomensaje(){
	printf("Ultimo mensaje\n");
}
void nada(){
}

int main(){
	int pidB, pidC;
	struct sigaction estructura;

	signal(10, mensaje2);
	estructura.sa_flags = 0;
	estructura.sa_handler =  ultimomensaje;
	sigaction(11, &estructura, NULL);
	signal(12, nada);

	pidB = fork();
	if(pidB == 0)
		execl("B", "B", NULL);

	pidC = fork();
	if(pidC == 0)
		execl("C", "C", NULL);

	pause();
	kill(pidB, 10);
	pause();
	kill(pidC, 10);
	pause();
	kill(pidB, 9);
	kill(pidC, 9);

	return 0;
}
