#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
	printf("Soy pr32 y mi pid es: %d\n", getpid());

	int vpid = fork();
	if(vpid == 0){
		execl("pr33", "pr33", (char*)0);
	}

	int pid = getpid();
	int f = open("prac3.dat", O_WRONLY | O_APPEND);
	write(f, (char*)&pid, sizeof(int));
	close(f);

	sleep(3);
	printf("Fin de pr32\n");
	return 0;
}
