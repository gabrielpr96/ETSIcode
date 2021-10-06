#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
	printf("Soy A Bienvenido\n");
	printf("Soy A y mi pid es: %d\n", getpid());

	int vpid;

	vpid = fork();
	if(vpid == 0)
		execl("B", "B", (char*)0);

	printf("Soy A y el pid de B es: %d\n", vpid);

	vpid = fork();
	if(vpid == 0){
		close(1);
		creat("infoc", 0600);
		execl("C", "C", (char*)0);
	}

	printf("Soy A y el pid de C es: %d\n", vpid);

	sleep(1);
	return 0;
}
