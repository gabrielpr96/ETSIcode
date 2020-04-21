#include <unistd.h>
#include <stdio.h>

int main(){
	printf("Soy C Bienvenido\n");
	printf("Soy C y mi pid es: %d y el pid de C es: %d\n", getpid(), getppid());

	return 0;
}
