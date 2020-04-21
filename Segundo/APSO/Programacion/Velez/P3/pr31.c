#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
	printf("Soy pr31 y mi pid es: %d\n", getpid());

	int vpid = fork();
	if(vpid == 0){
		sleep(1);
		execl("pr32", "pr32", 0);
	}

	vpid = fork();
	if(vpid == 0){
		printf("Soy la copia de pr31 y mi pid es: %d\n", getpid());
		sleep(2);

		int pid = getpid();
		int f = open("prac3.dat", O_WRONLY | O_APPEND);
		write(f, (char*)&pid, sizeof(int));
		close(f);

		sleep(3);
		printf("Fin de la copia de pr31\n");
	}else{
		int f = creat("prac3.dat", 0700);
		close(f);

		sleep(6);
		printf("Fin de pr31 original\n");
	}

	return 0;
}
