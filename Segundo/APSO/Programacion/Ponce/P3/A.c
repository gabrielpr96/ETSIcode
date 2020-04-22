#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(){
	printf("Soy A, mi pid es: %d y el de mi padre es: %d\n", getpid(), getppid());

	int vpid;

	vpid = fork();
	if(vpid == 0){
		close(2);
		creat("pb-error.out", 0600);
		char  buff[4];
		snprintf(buff, sizeof(buff), "%d", getpid());
		execl("B", "B", buff, 0);
		printf("Error al crear el proceso B\n");
		return 1;
	}
	printf("Soy A y he creado al proceso B con pid: %d\n", vpid);

	vpid = fork();
	if(vpid == 0){
		close(1);
		creat("pc.out", 0600);
		int res = execl("C", "C", 0);
		printf("Error al crear el proceso C\n");
		return 1;
	}
	printf("Soy A y he creado al proceso C con pid: %d\n", vpid);

	sleep(1);
	return 0;
}
