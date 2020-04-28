#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>

void continuar(){
	printf("pr52 con pid %d. Mensaje 2\n", getpid());
}

int main(){
	int pid54;

	signal(12, continuar);

	pid54 = fork();
	if(pid54 == 0){
		close(2);
		execl("pr54", "pr54", NULL);
	}

	pause();
	write(2, (char*)&pid54, sizeof(int));
	close(2);
	wait(NULL);
	return 0;
}
