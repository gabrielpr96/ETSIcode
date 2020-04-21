#include <unistd.h>
#include <stdio.h>

int main(){
	printf("Soy A Bienvenido\n");
	printf("Soy A y mi pid es: %d\n", getpid());

	int vpid;
	vpid = fork();
	if(vpid == 0)
		execl("B", "B", 0);
	else
		printf("Soy A y el pid de B es: %d\n", vpid);
	vpid = fork();
	if(vpid == 0)
		execl("C", "C", 0);
	else
		printf("Soy A y el pid de C es: %d\n", vpid);

	sleep(1);
	return 0;
}
