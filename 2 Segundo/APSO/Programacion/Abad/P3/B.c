#include <unistd.h>
#include <stdio.h>

int main(){
	printf("Soy B Bienvenido\n");
	printf("Soy B y mi pid es: %d y el pid de A es: %d\n", getpid(), getppid());

	return 0;
}
